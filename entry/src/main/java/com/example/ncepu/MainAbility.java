package com.example.ncepu;

import com.example.ncepu.JWUtils.ConnectJWGL;
import com.example.ncepu.Utils.PreferenceUtils;
import com.example.ncepu.Utils.TimeUtils;
import com.example.ncepu.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.Button;
import ohos.agp.components.TextField;
import ohos.agp.utils.Color;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;

import java.util.Map;

public class MainAbility extends Ability {

    public static ConnectJWGL connectJWGL;
    private Button login;
    private TextField textId, textPwd1, textPwd2;
    private Preferences preferences;
    private DatabaseHelper databaseHelper;
    public static Context mcontext;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        super.setUIContent(ResourceTable.Layout_ability_main);
        mcontext = getContext();
        initViews();
    }

    public void initViews() {
        login = (Button) findComponentById(ResourceTable.Id_main_btn_login);
        textId = (TextField) findComponentById(ResourceTable.Id_usersid);
        textPwd1 = (TextField) findComponentById(ResourceTable.Id_password);
        textPwd2 = (TextField) findComponentById(ResourceTable.Id_password1);
        login.setClickedListener(component -> {
            if(!TimeUtils.isFastClick()) {
                new ToastDialog(getContext()).setText("正在处理,请不要频繁点击!").show();
                return;
            }
            //设置颜色
            login.setTextColor(new Color(Color.getIntColor("#7adfb8")));
            String _id= textId.getText();
            String password=textPwd1.getText().toString();
            String password1 = textPwd2.getText().toString();
            if(_id.equals("")) {
                new ToastDialog(getContext()).setText("学号不能为空").show();
            }else if(password.equals("")) {
                new ToastDialog(getContext()).setText("教务系统密码不能为空!").show();
            }else if(password1.equals("")) {
                new ToastDialog(getContext()).setText("内网密码不能为空!").show();
            }else {
                new Thread(() -> {
                    try{
                        login(_id, password1, password);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }).start();//线程启动
            }
        });
    }

    private void login(String _id, String password1, String password) {
        try {
            connectJWGL = new ConnectJWGL(_id, password1, password, MainAbility.this);
            //内网登录密码错误，登录失败
            if(connectJWGL.init() == 0) {
                new ToastDialog(getContext()).setText("内网密码错误!").show();
//                runOnUiThread(() -> ToastUtil.showMessage(MainActivity.this, "内网密码错误！"));
            }else {
                if(MainAbility.connectJWGL.beginLogin()) {
                    Map<String, String> info_map = connectJWGL.getStudentInformation();
                    PreferenceUtils.getInstance().putString("id", _id);
                    PreferenceUtils.getInstance().putString("in_net", password1);
                    PreferenceUtils.getInstance().putString("sign", "Hungry And Humble");
                    PreferenceUtils.getInstance().putString("jw_password", password);
                    PreferenceUtils.getInstance().putString("name", info_map.get("name"));
                    PreferenceUtils.getInstance().putString("stu_id", info_map.get("id"));
                    PreferenceUtils.getInstance().putString("class", info_map.get("class"));
                    PreferenceUtils.getInstance().putString("major", info_map.get("major"));
                    PreferenceUtils.getInstance().putString("sex", info_map.get("sex"));
                    PreferenceUtils.getInstance().putString("dept", info_map.get("dept"));
                    PreferenceUtils.getInstance().putString("year", info_map.get("year"));

                    String cookies = connectJWGL.cookies.toString();
                    String cookies_in = connectJWGL.cookies_innet.toString();
                    PreferenceUtils.getInstance().putString("cookies", cookies);
                    PreferenceUtils.getInstance().putString("cookies_in", cookies_in);
//                    preferences.flush();
                    Intent intent = new Intent();
                    Operation operation = new Intent.OperationBuilder()
                            .withDeviceId("")
                            .withBundleName("com.example.ncepu")//这个必须填包名
                            .withAbilityName("com.example.ncepu.Student.StudentMainAbility")//这个必须填包名+类名
                            .build();
                    intent.setOperation(operation);
                    // 通过AbilitySlice的startAbility接口实现启动另一个页面
                    startAbility(intent);
//                    runOnUiThread(() -> ToastUtil.showMessage(MainActivity.this, "登录成功！"));

                }else {
                    new ToastDialog(getContext()).setText("教务系统密码错误!").show();
//                    runOnUiThread(() -> ToastUtil.showMessage(MainActivity.this, "教务系统密码错误！"));
                }
            }

        } catch (Exception e) {
//            runOnUiThread(() -> ToastUtil.showMessage(MainActivity.this, "内网连接超时,请检查网络是否正常连接或内网是否能正常访问!"));
            new ToastDialog(getContext()).setText("内网连接超时,请检查网络是否正常连接或内网是否能正常访问!").show();
            e.printStackTrace();
        }

    }
}
