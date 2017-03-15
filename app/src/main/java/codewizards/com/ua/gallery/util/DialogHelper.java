package codewizards.com.ua.gallery.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import codewizards.com.ua.gallery.R;

/**
 * Created by User on 22.02.2017.
 */

public class DialogHelper {
    public static void openMessageDialog(Context context, String title, String msg) {
        String ok = context.getString(R.string.dialog_ok);
        final AlertDialog.Builder adb = new AlertDialog.Builder(context)
                .setTitle(title).setPositiveButton(ok, (dialog, which) -> dialog.dismiss())
                .setMessage(msg);
        adb.show();
    }

    public static void openConfirmationDialog(Context context, String title, String msg, String confirmButton, DialogInterface.OnClickListener positiveAction) {
        String cancel = context.getString(R.string.dialog_cancel);
        final AlertDialog.Builder adb = new AlertDialog.Builder(context)
                .setTitle(title)
                .setPositiveButton(confirmButton, positiveAction)
                .setNegativeButton(cancel, (dialog, which) -> dialog.dismiss())
                .setMessage(msg);
        adb.show();
    }
}
