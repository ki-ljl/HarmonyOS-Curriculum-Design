package com.example.ncepu.Student.Query;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Student.Query.slice.GradeAbilitySlice;
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

import java.util.*;

import static com.example.ncepu.MainAbility.connectJWGL;

public class GradeAbility extends Ability {

    private Button schoolYear, semester, course;
    private Button btn_query;
    private Image ib_back;
    private DirectionalLayout directionalLayout;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(GradeAbilitySlice.class.getName());
        super.setUIContent(ResourceTable.Layout_ability_grade);
        initViews();
    }

    private void initViews() {
        ib_back = (Image) findComponentById(ResourceTable.Id_ib_back);
        ib_back.setClickedListener(component -> onBackPressed());

        schoolYear = (Button) findComponentById(ResourceTable.Id_school_year);
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

        semester = (Button) findComponentById(ResourceTable.Id_semester);
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

        course = (Button) findComponentById(ResourceTable.Id_course);
        course.setClickedListener(component -> {
            String []items = {"全部", "必修课", "实践课", "校选修课", "专选课"};
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
        btn_query = (Button) findComponentById(ResourceTable.Id_btn_query);
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
                            list[0] = connectJWGL.getStudentGrade(y, s, c);
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
                            if(year.equals("")) {
                                list[0].get(i).setXn("全部");
                            }
                            if(semester_.equals("")) {
                                list[0].get(i).setXq("全部");
                            }
                        }
                        //将list中的内容转换为列表显示出来
                        //不断添加Text
                        directionalLayout = (DirectionalLayout) findComponentById(ResourceTable.Id_cj_view);
                        directionalLayout.removeAllComponents();
                        for(int i = 0; i < list[0].size(); i++) {
                            Text text = new Text(getContext());
                            String f = list[0].get(i).getCourse_name() + "\t\t\t\t\t\t\t\t\t\t" + list[0].get(i).getMark();
                            text.setText(f);
                            text.setTextAlignment(TextAlignment.LEFT);
                            ShapeElement bg = new ShapeElement();
                            bg.setRgbColor(RgbColor.fromArgbInt(Color.getIntColor("#94D1C5")));
                            bg.setCornerRadius(50);
                            text.setBackground(bg);
                            text.setTextColor(Color.WHITE);
                            text.setWidth(directionalLayout.getWidth());
                            text.setHeight(90);
                            text.setTextSize(60);
                            directionalLayout.addComponent(text);
                            //添加分割线
                            Component line = new Component(getContext());
                            ShapeElement bg_line = new ShapeElement();
                            bg_line.setRgbColor(RgbColor.fromArgbInt(Color.getIntColor("#ffffff")));
                            line.setBackground(bg_line);
                            line.setWidth(directionalLayout.getWidth());
                            line.setHeight(5);
                            directionalLayout.addComponent(line);
                            //设置点击事件，点击后弹出ListDialog
                            final int t = i;
                            text.setClickedListener(component12 -> {
                                ListDialog listDialog = new ListDialog(getContext());
                                String []items = {
                                        "学年:" + list[0].get(t).getXn(),
                                        "学期:" + list[0].get(t).getXq(),
                                        "课程代码:" + list[0].get(t).getCourse_code(),
                                        "课程性质:" + list[0].get(t).getCourse_nature(),
                                        "考试性质:" + list[0].get(t).getGrade_nature(),
                                        "学分:" + list[0].get(t).getCredit(),
                                        "绩点:" + list[0].get(t).getGpa(),
                                        "开课学院:" + list[0].get(t).getCollege(),
                                        "教学班:" + list[0].get(t).getClass_(),
                                        "任课教师:" + list[0].get(t).getTeacher()
                                };
                                listDialog.setItems(items);
                                listDialog.setSize(800, 1100);
                                listDialog.setTitleText("详细信息:");
                                listDialog.setAlignment(LayoutAlignment.CENTER);
                                listDialog.setOnSingleSelectListener(new IDialog.ClickedListener() {
                                    @Override
                                    public void onClick(IDialog iDialog, int i) {
                                        listDialog.destroy();
                                    }
                                });
                                listDialog.show();
                            });
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
