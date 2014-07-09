
package com.iplus.edu.jlpt_voc.utils;

import android.app.Activity;
import android.app.ProgressDialog;

public class Loading {
    private static ProgressDialog dialog;

    public static void show(final Activity activity) {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Runnning");
        dialog.setMessage("Now Loading...                    ");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
    }

    public static void hide() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
