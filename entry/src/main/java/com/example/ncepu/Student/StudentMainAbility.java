package com.example.ncepu.Student;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Student.slice.StudentMainAbilitySlice;
import com.example.ncepu.Utils.PreferenceUtils;
import com.example.ncepu.Utils.ToastUtil;
import com.example.ncepu.provider.TestPageProvider;
import com.youth.banner.Banner;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.IDialog;
import ohos.agp.window.dialog.ListDialog;
import ohos.agp.window.dialog.ToastDialog;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;
import ohos.multimodalinput.event.KeyEvent;
import ohos.os.ProcessManager;
import ohos.utils.IntentConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentMainAbility extends Ability {

    private RadioContainer radioContainer;
    private Text real_sign;
    private RadioButton rb_main;
    private RadioButton rb_query;
    private RadioButton rb_predict;
    private RadioButton rb_user;
    private PageSlider pageSlider;
    private LayoutScatter layoutScatter;
    private DirectionalLayout query, user;
    private List<Component> pageViews;
    private Image cj, exam, gpa, cult;
    private Banner banner;
    private List<Integer> list;
    private List<String> titles;
    private DependentLayout csdn, logout, dept, major, year, id, sex, motto, ver;
    private Text user_class, user_name, dept_1, major_1, year_1, id_1, sex_1, motto_1;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(StudentMainAbilitySlice.class.getName());
        super.setUIContent(ResourceTable.Layout_ability_student_main);
//        initViews();
//        initClick();
//        try {
//            initBanner();
//        } catch (NotExistException e) {
//            e.printStackTrace();
//        } catch (WrongTypeException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if(keyCode == KeyEvent.KEY_BACK) {
//            ToastUtil.showMessage(this, "hhh");
//            terminateAbility();
//            System.exit(0);
//            ProcessManager.kill(ProcessManager.getPid());
//            Intent intent = new Intent();
//            Set<String> st = new HashSet<>();
//            st.add(IntentConstants.ENTITY_HOME);
//            Operation operation = new Intent.OperationBuilder()
//                    .withDeviceId("")
//                    .withBundleName("com.example.ncepu")//包名
//                    .withAction(IntentConstants.ACTION_HOME)
//                    .withEntities(st)
//                    .withFlags(Intent.FLAG_ABILITY_CLEAR_MISSION)
//                    .build();
//            intent.setOperation(operation);
//            startAbility(intent);
            return true;
        }
        return super.onKeyDown(keyCode, keyEvent);
    }

