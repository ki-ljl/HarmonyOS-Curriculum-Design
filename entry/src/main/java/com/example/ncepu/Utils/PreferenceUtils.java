package com.example.ncepu.Utils;

import com.example.ncepu.MyApplication;
import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;

public class PreferenceUtils {

    public static PreferenceUtils preferenceUtils = null;
    private static String name = "user_info";

    public static PreferenceUtils getInstance() {
        if(preferenceUtils == null) {
            preferenceUtils = new PreferenceUtils();
        }
        return preferenceUtils;
    }

    public Preferences getApplicationPref(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper.getPreferences(name);
    }

    public void putString(String key, String value) {
        preferenceUtils.getApplicationPref(MyApplication.context).putString(key, value).flushSync();
    }

    public String getString(String key, String defValue) {
        return preferenceUtils.getApplicationPref(MyApplication.context).getString(key, defValue);
    }

    public void delete() {
        DatabaseHelper databaseHelper = new DatabaseHelper(MyApplication.context);
        databaseHelper.deletePreferences(name);
    }
}
