package com.example.ncepu.Student.Query;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Student.Query.slice.GPAAbilitySlice;
import com.example.ncepu.Utils.Grade;
import com.example.ncepu.Utils.ToastUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.colors.ColorConverter;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.utils.Rect;
import ohos.agp.utils.TextAlignment;
import ohos.agp.window.dialog.BaseDialog;
import ohos.agp.window.dialog.IDialog;
import ohos.agp.window.dialog.ListDialog;
import ohos.agp.window.dialog.PopupDialog;
import ohos.global.resource.Resource;
import ohos.multimodalinput.event.KeyEvent;
import ohos.utils.IntentConstants;

import java.text.DecimalFormat;
import java.util.*;

import static com.example.ncepu.MainAbility.connectJWGL;

public class GPAAbility extends Ability {

    private Button schoolYear, semester, course;
    private Button btn_query;
    private Image ib_back;
    private RoundProgressBar bar;


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(GPAAbilitySlice.class.getName());
        super.setUIContent(ResourceTable.Layout_ability_gpa);
        initViews();
    }

    private void initViews() {
        ib_back = (Image) findComponentById(ResourceTable.Id_ib_back_gpa);
        ib_back.setClickedListener(component -> onBackPressed());
        bar = (RoundProgressBar) findComponentById(ResourceTable.Id_r_bar);
        schoolYear = (Button) findComponentById(ResourceTable.Id_school_year_gpa);
        schoolYear.setClickedListener(component -> {
            String []items = {"全部", "2024-2025", "2023-2024", "2022-2023", "2021-2022", "2020-2021",
                    "2019-2020", "2018-2019", "2017-2018", "2016-2017"};
            PopupDialog popupDialog = new PopupDialog(getContext(), component);
            RadioContainer radioContainer = new RadioContainer(getContext());
            for (int i = 0; i < items.length; i++) {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setText(items[i]);
                radioButton.setWidth(375);
                radioButton.setHeight(75);
                ShapeElement bg = new ShapeElement();
                bg.setRgbColor(RgbColor.fromArgbInt(Color.getIntColor("#A3D4F5")));
                bg.setCornerRadius(30);
                radioButton.setBackground(bg);
                radioButton.setTextSize(48);
                radioButton.setTextColorOff(Color.WHITE);
                radioButton.setMarginsLeftAndRight(10, 10);
                radioButton.setMarginsTopAndBottom(10, 10);
                radioButton.setTextAlignment(TextAlignment.LEFT);

                radioButton.setClickedListener(component1 -> {
                    schoolYear.setText(radioButton.getText());
                    popupDialog.destroy();
                });
                radioContainer.addComponent(radioButton);
            }
            radioContainer.setPadding(10, 10, 10, 10);
            popupDialog.setCustomComponent(radioContainer);
            popupDialog.setHasArrow(true);
            popupDialog.setArrowOffset(25);
            popupDialog.setMode(LayoutAlignment.LEFT | LayoutAlignment.BOTTOM);
            popupDialog.setBackColor(new Color(Color.getIntColor("#FFFFFF")));
            popupDialog.show();
        });

        semester = (Button) findComponentById(ResourceTable.Id_semester_gpa);
        semester.setClickedListener(component -> {
            String []items = {"全部", "1", "2"};
            PopupDialog popupDialog = new PopupDialog(getContext(), component);
            RadioContainer radioContainer = new RadioContainer(getContext());
            for (int i = 0; i < items.length; i++) {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setText(items[i]);
                radioButton.setWidth(375);
                radioButton.setHeight(75);
                ShapeElement bg = new ShapeElement();
                bg.setRgbColor(RgbColor.fromArgbInt(Color.getIntColor("#A3D4F5")));
                bg.setCornerRadius(30);
                radioButton.setBackground(bg);
                radioButton.setTextSize(48);
                radioButton.setTextColorOff(Color.WHITE);
                radioButton.setMarginsLeftAndRight(10, 10);
                radioButton.setMarginsTopAndBottom(10, 10);
                radioButton.setTextAlignment(TextAlignment.LEFT);
                radioButton.setClickedListener(component1 -> {
                    semester.setText(radioButton.getText());
                    popupDialog.destroy();
                });
                radioContainer.addComponent(radioButton);
            }
            radioContainer.setPadding(10, 10, 10, 10);
            popupDialog.setCustomComponent(radioContainer);
            popupDialog.setHasArrow(true);
            popupDialog.setArrowOffset(25);
            popupDialog.setMode(LayoutAlignment.LEFT | LayoutAlignment.BOTTOM);
            popupDialog.setBackColor(new Color(Color.getIntColor("#FFFFFF")));
            popupDialog.show();
        });

        course = (Button) findComponentById(ResourceTable.Id_course_gpa);
        course.setClickedListener(component -> {
            String []items = {"全部", "必修", "必修+实践", "必修+专选", "必修-体育", "除去校选"};
            PopupDialog popupDialog = new PopupDialog(getContext(), component);
            RadioContainer radioContainer = new RadioContainer(getContext());
            for (int i = 0; i < items.length; i++) {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setText(items[i]);
                radioButton.setWidth(375);
                radioButton.setHeight(75);
                ShapeElement bg = new ShapeElement();
                bg.setRgbColor(RgbColor.fromArgbInt(Color.getIntColor("#A3D4F5")));
                bg.setCornerRadius(30);
                radioButton.setBackground(bg);
                radioButton.setTextSize(48);
                radioButton.setTextColorOff(Color.WHITE);
                radioButton.setMarginsLeftAndRight(10, 10);
                radioButton.setMarginsTopAndBottom(10, 10);
                radioButton.setTextAlignment(TextAlignment.LEFT);

                radioButton.setClickedListener(component1 -> {
                    course.setText(radioButton.getText());
                    popupDialog.destroy();
                });
                radioContainer.addComponent(radioButton);
            }
            radioContainer.setPadding(10, 10, 10, 10);
            popupDialog.setCustomComponent(radioContainer);
            popupDialog.setHasArrow(true);
            popupDialog.setArrowOffset(25);
            popupDialog.setMode(LayoutAlignment.LEFT | LayoutAlignment.BOTTOM);
            popupDialog.setBackColor(new Color(Color.getIntColor("#FFFFFF")));
            popupDialog.show();
        });

        //查询点击事件
        btn_query = (Button) findComponentById(ResourceTable.Id_btn_query_gpa);
        btn_query.setClickedListener(component -> {
            String year = schoolYear.getText();
            String semester_ = semester.getText();
            String course_nature = course.getText();
            if(year.equals("学年")) {
                ToastUtil.showMessage(this, "请选择学年！");
            }else if(semester_.equals("学期")) {
                ToastUtil.showMessage(this, "请选择学期！");
            }else if(course_nature.equals("课程性质")) {
                ToastUtil.showMessage(this, "请选择课程性质！");
            }else {
                //开始查询成绩并显示
                try {
                    if(year.equals("全部")) {
                        year = "";
                    }else {
                        year = year.substring(0, 4);
                    }
                    if(semester_.equals("全部")) {
                        semester_ = "";
                    }
                    final ArrayList<Grade>[] list = new ArrayList[]{new ArrayList()};
                    final String y = year;
                    final String s = semester_;
                    final String c = course_nature;
                    Thread thread = new Thread(() -> {
                        try {
                            list[0] = connectJWGL.getStudentGrade(y, s, "全部");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    thread.start();
                    thread.join();
//                    ArrayList<Grade> list = connectJWGL.getStudentGrade(year, semester_, course_nature);
                    if (list[0].size() == 0) {
                        ToastUtil.showMessage(this, "没有查到记录！");
                    }else {
                        for(int i = 0; i < list[0].size(); ++i) {
                            list[0].get(i).setXn(year);
                            list[0].get(i).setXq(semester_);
                        }
                        //设置GPA
                        double currentGPA = getGPA(list[0], course_nature);
//                        bar = (RoundProgressBar) findComponentById(ResourceTable.Id_r_bar);
//                        bar.setProgressValue((int) (100 * currentGPA / 5));
//                        DecimalFormat df   = new DecimalFormat(".00");
//                        bar.setProgressHintText("GPA:" + df.format(currentGPA));
//                        ToastUtil.showMessage(this, "GPA:" + df.format(currentGPA));
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                GPAAbility.this.getUITaskDispatcher().delayDispatch(() -> {
                                    bar.setProgressValue((int) (100 * currentGPA / 5));
                                    DecimalFormat df   = new DecimalFormat(".00");
                                    bar.setProgressHintText("GPA:" + df.format(currentGPA));
                                }, 0);
                            }
                        }, 0, 60000);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private double getGPA(ArrayList<Grade> list, String nature) {
        double total = 0.0;
        double total_num = 0.0;
        list = cut(list, nature);
        for(int i = 0; i < list.size(); i++) {
            double credit = Double.parseDouble(list.get(i).getCredit());
            double mark = Double.parseDouble(list.get(i).getGpa()) * 10 + 50;
            total_num += credit;
            total += credit * mark;
        }
        double gpa = (total / total_num - 50) / 10;
        return gpa;
    }


    public ArrayList<Grade> cut(ArrayList<Grade> list, String nature) {
        if(nature.equals("全部")) {
            return list;
        }
        ArrayList<String> list_nature = new ArrayList<>();
        if(nature.length() > 2) {
            if(nature.equals("除去校选")) {
                list_nature.add("必修课");
                list_nature.add("实践课");
                list_nature.add("专选课");
            }else if(nature.equals("必修-体育")) {
                list_nature.add("必修课");
            }else {
                String []natures = nature.split("\\+");
                for(int i = 0; i < natures.length; i++) {
                    list_nature.add(natures[i] + "课");
                }
            }
        }else {
            list_nature.add(nature + "课");
        }
        Iterator<Grade> it = list.iterator();
        while(it.hasNext()) {
            Grade grade = it.next();
            String nature_ = grade.getCourse_nature();
            if(!list_nature.contains(nature_)) {
                it.remove();
            }
        }
        if(nature.equals("必修-体育")) {
            Iterator<Grade> its = list.iterator();
            while(its.hasNext()) {
                Grade grade = its.next();
                String name = grade.getCourse_name();
                if(name.contains("体育")) {
                    System.out.println("体育删除");
                    its.remove();
                }
            }
        }
        return list;
    }
}
