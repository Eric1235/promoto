package scun2016.com.promoto.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import scun2016.com.promoto.R;

/**
 * Created by EricLi.
 * on 2017/3/29 in 下午9:42
 * Email: EricLi1235@gmial.com
 */

public class CustomDialog extends AlertDialog{

    private Context mContext;

    public CustomDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public CustomDialog(@NonNull Context context,
            @StyleRes int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    public CustomDialog(@NonNull Context context,
            boolean cancelable,
            @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    //应该去隐藏键盘
    @Override
    public void dismiss() {
        super.dismiss();
    }

    private void init(){
        View view = View.inflate(mContext, R.layout.activity_add_promoto, null);
        setContentView(view);
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) mContext).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.TOP);//设置对话框位置
        params.width = LinearLayout.LayoutParams.MATCH_PARENT; // 宽度设置为屏幕的0.65，根据实际情况调整
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(params);
    }
}
