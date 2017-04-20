package scun2016.com.promoto.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import scun2016.com.promoto.R;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午11:18
 * Email: EricLi1235@gmial.com
 */

public class CenterIndicator extends View {

    private static final int STATUS_NONE = 1;//未开始状态
    private static final int STATUS_WORKING = 2;//倒计时状态
    private static final int STATUS_FINISHED = 3;//完成状态

    private int mProgress;//进度是0到100
    private int mRadius;

    private int mStatus;//未开始，正在倒计时，结束

    private float mRoundDegree;//弧度

    private int mBackgroundColor;
    private int mColor;
    private int mWidth;
    private int mHeight;
    private Paint mBackgroundPaint;
    private Paint mPaint;
    private Paint mCirclePaint;
    private Paint mTrianglePaint;
    private Path mTickPath;//钩钩的路径
    private Path mTrianglePath;//三角形的路径

    private OnCenterClickListener mOnCenterClickListener;//点击事件回调


    public CenterIndicator(Context context) {
        this(context, null);
    }

    public CenterIndicator(Context context,
            @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CenterIndicator(Context context,
            @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet, int def){

        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.CenterIndicator);
        mBackgroundColor = a.getColor(R.styleable.CenterIndicator_color_background, Color.RED);
        mColor = a.getColor(R.styleable.CenterIndicator_color_center, Color.WHITE);
        mRoundDegree = a.getDimension(R.styleable.CenterIndicator_round_degree, 10.0f);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setDither(true);
        mBackgroundPaint.setColor(mBackgroundColor);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);

        mTrianglePaint = new Paint();
        mTrianglePaint.setAntiAlias(true);
        mTrianglePaint.setDither(true);
        mTrianglePaint.setStyle(Paint.Style.FILL);
        mTrianglePaint.setColor(mColor);

        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);
        mCirclePaint.setColor(mColor);

        mTickPath = new Path();
        mTrianglePath = new Path();

        a.recycle();

    }

    //不断更新进度
    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    //设置按钮状态
    public void setStatus(int status){
        mStatus = status;
        invalidate();
    }

    //当activity退出，或者当前View被remove的时候，会调用这个方法。如果View有线程或者动画，应该在此方法里面清空
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (wMode){
            case MeasureSpec.AT_MOST:
                wSize = 100;
                break;
            //这种情况，一般是用于系统内部。普通的view测量，没有这种模式
//            case MeasureSpec.UNSPECIFIED:
//                wSize = 100;
//                break;
            case MeasureSpec.EXACTLY:
                wSize = wSize - getPaddingLeft() - getPaddingRight();
                break;
        }
        switch (hMode){
            case MeasureSpec.AT_MOST:
                hSize = 50;
                break;
//            case MeasureSpec.UNSPECIFIED:
//                hSize = 50;
//                break;
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
        mRadius = mHeight / 3;
        mTrianglePath.reset();
        mTickPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mOnCenterClickListener != null){
            mOnCenterClickListener.onCenterClick(mStatus);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        switch (mStatus){
            case STATUS_NONE:
                drawTriangle(canvas);
                break;
            case STATUS_WORKING:
                drawTiming(canvas);
                break;
            case STATUS_FINISHED:
                drawTick(canvas);
                break;
        }

//        drawTiming(canvas);
//        drawTriangle(canvas);
    }

    //绘制圆角背景
    private void drawBackground(Canvas canvas){
        RectF backgroundRect = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(backgroundRect, mRoundDegree, mRoundDegree, mBackgroundPaint);
    }

    //绘制三角形
    private void drawTriangle(Canvas canvas){
        int centerX = mWidth / 2;
        int centerY = mHeight / 2;
        int deltaX = mRadius / 2;
        int deltaY = (int)(mRadius * Math.sin(Math.toRadians(60.0)));
        mTrianglePath.moveTo(centerX - deltaX, centerY - deltaY);
        mTrianglePath.lineTo(centerX - deltaX, centerY + deltaY);
        mTrianglePath.lineTo(centerX + mRadius, centerY);
        mTrianglePath.close();
        canvas.drawPath(mTrianglePath, mPaint);

    }

    //绘制倒计时
    private void drawTiming(Canvas canvas){
        RectF rectF = new RectF(mWidth / 2 - mRadius, mHeight / 2 - mRadius,
                mWidth / 2 + mRadius, mHeight / 2 + mRadius);
        canvas.drawArc(rectF, 0, 360, false, mCirclePaint);
        float sweepAngle = mProgress * 3.6f;
        //记住，开始的角度是-90
        canvas.drawArc(rectF, -90, sweepAngle, true, mPaint);
    }

    //绘制完成的沟沟
    private void drawTick(Canvas canvas){
        int centerX = mWidth / 2;
        int centerY = mHeight / 2;
        Path path = new Path();
        path.moveTo(centerX - (centerX / 3), centerY - (centerY / 6));
        path.lineTo(centerX - (centerX / 6), centerY + (centerY / 3));
        path.lineTo(centerX + centerX / 3, centerY - (centerY / 3));
        canvas.drawPath(path, mCirclePaint);
    }

    //设置点击事件
    public void setOnCenterClickListener(
            OnCenterClickListener onCenterClickListener) {
        mOnCenterClickListener = onCenterClickListener;
    }

    //点击回调
    public interface OnCenterClickListener{
        void onCenterClick(int status);
    }
}

