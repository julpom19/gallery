package codewizards.com.ua.gallery.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by User on 22.02.2017.
 */

public class DialogHelper {
    public static void openMessageDialog(Context context, String title, String msg) {
        String ok = "OK";
        final AlertDialog.Builder adb = new AlertDialog.Builder(context)
                .setTitle(title).setPositiveButton(ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setMessage(msg);
        adb.show();
    }
}
