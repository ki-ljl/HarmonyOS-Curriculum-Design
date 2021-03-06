package com.example.ncepu.Student.slice;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Utils.PreferenceUtils;
import com.example.ncepu.Utils.ToastUtil;
import com.example.ncepu.provider.TestPageProvider;
import com.youth.banner.Banner;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.IDialog;
import ohos.agp.window.dialog.ListDialog;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentMainAbilitySlice extends AbilitySlice {

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
        super.setUIContent(ResourceTable.Layout_ability_student_main);
        initViews();
        initClick();
        try {
            initBanner();
        } catch (NotExistException e) {
            e.printStackTrace();
        } catch (WrongTypeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    private void initViews() {
        radioContainer = (RadioContainer) findComponentById(ResourceTable.Id_rg_tab_bar);
        rb_query = (RadioButton) findComponentById(ResourceTable.Id_rb_query);
        rb_user = (RadioButton) findComponentById(ResourceTable.Id_rb_user);
        real_sign = (Text) findComponentById(ResourceTable.Id_right_desc_6);
        initPageSlider();
        initUserInfo();
    }

    private void initUserInfo() {
        //??????
        user_class = (Text) findComponentById(ResourceTable.Id_user_class);
        user_class.setText(PreferenceUtils.getInstance().getString("class", ""));
        //??????
        user_name = (Text) findComponentById(ResourceTable.Id_user_name);
        user_name.setText(PreferenceUtils.getInstance().getString("name", ""));
        //??????
        dept_1 = (Text) findComponentById(ResourceTable.Id_right_desc_1);
        dept_1.setText(PreferenceUtils.getInstance().getString("dept", ""));
        //??????
        major_1 = (Text) findComponentById(ResourceTable.Id_right_desc_2);
        major_1.setText(PreferenceUtils.getInstance().getString("major", ""));
        //????????????
        year_1 = (Text) findComponentById(ResourceTable.Id_right_desc_3);
        year_1.setText(PreferenceUtils.getInstance().getString("year", ""));
        //??????
        id_1 = (Text) findComponentById(ResourceTable.Id_right_desc_4);
        id_1.setText(PreferenceUtils.getInstance().getString("stu_id", ""));
        //??????
        sex_1 = (Text) findComponentById(ResourceTable.Id_right_desc_5);
        sex_1.setText(PreferenceUtils.getInstance().getString("sex", ""));
        //????????????
        motto_1 = (Text) findComponentById(ResourceTable.Id_right_desc_6);
        motto_1.setText(PreferenceUtils.getInstance().getString("sign", ""));
    }

    private void initBanner() throws NotExistException, WrongTypeException, IOException {
//        LayoutScatter layoutScatter = LayoutScatter.getInstance(getContext());
//        DirectionalLayout layout = (DirectionalLayout) layoutScatter.parse(ResourceTable.Id_banner_d, null, false);
        list = new ArrayList<>();
        titles = new ArrayList<>();
        banner = (Banner) findComponentById(ResourceTable.Id_banner);
//        list.add(ResourceTable.Media_ncepu_1);
        list.add(ResourceTable.Media_ncepu_2);
        list.add(ResourceTable.Media_ncepu_3);
        list.add(ResourceTable.Media_ncepu_4);
        list.add(ResourceTable.Media_ncepu_5);
        list.add(ResourceTable.Media_ncepu_6);
        for(int i = 1; i <= 5; i++) {
            titles.add(i + "/5");
        }
        //????????????
        banner.setImages(list)
                .setBannerTitles(titles)
                .setDelayTime(3000)
                .setBannerStyle(5)
                .setScaleType(0)
                .setTitleTextSize(60);
        banner.start();
    }

    private void initClick() {
        //????????????
        cj = (Image) findComponentById(ResourceTable.Id_iv_grid_1);
        cj.setClickedListener(component -> {
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.ncepu")//?????????????????????
                    .withAbilityName("com.example.ncepu.Student.Query.GradeAbility")//?????????????????????+??????
                    .build();
            intent.setOperation(operation);
            startAbility(intent);
        });

        //????????????
        exam = (Image) findComponentById(ResourceTable.Id_iv_grid_3);
        exam.setClickedListener(component -> {
//            ToastUtil.showMessage(this, "hhhh");
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.ncepu")//?????????????????????
                    .withAbilityName("com.example.ncepu.Student.Query.ExamAbility")//?????????????????????+??????
                    .build();
            intent.setOperation(operation);
            startAbility(intent);
        });

        //GPA??????
        gpa = (Image) findComponentById(ResourceTable.Id_iv_grid_4);
        gpa.setClickedListener(component -> {
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.ncepu")//?????????????????????
                    .withAbilityName("com.example.ncepu.Student.Query.GPAAbility")//?????????????????????+??????
                    .build();
            intent.setOperation(operation);
            startAbility(intent);
        });

        //??????????????????
        cult = (Image) findComponentById(ResourceTable.Id_iv_grid_6);
        cult.setClickedListener(component -> {
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.ncepu")//?????????????????????
                    .withAbilityName("com.example.ncepu.Student.Query.ScorePdfAbility")//?????????????????????+??????
                    .build();
            intent.setOperation(operation);
            startAbility(intent);
        });

        //??????
        csdn = (DependentLayout) findComponentById(ResourceTable.Id_csdn);
        csdn.setClickedListener(component -> {
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.ncepu")//?????????????????????
                    .withAbilityName("com.example.ncepu.Student.User.CSDNAbility")//?????????????????????+??????
                    .build();
            intent.setOperation(operation);
            startAbility(intent);
        });

        //????????????
        logout = (DependentLayout) findComponentById(ResourceTable.Id_logout);
        logout.setClickedListener(component -> {
            //?????????????????????
            ListDialog listDialog = new ListDialog(this);
            String []items = {""};
            listDialog.setItems(items);
            listDialog.setBackColor(Color.getIntColor("#E8E8C1"));
            listDialog.setTitleText("?????????????????????????");
            listDialog.setButton(0, "??????", (iDialog, i) -> listDialog.destroy());
            listDialog.setButton(1, "??????", new IDialog.ClickedListener() {
                @Override
                public void onClick(IDialog iDialog, int i) {
                    //????????????????????????????????????????????????
                    PreferenceUtils.getInstance().delete();
                    Intent intent = new Intent();
                    Operation operation = new Intent.OperationBuilder()
                            .withDeviceId("")
                            .withBundleName("com.example.ncepu")//?????????????????????
                            .withAbilityName("com.example.ncepu.MainAbility")//?????????????????????+??????
                            .build();
                    intent.setOperation(operation);
                    startAbility(intent);
                }
            });
            listDialog.setAlignment(LayoutAlignment.CENTER);
            listDialog.setOnSingleSelectListener((iDialog, i) -> {
                listDialog.destroy();
            });
            listDialog.show();
        });

        //??????
        dept = (DependentLayout) findComponentById(ResourceTable.Id_dept);
        dept.setClickedListener(component -> {
            Text text = (Text) findComponentById(ResourceTable.Id_right_desc_1);
            ToastUtil.showMessage(getContext(), text.getText());
        });

        //??????
        major = (DependentLayout) findComponentById(ResourceTable.Id_major);
        major.setClickedListener(component -> {
            Text text = (Text) findComponentById(ResourceTable.Id_right_desc_2);
            ToastUtil.showMessage(getContext(), text.getText());
        });

        //????????????
        year = (DependentLayout) findComponentById(ResourceTable.Id_year);
        year.setClickedListener(component -> {
            Text text = (Text) findComponentById(ResourceTable.Id_right_desc_3);
            ToastUtil.showMessage(getContext(), text.getText());
        });

        //??????
        id = (DependentLayout) findComponentById(ResourceTable.Id_id);
        id.setClickedListener(component -> {
            Text text = (Text) findComponentById(ResourceTable.Id_right_desc_4);
            ToastUtil.showMessage(getContext(), text.getText());
        });

        //??????
        sex = (DependentLayout) findComponentById(ResourceTable.Id_sex);
        sex.setClickedListener(component -> {
            Text text = (Text) findComponentById(ResourceTable.Id_right_desc_5);
            ToastUtil.showMessage(getContext(), text.getText());
        });

        //????????????
        motto = (DependentLayout) findComponentById(ResourceTable.Id_motto);
        motto.setClickedListener(component -> {
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.ncepu")//?????????????????????
                    .withAbilityName("com.example.ncepu.Student.User.SignatureAbility")//?????????????????????+??????
                    .build();
            intent.setOperation(operation);
            startAbility(intent);
        });

        //????????????
        ver = (DependentLayout) findComponentById(ResourceTable.Id_ver);
        ver.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                ToastUtil.showMessage(getContext(), "?????????????????????!");
            }
        });

    }

    private void initPageSlider() {
        pageSlider = (PageSlider) findComponentById(ResourceTable.Id_vpager);
        layoutScatter = LayoutScatter.getInstance(getContext());
        query = (DirectionalLayout) layoutScatter.parse(ResourceTable.Layout_ability_query_fragment, null, false);
        user = (DirectionalLayout) layoutScatter.parse(ResourceTable.Layout_ability_user_fragment, null, false);
        pageViews = new ArrayList<>();
        pageViews.add(query);
        pageViews.add(user);
        //??????provider
        pageSlider.setProvider(new TestPageProvider(pageViews, this));
        //??????????????????
        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            @Override
            public void onPageSliding(int i, float v, int i1) {

            }

            @Override
            public void onPageSlideStateChanged(int i) {

            }

            @Override
            public void onPageChosen(int i) {
                ((RadioButton) radioContainer.getComponentAt(i)).setChecked(true);
            }
        });
        radioContainer.setMarkChangedListener((radioContainer, i) -> pageSlider.setCurrentPage(i));
    }
}
