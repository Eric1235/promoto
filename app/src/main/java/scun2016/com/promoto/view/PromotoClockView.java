package scun2016.com.promoto.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
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
    private int mProgressAngle;

    private String content;

    private int mWidth;
    private int mHeight;
    private int mRadius;
    private int mStrokeWidth;
    private int mCenter;

    private Paint mPaint;
    private Paint mBackgroundPaint;
    private Paint mTrianglePaint;
    private Paint mTextPaint;
    private Path mTrianglePath;

    public void setState(int state) {
        mState = state;
        invalidate();
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    public PromotoClockView(Context context) {
        this(context, null);
    }

    public PromotoClockView(Context context,
            @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PromotoClockView(Context context,
            @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mState = 1;
        mStrokeWidth = 20;
        mProgressAngle = 100;
        content = "25:00";
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setDither(true);
        mBackgroundPaint.setColor(Color.BLACK);
        mBackgroundPaint.setStyle(Paint.Style.STROKE);
        mBackgroundPaint.setStrokeWidth(mStrokeWidth);

        mTrianglePaint = new Paint();
        mTrianglePaint.setAntiAlias(true);
        mTrianglePaint.setDither(true);
        mTrianglePaint.setColor(Color.WHITE);
        mTrianglePaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(80);

        mTrianglePath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (wMode){
            case MeasureSpec.AT_MOST:
                wSize = 200;
                break;
            //这种情况，一般是用于系统内部。普通的view测量，没有这种模式
            case MeasureSpec.EXACTLY:
                wSize = wSize - getPaddingLeft() - getPaddingRight();
                break;
        }
        switch (hMode){
            case MeasureSpec.AT_MOST:
                hSize = 20;
                break;

            case MeasureSpec.EXACTLY:
                hSize = hSize - getPaddingTop() - getPaddingBottom();
                break;
        }

        setMeasuredDimension(wSize, hSize);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = (mHeight - mStrokeWidth) / 2;
        mCenter = mWidth / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mState){
            case STATE_NORMAL:
                drawRoundBack(canvas,-90, 360);
                drawTriangle(canvas);
                break;
            case STATE_WORKING:
                drawRoundBack(canvas,mProgressAngle - 90, 360 - mProgressAngle);
                drawProgress(canvas, mProgressAngle);
                drawText(canvas);
                break;
        }
    }

    //停止时候的三角形
    private void drawTriangle(Canvas canvas){
        int centerX = mWidth / 2;
        int centerY = mHeight / 2;
        int width = mRadius / 2;
        int deltaX = width / 2;
        int deltaY = (int)(width * Math.sin(Math.toRadians(60.0)));
        mTrianglePath.moveTo(centerX - deltaX, centerY - deltaY);
        mTrianglePath.lineTo(centerX - deltaX, centerY + deltaY);
        mTrianglePath.lineTo(centerX + width, centerY);
        mTrianglePath.close();
        canvas.drawPath(mTrianglePath, mTrianglePaint);
    }

    //进度
    private void drawProgress(Canvas canvas, int sweepAngle){
        RectF rectF = new RectF(0 + mStrokeWidth, 0 + mStrokeWidth,
                mWidth - mStrokeWidth, mHeight - mStrokeWidth);
        canvas.drawArc(rectF, -90, sweepAngle, false, mPaint);
    }

    //灰色圆圈背景
    private void drawRoundBack(Canvas canvas, int startAngle, int sweepAngle){
        RectF rectF = new RectF(0 + mStrokeWidth, 0 + mStrokeWidth,
                mWidth - mStrokeWidth, mHeight - mStrokeWidth);
        canvas.drawArc(rectF, startAngle, sweepAngle, false, mBackgroundPaint);
    }

    //文字
    private void drawText(Canvas canvas){
        Rect bound = new Rect();
        //测量文字大小，然后进行居中绘制
        mTextPaint.getTextBounds(content,0, content.length(), bound);
        float startX = mCenter - bound.width() / 2;
        float startY = mCenter + bound.height() / 2;
        canvas.drawText(content, startX, startY, mTextPaint);
    }
}
