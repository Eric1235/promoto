package scun2016.com.promoto.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import scun2016.com.promoto.R;

/**
 * Created by EricLi.
 * on 2017/4/10 in 下午5:26
 * Email: EricLi1235@gmial.com
 */

public class NumberKeyboardView extends View{

    private Paint mPaint;
    private Bitmap mBpDelete;

//    private float cliclX, clickY;
    private float mWidth, mHeight;
    private float mRectWidth, mRectHeight;
    private float mWidthOfBp, mHeightOfBp;
    private boolean isInit = false;

    private String number;//被点击的数字

    private float[] xs = new float[3];
    private float[] ys = new float[4];
//    private float x1, x2, y1, y2;

    private int type;

    private OnNumberClickListener mOnNumberClickListener;


    public NumberKeyboardView(Context context) {
        super(context);
    }

    public NumberKeyboardView(Context context,
            @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberKeyboardView(Context context,
            @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initData(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWidth = getWidth();
        mHeight = getHeight();
        mBpDelete = BitmapFactory.decodeResource(getResources(), R.drawable.checkbox);
        mWidthOfBp = mBpDelete.getWidth();
        mHeightOfBp = mBpDelete.getHeight();

        mRectWidth = (mWidth - 40) / 3;
        mRectHeight = (mHeight - 100) / 4;

        xs[0] = mRectWidth / 2;
        xs[1] = (mRectWidth * 3) / 2 + 10;
        xs[2] = (mRectWidth * 5) / 2 + 20;

        ys[0] = (mRectHeight / 2) + 25 ;
        ys[1] =  (mRectHeight * 3) / 2 + 35 ;
        ys[2] = (mRectHeight * 5) / 2 + 45 ;
        ys[3] = (mRectHeight * 7) / 2 + 55 ;

        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit){
            initData();
        }
        drawKeyboard(canvas);

    }

    private void drawKeyboard(Canvas canvas){
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(80);
        mPaint.setStrokeWidth(2);
        canvas.drawColor(Color.BLACK);
        canvas.drawText("1", xs[0], ys[0], mPaint);
        canvas.drawText("2", xs[1], ys[0], mPaint);
        canvas.drawText("3", xs[2], ys[0], mPaint);

        canvas.drawText("4", xs[0], ys[1], mPaint);
        canvas.drawText("5", xs[1], ys[1], mPaint);
        canvas.drawText("6", xs[2], ys[1], mPaint);

        canvas.drawText("7", xs[0], ys[2], mPaint);
        canvas.drawText("8", xs[1], ys[2], mPaint);
        canvas.drawText("9", xs[2], ys[2], mPaint);


        canvas.drawText("0", xs[1], ys[3], mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                handleDown(x, y);
                return true;
            case MotionEvent.ACTION_UP:
                return true;
            case MotionEvent.ACTION_CANCEL:
                return true;
        }
        return false;
    }

    private void setDefault(){
        number = null;
    }

    private void handleDown(float x, float y) {
        if (y < 0){
            return;
        }
        //第一列
        if (x >= 10 && x + 10 < mRectWidth) {
            //第一排
            if (y >= 0 && y <= mRectHeight + 10){
                number = "1";
            } else if (y > mRectHeight + 10 && y <= 2 * mRectHeight + 20){
                number = "3";
            } else if (y > 2 + mRectHeight + 30 && y <= 3 * mRectHeight + 30){
                number = "5";
            }
        } else if (x > 20 + mRectWidth && x <= 20 + 2 * mRectWidth){
            if (y >= 0 && y <= mRectHeight + 10){
                number = "2";
            } else if (y > mRectHeight + 10 && y <= 2 * mRectHeight + 20){
                number = "4";
            } else if (y > 2 + mRectHeight + 30 && y <= 3 * mRectHeight + 30){
                number = "6";
            } else {
                number = "0";
            }
        } else if (x > 30 + mRectWidth * 2 && x <= mRectWidth * 3 + 30){
            if (y >= 0 && y <= mRectHeight + 10){
                number = "3";
            } else if (y > mRectHeight + 10 && y <= 2 * mRectHeight + 20){
                number = "6";
            } else if (y > 2 + mRectHeight + 30 && y <= 3 * mRectHeight + 30){
                number = "9";
            }
        }

        if (mOnNumberClickListener != null && number != null){
            mOnNumberClickListener.onNumberReturn(number);
        }
    }

    public void setOnNumberClickListener(
            OnNumberClickListener onNumberClickListener) {
        mOnNumberClickListener = onNumberClickListener;
    }

    public interface OnNumberClickListener{
        void onNumberReturn(String number);

        void onNumberDelete();
    }
}