//    private void initViews() {
//        radioContainer = (RadioContainer) findComponentById(ResourceTable.Id_rg_tab_bar);
//        rb_query = (RadioButton) findComponentById(ResourceTable.Id_rb_query);
//        rb_user = (RadioButton) findComponentById(ResourceTable.Id_rb_user);
//        real_sign = (Text) findComponentById(ResourceTable.Id_right_desc_6);
//        initPageSlider();
//        initUserInfo();
//    }
//
//    private void initUserInfo() {
//        //班级
//        user_class = (Text) findComponentById(ResourceTable.Id_user_class);
//        user_class.setText(PreferenceUtils.getInstance().getString("class", ""));
//        //姓名
//        user_name = (Text) findComponentById(ResourceTable.Id_user_name);
//        user_name.setText(PreferenceUtils.getInstance().getString("name", ""));
//        //学院
//        dept_1 = (Text) findComponentById(ResourceTable.Id_right_desc_1);
//        dept_1.setText(PreferenceUtils.getInstance().getString("dept", ""));
//        //专业
//        major_1 = (Text) findComponentById(ResourceTable.Id_right_desc_2);
//        major_1.setText(PreferenceUtils.getInstance().getString("major", ""));
//        //入学年份
//        year_1 = (Text) findComponentById(ResourceTable.Id_right_desc_3);
//        year_1.setText(PreferenceUtils.getInstance().getString("year", ""));
//        //学号
//        id_1 = (Text) findComponentById(ResourceTable.Id_right_desc_4);
//        id_1.setText(PreferenceUtils.getInstance().getString("stu_id", ""));
//        //性别
//        sex_1 = (Text) findComponentById(ResourceTable.Id_right_desc_5);
//        sex_1.setText(PreferenceUtils.getInstance().getString("sex", ""));
//        //个性签名
//        motto_1 = (Text) findComponentById(ResourceTable.Id_right_desc_6);
//        motto_1.setText(PreferenceUtils.getInstance().getString("sign", ""));
//    }
//
//    private void initBanner() throws NotExistException, WrongTypeException, IOException {
////        LayoutScatter layoutScatter = LayoutScatter.getInstance(getContext());
////        DirectionalLayout layout = (DirectionalLayout) layoutScatter.parse(ResourceTable.Id_banner_d, null, false);
//        list = new ArrayList<>();
//        titles = new ArrayList<>();
//        banner = (Banner) findComponentById(ResourceTable.Id_banner);
////        list.add(ResourceTable.Media_ncepu_1);
//        list.add(ResourceTable.Media_ncepu_2);
//        list.add(ResourceTable.Media_ncepu_3);
//        list.add(ResourceTable.Media_ncepu_4);
//        list.add(ResourceTable.Media_ncepu_5);
//        list.add(ResourceTable.Media_ncepu_6);
//        for(int i = 1; i <= 5; i++) {
//            titles.add(i + "/5");
//        }
//        //设置参数
//        banner.setImages(list)
//                .setBannerTitles(titles)
//                .setDelayTime(3000)
//                .setBannerStyle(5)
//                .setScaleType(0)
//                .setTitleTextSize(60);
//        banner.start();
//    }
//
//    private void initClick() {
//        //成绩查询
//        cj = (Image) findComponentById(ResourceTable.Id_iv_grid_1);
//        cj.setClickedListener(component -> {
//            Intent intent = new Intent();
//            Operation operation = new Intent.OperationBuilder()
//                    .withDeviceId("")
//                    .withBundleName("com.example.ncepu")//这个必须填包名
//                    .withAbilityName("com.example.ncepu.Student.Query.GradeAbility")//这个必须填包名+类名
//                    .build();
//            intent.setOperation(operation);
//            startAbility(intent);
//        });
//
//        //考试查询
//        exam = (Image) findComponentById(ResourceTable.Id_iv_grid_3);
//        exam.setClickedListener(component -> {
////            ToastUtil.showMessage(this, "hhhh");
//            Intent intent = new Intent();
//            Operation operation = new Intent.OperationBuilder()
//                    .withDeviceId("")
//                    .withBundleName("com.example.ncepu")//这个必须填包名
//                    .withAbilityName("com.example.ncepu.Student.Query.ExamAbility")//这个必须填包名+类名
//                    .build();
//            intent.setOperation(operation);
//            startAbility(intent);
//        });
//
//        //GPA查询
//        gpa = (Image) findComponentById(ResourceTable.Id_iv_grid_4);
//        gpa.setClickedListener(component -> {
//            Intent intent = new Intent();
//            Operation operation = new Intent.OperationBuilder()
//                    .withDeviceId("")
//                    .withBundleName("com.example.ncepu")//这个必须填包名
//                    .withAbilityName("com.example.ncepu.Student.Query.GPAAbility")//这个必须填包名+类名
//                    .build();
//            intent.setOperation(operation);
//            startAbility(intent);
//        });
//
//        //教学计划查询
//        cult = (Image) findComponentById(ResourceTable.Id_iv_grid_6);
//        cult.setClickedListener(component -> {
//            Intent intent = new Intent();
//            Operation operation = new Intent.OperationBuilder()
//                    .withDeviceId("")
//                    .withBundleName("com.example.ncepu")//这个必须填包名
//                    .withAbilityName("com.example.ncepu.Student.Query.ScorePdfAbility")//这个必须填包名+类名
//                    .build();
//            intent.setOperation(operation);
//            startAbility(intent);
//        });
//
//        //博客
//        csdn = (DependentLayout) findComponentById(ResourceTable.Id_csdn);
//        csdn.setClickedListener(component -> {
//            Intent intent = new Intent();
//            Operation operation = new Intent.OperationBuilder()
//                    .withDeviceId("")
//                    .withBundleName("com.example.ncepu")//这个必须填包名
//                    .withAbilityName("com.example.ncepu.Student.User.CSDNAbility")//这个必须填包名+类名
//                    .build();
//            intent.setOperation(operation);
//            startAbility(intent);
//        });
//
//        //退出登录
//        logout = (DependentLayout) findComponentById(ResourceTable.Id_logout);
//        logout.setClickedListener(component -> {
//            //弹出一个对话框
//            ListDialog listDialog = new ListDialog(this);
//            String []items = {""};
//            listDialog.setItems(items);
//            listDialog.setBackColor(Color.getIntColor("#E8E8C1"));
//            listDialog.setTitleText("您确认退出登录吗?");
//            listDialog.setButton(0, "取消", (iDialog, i) -> listDialog.destroy());
//            listDialog.setButton(1, "确认", new IDialog.ClickedListener() {
//                @Override
//                public void onClick(IDialog iDialog, int i) {
//                    //删除登录缓存文件并跳转至登录界面
//                    PreferenceUtils.getInstance().delete();
//                    Intent intent = new Intent();
//                    Operation operation = new Intent.OperationBuilder()
//                            .withDeviceId("")
//                            .withBundleName("com.example.ncepu")//这个必须填包名
//                            .withAbilityName("com.example.ncepu.MainAbility")//这个必须填包名+类名
//                            .build();
//                    intent.setOperation(operation);
//                    startAbility(intent);
//                }
//            });
//            listDialog.setAlignment(LayoutAlignment.CENTER);
//            listDialog.setOnSingleSelectListener((iDialog, i) -> {
//                listDialog.destroy();
//            });
//            listDialog.show();
//        });
//
//        //学院
//        dept = (DependentLayout) findComponentById(ResourceTable.Id_dept);
//        dept.setClickedListener(component -> {
//            Text text = (Text) findComponentById(ResourceTable.Id_right_desc_1);
//            ToastUtil.showMessage(getContext(), text.getText());
//        });
//
//        //专业
//        major = (DependentLayout) findComponentById(ResourceTable.Id_major);
//        major.setClickedListener(component -> {
//            Text text = (Text) findComponentById(ResourceTable.Id_right_desc_2);
//            ToastUtil.showMessage(getContext(), text.getText());
//        });
//
//        //入学年份
//        year = (DependentLayout) findComponentById(ResourceTable.Id_year);
//        year.setClickedListener(component -> {
//            Text text = (Text) findComponentById(ResourceTable.Id_right_desc_3);
//            ToastUtil.showMessage(getContext(), text.getText());
//        });
//
//        //学号
//        id = (DependentLayout) findComponentById(ResourceTable.Id_id);
//        id.setClickedListener(component -> {
//            Text text = (Text) findComponentById(ResourceTable.Id_right_desc_4);
//            ToastUtil.showMessage(getContext(), text.getText());
//        });
//
//        //性别
//        sex = (DependentLayout) findComponentById(ResourceTable.Id_sex);
//        sex.setClickedListener(component -> {
//            Text text = (Text) findComponentById(ResourceTable.Id_right_desc_5);
//            ToastUtil.showMessage(getContext(), text.getText());
//        });
//
//        //个性签名
//        motto = (DependentLayout) findComponentById(ResourceTable.Id_motto);
//        motto.setClickedListener(component -> {
//            Intent intent = new Intent();
//            Operation operation = new Intent.OperationBuilder()
//                    .withDeviceId("")
//                    .withBundleName("com.example.ncepu")//这个必须填包名
//                    .withAbilityName("com.example.ncepu.Student.User.SignatureAbility")//这个必须填包名+类名
//                    .build();
//            intent.setOperation(operation);
//            startAbility(intent);
//        });
//
//        //版本更新
//        ver = (DependentLayout) findComponentById(ResourceTable.Id_ver);
//        ver.setClickedListener(new Component.ClickedListener() {
//            @Override
//            public void onClick(Component component) {
//                ToastUtil.showMessage(getContext(), "暂无新版本发布!");
//            }
//        });
//
//    }
//
//    private void initPageSlider() {
//        pageSlider = (PageSlider) findComponentById(ResourceTable.Id_vpager);
//        layoutScatter = LayoutScatter.getInstance(getContext());
//        query = (DirectionalLayout) layoutScatter.parse(ResourceTable.Layout_ability_query_fragment, null, false);
//        user = (DirectionalLayout) layoutScatter.parse(ResourceTable.Layout_ability_user_fragment, null, false);
//        pageViews = new ArrayList<>();
//        pageViews.add(query);
//        pageViews.add(user);
//        //设置provider
//        pageSlider.setProvider(new TestPageProvider(pageViews, this));
//        //添加监听事件
//        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
//            @Override
//            public void onPageSliding(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSlideStateChanged(int i) {
//
//            }
//
//            @Override
//            public void onPageChosen(int i) {
//                ((RadioButton) radioContainer.getComponentAt(i)).setChecked(true);
//            }
//        });
//        radioContainer.setMarkChangedListener((radioContainer, i) -> pageSlider.setCurrentPage(i));
//    }
}
