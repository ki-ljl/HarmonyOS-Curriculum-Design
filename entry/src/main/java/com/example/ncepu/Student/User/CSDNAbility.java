package com.example.ncepu.Student.User;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Student.User.slice.CSDNAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.webengine.Navigator;
import ohos.agp.components.webengine.ResourceRequest;
import ohos.agp.components.webengine.WebAgent;
import ohos.agp.components.webengine.WebView;
import ohos.multimodalinput.event.KeyEvent;
import ohos.utils.net.Uri;

public class CSDNAbility extends Ability {

    private WebView webView;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(CSDNAbilitySlice.class.getName());
        super.setUIContent(ResourceTable.Layout_ability_csdn);
        initViews();
    }

    private void initViews() {
        webView = (WebView) findComponentById(ResourceTable.Id_web_view);
        webView.getWebConfig().setJavaScriptPermit(true);  // 如果网页需要使用JavaScript，增加此行；如何使用JavaScript下文有详细介绍
        webView.getWebConfig().setLoadsImagesPermit(true);
        webView.getWebConfig().setWebStoragePermit(true);
        webView.getWebConfig().setViewPortFitScreen(true);
        webView.setWebAgent(new MyWebAgent());
        final String url = "https://blog.csdn.net/Cyril_KI"; // EXAMPLE_URL由开发者自定义
        webView.load(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        Navigator navigator = webView.getNavigator();
        if(keyCode == keyEvent.KEY_BACK && navigator.canGoBack()) {
            navigator.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, keyEvent);
    }

    class MyWebAgent extends WebAgent {
        public static final String EXAMPLE_URL = "...";
        @Override
        public boolean isNeedLoadUrl(WebView webview, ResourceRequest request) {
            if (request == null || request.getRequestUrl() == null) {
                return false;
            }
            Uri uri = request.getRequestUrl();
            // EXAMPLE_URL由开发者自定义
            if (uri.getDecodedHost().equals(EXAMPLE_URL)) {
                // 增加开发者自定义逻辑
                return false;
            } else {
                return super.isNeedLoadUrl(webview, request);
            }
        }
    }
}
