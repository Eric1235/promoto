package scun2016.com.promoto.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import scun2016.com.promoto.R;
import scun2016.com.promoto.util.DensityUtil;

/**
 * Created by EricLi.
 * on 2017/4/11 in 上午10:40
 * Email: EricLi1235@gmial.com
 */

public class NumberKeyboardDialog{

    /**
     * 弹出数字键盘弹窗
     * @param context
     * @param listener
     */
    public static void sowNumberDialog(Context context, final OnNumberConfirmListener listener){
        final StringBuffer result = new StringBuffer();
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_number);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = DensityUtil.dip2px(context,300.0f);
        lp.height = DensityUtil.dip2px(context,450.0f);
        window.setAttributes(lp);
        NumberKeyboardView numberKeyboardView = (NumberKeyboardView)
                window.findViewById(R.id.number_view);
        final TextView tvContent = (TextView) window.findViewById(R.id.tv_content);
        final Button btnDelete = (Button) window.findViewById(R.id.btn_delete);
        Button btnCancel = (Button) window.findViewById(R.id.btn_cancel);
        final Button btnConfirm = (Button) window.findViewById(R.id.btn_confirm) ;

        btnConfirm.setTextColor(Color.GRAY);
        numberKeyboardView.setOnNumberClickListener(new NumberKeyboardView.OnNumberClickListener() {
            @Override
            public void onNumberReturn(String number) {
                result.append(number);

                if (result.length() > 0){
                    btnConfirm.setEnabled(true);
                    btnConfirm.setTextColor(Color.WHITE);
                    btnDelete.setEnabled(true);
                }

                tvContent.setText(result.toString());
            }

            @Override
            public void onNumberDelete() {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.getNumber(result.toString());
                    dialog.dismiss();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result.length() > 0){
                    result.delete(result.length() - 1, result.length());
                    tvContent.setText(result.toString());
                }

                if (result.length() == 0){
                    btnDelete.setEnabled(false);
                    btnConfirm.setTextColor(Color.GRAY);
                    btnConfirm.setEnabled(false);
                }
            }
        });

    }
}
