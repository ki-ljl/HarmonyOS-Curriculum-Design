package com.example.ncepu.Student.Query.slice;

import com.example.ncepu.ResourceTable;
import com.youth.banner.Banner;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueryFragmentSlice extends AbilitySlice {

    private Banner banner;
    private List<Integer> list;
    private List<String> titles;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_query_fragment);
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

    private void initBanner() throws NotExistException, WrongTypeException, IOException {
        banner = (Banner) findComponentById(ResourceTable.Id_banner);
        list = new ArrayList<>();
        titles = new ArrayList<>();
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

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
