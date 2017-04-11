package scun2016.com.promoto.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import scun2016.com.promoto.R;

/**
 * Created by EricLi.
 * on 2017/4/10 in 下午4:38
 * Email: EricLi1235@gmial.com
 * 自定义的弹出框工具类
 */

public class CustomAlertDialog {

    /**
     * 封装弹出框
     * @param context
     * @param title
     * @param content
     * @param positiveListener
     */
    public static void showAlertDialog(Context context,String title, String content,
            final DialogInterface.OnClickListener positiveListener){
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage(content)
                .setCancelable(false)
                .setTitle(title)
                .setPositiveButton(context.getText(R.string.upgrade), positiveListener)
                .setNegativeButton(context.getText(R.string.undo), null)
                .create();
        dialog.show();
    }


}
