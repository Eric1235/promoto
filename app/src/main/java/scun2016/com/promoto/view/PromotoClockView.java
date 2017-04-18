package scun2016.com.promoto.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by EricLi.
 * on 2017/4/18 in 下午3:09
 * Email: EricLi1235@gmial.com
 */

public class PromotoClockView extends View {

    public static final int STATE_NORMAL = 0;
    public static final int STATE_WORKING = 1;

    private int mState;

    private int mProgress;

    private String content;

    private int mWidth;
    private int mHeight;

    public void setState(int state) {
        mState = state;
        invalidate();
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    public PromotoClockView(Context context) {
        super(context);
    }

    public PromotoClockView(Context context,
            @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PromotoClockView(Context context,
            @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
