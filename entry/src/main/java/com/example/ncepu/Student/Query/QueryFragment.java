package com.example.ncepu.Student.Query;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Student.Query.slice.QueryFragmentSlice;
import com.youth.banner.loader.ImageLoader;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueryFragment extends Ability {

    private Banner banner;
    private List<Integer> list;
    private List<String> titles;
    private TableLayout layout1, layout2;
    private Image cj;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(QueryFragmentSlice.class.getName());
        super.setUIContent(ResourceTable.Layout_ability_query_fragment);
        initViews();
        new ToastDialog(this).setText("hhhh").show();
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

    private void initViews() {
        layout1 = (TableLayout) findComponentById(ResourceTable.Id_table1);
        cj = (Image) findComponentById(ResourceTable.Id_iv_grid_1);
        cj.setClickedListener(component -> {
            new ToastDialog(this).setText("hhhh").show();
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.ncepu")//这个必须填包名
                    .withAbilityName("com.example.ncepu.Student.Query.GradeAbility")//这个必须填包名+类名
                    .build();
            intent.setOperation(operation);
            startAbility(intent);
        });
        layout1.setClickedListener(component -> {
            switch (component.getId()) {
                case ResourceTable.Id_ttt:
                    new ToastDialog(this).setText("hhhh").show();
                    Intent intent = new Intent();
                    Operation operation = new Intent.OperationBuilder()
                            .withDeviceId("")
                            .withBundleName("com.example.ncepu")//这个必须填包名
                            .withAbilityName("com.example.ncepu.Student.Query.GradeAbility")//这个必须填包名+类名
                            .build();
                    intent.setOperation(operation);
                    startAbility(intent);
                    break;
            }
        });
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
            titles.add(String.valueOf(i));
        }
        //设置参数
        banner.setImages(list)
                .setBannerTitles(titles)
                .setDelayTime(3000)
                .setBannerStyle(5)
                .setTitleTextSize(60);
        banner.start();
    }
}
