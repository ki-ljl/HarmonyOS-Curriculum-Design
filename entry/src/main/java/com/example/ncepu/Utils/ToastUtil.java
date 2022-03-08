package com.example.ncepu.Utils;

import ohos.agp.components.TableLayout;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;

public class ToastUtil {
    public static ToastDialog toastDialog;
    public static void showMessage(Context context, String text) {
        if(toastDialog == null) {
            toastDialog = new ToastDialog(context);
        }else {
            toastDialog.setText(text);
        }
        toastDialog.setDuration(2000);
        toastDialog.setAlignment(LayoutAlignment.CENTER);
        toastDialog.show();
    }
}
