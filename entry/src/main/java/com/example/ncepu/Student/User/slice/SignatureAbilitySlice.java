package com.example.ncepu.Student.User.slice;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Student.User.SignatureAbility;
import com.example.ncepu.Student.slice.StudentMainAbilitySlice;
import com.example.ncepu.Utils.PreferenceUtils;
import com.example.ncepu.Utils.ToastUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.multimodalinput.event.KeyEvent;

public class SignatureAbilitySlice extends AbilitySlice {

    private Text affirm;
    private TextField textField;
    private String initStr;
    private Image image;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_signature);
        initViews();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if(keyCode == keyEvent.KEY_BACK) {
            String c_str = textField.getText();
            if(c_str.equals(initStr)) {
                onBackPressed();
            }else {
                ToastUtil.showMessage(this, "请点击确认!");
            }
            return true;
        }
        return super.onKeyDown(keyCode, keyEvent);
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
        image = (Image) findComponentById(ResourceTable.Id_ib_back_sig);
        image.setClickedListener(component -> {
            String c_str = textField.getText();
            if(c_str.equals(initStr)) {
                onBackPressed();
            }else {
                ToastUtil.showMessage(getContext(), "请点击确认!");
            }
        });
        affirm = (Text) findComponentById(ResourceTable.Id_affirm);
        textField = (TextField) findComponentById(ResourceTable.Id_editText);
        textField.setText(PreferenceUtils.getInstance().getString("sign", ""));
        initStr = PreferenceUtils.getInstance().getString("sign", "");
        affirm.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                String str = textField.getText();
                if(str.equals("")) {
                    ToastUtil.showMessage(getContext(),"请输入内容!!");
                }else {
                    //更新个人签名
                    PreferenceUtils.getInstance().putString("sign", str);
                    //回传数据
                    Intent intent = new Intent();
                    intent.setParam("sign", str);
                    present(new StudentMainAbilitySlice(), intent);
                    ToastUtil.showMessage(getContext(), "修改成功！");
                }
            }
        });
    }
}
