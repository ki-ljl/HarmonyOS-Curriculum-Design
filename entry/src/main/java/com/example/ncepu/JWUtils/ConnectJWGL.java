package com.example.ncepu.JWUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.ncepu.Utils.Exam;
import ohos.app.Context;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.*;

import com.example.ncepu.Utils.Grade;


public class ConnectJWGL {


    public Map<String,String> cookies_innet;

    Context mContext;

    private final String url = "https://202-204-74-178.webvpn.ncepu.edu.cn";
    public Map<String,String> cookies = new HashMap<>();
    private String modulus;
    private String exponent;
    private String csrftoken;
    private Connection connection;
    private Connection.Response response;
    private Document document;
    private String stuNum;
    private String jw_Password;
    private String in_Password;

    public ConnectJWGL(String stuNum, String in_Password, String jw_Password, Context mContext) throws Exception {
        this.stuNum = stuNum;
        this.in_Password = in_Password;
        this.jw_Password = jw_Password;
        this.mContext = mContext;
        Login_Innet login_innet = new Login_Innet(stuNum, in_Password);
        cookies_innet = login_innet.in_net();
    }

    public int init() throws Exception{
        if(cookies_innet.containsKey("webvpn_username")) {
            getCsrftoken();
            getRSApublickey();
            System.out.println("内网密码正确");
            return 1;
        }else {
            System.out.println("内网密码错误");
            return 0;
        }
    }

    // 获取csrftoken和Cookies
    private void getCsrftoken(){
        try{
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);

            connection = Jsoup.connect(url+ "/jwglxt/xtgl/login_slogin.html?language=zh_CN&_t="+new Date().getTime()).cookies(cookies_innet);
            connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36");
            connection.header("Connection", "keep-alive");
            response = connection.followRedirects(true).timeout(60000).execute();
//            response = connection.execute();
            cookies = response.cookies();
            //保存csrftoken
            document = Jsoup.parse(response.body());
            csrftoken = document.getElementById("csrftoken").val();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // 获取公钥并加密密码
    public void getRSApublickey() throws Exception{

//        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        connection = Jsoup.connect(url+ "/jwglxt/xtgl/login_getPublicKey.html?" +
                "time="+ new Date().getTime()).cookies(cookies_innet);
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36");
        connection.header("Connection", "keep-alive");
        //去
        response = connection.cookies(cookies).ignoreContentType(true).followRedirects(true).timeout(60000).execute();

        JSONObject jsonObject = JSON.parseObject(response.body());
        modulus = jsonObject.getString("modulus");
        exponent = jsonObject.getString("exponent");
        jw_Password = RSAEncoder.RSAEncrypt(jw_Password, B64.b64tohex(modulus), B64.b64tohex(exponent));

//        StrictMode.ThreadPolicy policy2=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy2);

        jw_Password = B64.hex2b64(jw_Password);
    }

    //登录
    public boolean beginLogin() throws Exception{

//        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        connection = Jsoup.connect(url+ "/jwglxt/xtgl/login_slogin.html").cookies(cookies_innet);
        //connection.header("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36");
        connection.header("Connection", "keep-alive");

        connection.data("csrftoken",csrftoken);
//        connection.data("language", "zh_CN");
        connection.data("yhm",stuNum);
        connection.data("mm", jw_Password);
        connection.data("mm", jw_Password);

        response = connection.cookies(cookies).ignoreContentType(true).followRedirects(true)
                .method(Connection.Method.POST).timeout(60000).execute();
//        response = connection.execute();
        System.out.println("登录后的cookies为:" + response.cookies().get("JSESSIONID"));
        Map<String, String> subCookies = new HashMap<>();
        subCookies.put("JSESSIONID", response.cookies().get("JSESSIONID"));
        System.out.println("subCookies为:" + subCookies);
        cookies = subCookies;
        document = Jsoup.parse(response.body());
//        System.out.println("登录后的界面为:" + document);
        if(document.getElementById("tips") == null){
            System.out.println("教务系统密码正确");
            return true;
        }else{
            System.out.println(document.getElementById("tips").text());
            return false;
        }
    }

