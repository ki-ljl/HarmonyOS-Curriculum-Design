package com.example.ncepu.Student.User;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Student.User.slice.SignatureAbilitySlice;
import com.example.ncepu.Utils.PreferenceUtils;
import com.example.ncepu.Utils.ToastUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.multimodalinput.event.KeyEvent;


public class SignatureAbility extends Ability {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(SignatureAbilitySlice.class.getName());
        super.setUIContent(ResourceTable.Layout_ability_signature);
    }
}
