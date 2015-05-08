package ui.parts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import viktoriia.vihriian.visitor.R;

/**
 * Created by Администратор on 05.05.2015.
 * Reusable alert class, so there is no need to write the alert code in the all activities.
 */
public class AlertDialogManager {
    /**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)
     *               - pass null if you don't want icon
     * */

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("Close",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        if(status != null) {
            builder.setIcon((status) ? R.mipmap.ic_success: R.mipmap.ic_fail);
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
 }