    // 查询学生信息
    public Map<String, String> getStudentInformation() throws Exception {
//        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        connection = Jsoup.connect(url+ "/jwglxt/xsxxxggl/xsxxwh_cxCkDgxsxx.html?gnmkdm=N100801&su="+ stuNum);
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        connection.header("Connection", "keep-alive");
        response = connection.cookies(cookies_innet).cookies(cookies).ignoreContentType(true).followRedirects(true).timeout(60000).execute();
//        Log.d("dddddddddd", response.body());
        JSONObject jsonObject = JSON.parseObject(response.body());
        System.out.println(jsonObject);
        System.out.println("--- 基本信息 ---");
        System.out.println("学号：" + jsonObject.getString("xh_id"));
        System.out.println("性别：" + jsonObject.getString("xbm"));
        System.out.println("民族：" + jsonObject.getString("mzm"));
        System.out.println("学院：" + jsonObject.getString("jg_id"));
        System.out.println("班级：" + jsonObject.getString("bh_id"));
        System.out.println("专业：" + jsonObject.getString("zszyh_id"));
        System.out.println("状态：" + jsonObject.getString("xjztdm"));
        System.out.println("入学年份：" + jsonObject.getString("njdm_id"));
        System.out.println("证件号码：" + jsonObject.getString("zjhm"));
        System.out.println("政治面貌：" + jsonObject.getString("zzmmm"));
        Map<String, String> map = new HashMap<>();
        map.put("id", jsonObject.getString("xh_id"));
        map.put("class", jsonObject.getString("bh_id"));
        map.put("name", jsonObject.getString("xm"));
        map.put("major", jsonObject.getString("zyh_id"));
        map.put("sex", jsonObject.getString("xbm"));
        map.put("dept", jsonObject.getString("jg_id"));
        map.put("year", jsonObject.getString("njdm_id"));
        return map;
    }

    public void logout() throws Exception {
        connection = Jsoup.connect(url+ "/jwglxt/logout");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        connection.header("Connection", "keep-alive");
        response = connection.cookies(cookies_innet).cookies(cookies).ignoreContentType(true).execute();
    }

    // 获取成绩信息
    public ArrayList<Grade> getStudentGrade(String year , String term, String query_nature) throws Exception {
        Map<String,String> datas = new HashMap<>();
        datas.put("xnm", year);
        if(term.equals("")) {
            datas.put("xqm", "");
        }else {
            int term_ = Integer.parseInt(term);
            datas.put("xqm",String.valueOf(term_ * term_ * 3));
        }
        datas.put("_search","false");
        datas.put("nd",String.valueOf(new Date().getTime()));
        datas.put("queryModel.showCount","150");
        datas.put("queryModel.currentPage","1");
        datas.put("queryModel.sortName","");
        datas.put("queryModel.sortOrder","asc");
        datas.put("queryModel.sortName","");
        datas.put("time","0");

//        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        connection = Jsoup.connect(url+ "/jwglxt/cjcx/cjcx_cxDgXscj.html?gnmkdm=N305005&layout=default&su=" + stuNum);
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        connection.header("Connection", "keep-alive");
        response = connection.cookies(cookies_innet).cookies(cookies).method(Connection.Method.POST)
                .data(datas).ignoreContentType(true).followRedirects(true).timeout(60000).execute();
        connection = Jsoup.connect(url+ "/jwglxt/cjcx/cjcx_cxDgXscj.html?doType=query&gnmkdm=N305005");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        connection.header("Connection", "keep-alive");
        response = connection.cookies(cookies_innet).cookies(cookies).method(Connection.Method.POST)
                .data(datas).ignoreContentType(true).followRedirects(true).timeout(60000).execute();
        JSONObject jsonObject = JSON.parseObject(response.body());
        ArrayList<Grade> list = new ArrayList<>();
        JSONArray gradeTable = JSON.parseArray(jsonObject.getString("items"));
        for (Iterator iterator = gradeTable.iterator(); iterator.hasNext();) {
            JSONObject lesson = (JSONObject) iterator.next();
            Grade grade = new Grade();
            grade.setCourse_code(lesson.getString("kch"));
            grade.setCourse_nature(lesson.getString("kcxzmc"));
            grade.setCourse_name(lesson.getString("kcmc"));
            grade.setCredit(lesson.getString("xf"));
            grade.setGpa(lesson.getString("jd"));
            grade.setMark(lesson.getString("cj"));
            grade.setCollege(lesson.getString("kkbmmc"));
            grade.setClass_(lesson.getString("jxbmc"));
            grade.setTeacher(lesson.getString("jsxm"));
            grade.setGrade_nature(lesson.getString("ksxz"));
            grade.setXn(lesson.getString("xnmmc"));
            grade.setXq(lesson.getString("xqmmc"));
            list.add(grade);
        }
        if(query_nature.equals("全部")) {
            return list;
        }
        //删除不是当前课程性质的课程
        Iterator<Grade> it = list.iterator();
        while(it.hasNext()) {
            Grade grade = it.next();
            String nature = grade.getCourse_nature();
            if(!nature.equals(query_nature)) {
                it.remove();
            }
        }
        return list;
    }

