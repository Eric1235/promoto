package scun2016.com.promoto.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:43
 * Email: EricLi1235@gmial.com
 */

/**
 * 自定义底部栏
 */
public class ViewIndicator extends LinearLayout implements View.OnClickListener {

    public ViewIndicator(Context context) {
        super(context);
    }

    public ViewIndicator(Context context,
            @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewIndicator(Context context,
            @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {

    }

    class ViewIndicatorParam{

    }
}
