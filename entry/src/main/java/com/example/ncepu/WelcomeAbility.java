package com.example.ncepu;

import com.example.ncepu.JWUtils.ConnectJWGL;
import com.example.ncepu.Utils.PreferenceUtils;
import com.example.ncepu.Utils.ToastUtil;
import com.example.ncepu.slice.MainAbilitySlice;
import com.example.ncepu.slice.WelcomeAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeAbility extends Ability {

    private Preferences preferences;
    public static String stu_name, stu_class, stu_id;
    private DatabaseHelper databaseHelper;
    private Context context;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(WelcomeAbilitySlice.class.getName());
        super.setUIContent(ResourceTable.Layout_ability_welcome);
        init();
    }

    public void init() {
        context = MyApplication.context;
        databaseHelper = new DatabaseHelper(context);
        String name = "user_info";
        preferences = databaseHelper.getPreferences(name);
//        new ToastDialog(getContext()).setText(PreferenceUtils.getInstance().getString("id", "tttttt")).show();
        if(PreferenceUtils.getInstance().getString("id", null) == null) {
            new ToastDialog(getContext()).setText("请先进行登录!").setAlignment(LayoutAlignment.CENTER).show();
//            ToastUtil.showMessage(getContext(), "请先进行登录!");
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.ncepu")//这个必须填包名
                    .withAbilityName("com.example.ncepu.MainAbility")//这个必须填包名+类名
                    .build();
            intent.setOperation(operation);
            Timer timer=new Timer();
            TimerTask timerTask=new TimerTask() {
                @Override
                public void run() {
                    startAbility(intent);
                }
            };
            timer.schedule(timerTask,2000);
        }else {
            try {
                Timer timer=new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        String _id = preferences.getString("id", "");
                        String password = preferences.getString("in_net", "");
                        String password1 = preferences.getString("jw_password", "");
                        stu_class = preferences.getString("class", "");
                        stu_id = preferences.getString("id", "");
                        stu_name = preferences.getString("name", "");
                        try {
                            MainAbility.connectJWGL = new ConnectJWGL(_id, password, password1, WelcomeAbility.this);
                            MainAbility.connectJWGL.init();
                            MainAbility.connectJWGL.beginLogin();
                            String cookies = MainAbility.connectJWGL.cookies.toString();
                            String cookies_in = MainAbility.connectJWGL.cookies_innet.toString();
                            PreferenceUtils.getInstance().putString("cookies", cookies);
                            PreferenceUtils.getInstance().putString("cookies_in", cookies_in);
                            Intent intent = new Intent();
                            Operation operation = new Intent.OperationBuilder()
                                    .withDeviceId("")
                                    .withBundleName("com.example.ncepu")//这个必须填包名
                                    .withAbilityName("com.example.ncepu.Student.StudentMainAbility")//这个必须填包名+类名
                                    .build();
                            intent.setOperation(operation);
                            startAbility(intent);
                        } catch (Exception e) {
                            /*runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showMessage(WelcomeActivity.this, "内网连接超时,请检查网络是否正常连接或内网是否能正常访问!");
                                    new Handler().postDelayed(() -> {
                                        Intent home = new Intent(Intent.ACTION_MAIN);
                                        home.addCategory(Intent.CATEGORY_HOME);
                                        startActivity(home);
                                    }, 3000);
                                    e.printStackTrace();
                                }
                            });*/
                        }
                    }
                };
                timer.schedule(timerTask,1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