    //获取考试信息
    public ArrayList<Exam> getExamInformation(String year, String term) throws Exception {
        Map<String, String> datas = new HashMap<>();
        if(!year.equals("")) {
            year = year.substring(0, 4);
        }
        datas.put("xnm", year);
        if(term.equals("")) {
            datas.put("xqm", "");
        }else {
            int term_ = Integer.parseInt(term);
            datas.put("xqm",String.valueOf(term_ * term_ * 3));
        }
        datas.put("ksmcdmb_id","");
        datas.put("kch","");
        datas.put("kc","");
        datas.put("ksrq","");
        datas.put("_search","fasle");
        datas.put("nd",String.valueOf(new Date().getTime()));
        datas.put("queryModel.showCount","150");
        datas.put("queryModel.currentPage","1");
        datas.put("queryModel.sortName","");
        datas.put("queryModel.sortOrder","asc");
        datas.put("time","0");

//        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        connection = Jsoup.connect(url+ "/jwglxt/kwgl/kscx_cxXsksxxIndex.html?doType=query&gnmkdm=N358105&su=" + stuNum);
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36");
        connection.header("Connection", "keep-alive");
        response = connection.cookies(cookies_innet).cookies(cookies).method(Connection.Method.POST)
                .data(datas).ignoreContentType(true).followRedirects(true).timeout(60000).execute();

        System.out.println(response.body());

        JSONObject jsonObject = JSON.parseObject(response.body());
        JSONArray examTable = JSON.parseArray(jsonObject.getString("items"));

        ArrayList<Exam> list = new ArrayList<>();

        for (Iterator iterator = examTable.iterator(); iterator.hasNext();) {
            JSONObject lesson = (JSONObject) iterator.next();
//            System.out.println(lesson.getString("jxbmc") + " " +
//                    lesson.getString("kch") + " " +
//                    lesson.getString("jsxx") + " " +
//                    lesson.getString("cdmc") + " " +
//                    lesson.getString("kssj"));
            Exam exam = new Exam();
            String kch = lesson.getString("kch");  //课程代码
            exam.setCourse_code(kch);
            String course_name = lesson.getString("kcmc");  //课程名称
            exam.setCourse_name(course_name);
            String class_name = lesson.getString("jxbmc");  //教学班名称
            exam.setClass_name(class_name);
            String class_comp = lesson.getString("jxbzc");  //教学班组成
            exam.setClass_comp(class_comp);
            String teacher = lesson.getString("jsxx");  //老师
            exam.setTeacher(teacher);
            String school_time = lesson.getString("sksj");  //上课时间
            exam.setSchool_time(school_time);
            String class_place = lesson.getString("jxdd");   //上课地点
            exam.setClass_place(class_place);
            String exam_time = lesson.getString("kssj");  //考试时间
            exam.setExam_time(exam_time);
            String exam_place = lesson.getString("cdmc");  //考试地点
            exam.setExam_place(exam_place);
            String major = lesson.getString("zymc");  //专业
            exam.setMajor(major);
            String campus = lesson.getString("cdxqmc");  //考试校区
            exam.setCampus(campus);
            String grade_class = lesson.getString("njmc");  //年级
            exam.setGrade_class(grade_class);
            list.add(exam);
        }
        return list;
    }
}
