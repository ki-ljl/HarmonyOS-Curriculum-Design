package com.example.ncepu.Student.User;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Student.User.slice.UserFragmentSlice;
import com.example.ncepu.Utils.ToastUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class UserFragment extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(UserFragmentSlice.class.getName());
        super.setUIContent(ResourceTable.Layout_ability_user_fragment);
        addActionRoute(Intent.ACTION_QUERY_WEATHER, SignatureAbility.class.getName());
    }

    @Override
    protected void onAbilityResult(int requestCode, int resultCode, Intent resultData) {
        if(resultData == null) {
            return;
        }
        if(requestCode == 110) {
            String mod = resultData.getStringParam("sign");
//            mod = PreferenceUtils.getInstance().getString("sign", "");
            ToastUtil.showMessage(this, "hhh");
//            real_sign.setText(mod);
//            pageSlider.setCurrentPage(1);
        }
        super.onAbilityResult(requestCode, resultCode, resultData);
    }
}
