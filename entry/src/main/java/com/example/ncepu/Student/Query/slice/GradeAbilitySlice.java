package com.example.ncepu.Student.Query.slice;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Utils.ToastUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.multimodalinput.event.KeyEvent;
import ohos.utils.IntentConstants;

import java.util.HashSet;
import java.util.Set;

public class GradeAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_grade);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
//        if(keyCode == KeyEvent.KEY_BACK) {
//            ToastUtil.showMessage(this, "hhh");
//            Intent intent = new Intent();
//            Set<String> st = new HashSet<>();
//            st.add(Intent.ENTITY_HOME);
//            Operation operation = new Intent.OperationBuilder()
//                    .withDeviceId("")
//                    .withBundleName("com.example.ncepu")//包名
//                    .withAction(IntentConstants.ACTION_HOME)
//                    .withEntities(st)
//                    .build();
//            intent.setOperation(operation);
//            startAbility(intent);
//            return true;
//        }
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
}
