package com.example.ncepu;

import ohos.aafwk.ability.AbilityPackage;
import ohos.app.Context;

public class MyApplication extends AbilityPackage {

    public static Context context;

    @Override
    public void onInitialize() {
        super.onInitialize();
        context = this;
    }
}
