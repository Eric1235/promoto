package scun2016.com.promoto.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午11:18
 * Email: EricLi1235@gmial.com
 */

public class CenterIndicator extends View {

    private static final int STATUS_NONE = 1;
    private static final int STATUS_WORKING = 2;
    private static final int STATUS_FINISHED = 3;

    private int mProgress;//进度是0到100
    private int mRadius;

    private int mStatus;//未开始，正在倒计时，结束

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
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setDither(true);
        mBackgroundPaint.setColor(Color.RED);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);

        mTrianglePaint = new Paint();
        mTrianglePaint.setAntiAlias(true);
        mTrianglePaint.setDither(true);
        mTrianglePaint.setStyle(Paint.Style.FILL);
        mTrianglePaint.setColor(Color.BLUE);

        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);
        mCirclePaint.setColor(Color.WHITE);

        mTickPath = new Path();
        mTrianglePath = new Path();

    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
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
            case MeasureSpec.UNSPECIFIED:
                wSize = 100;
                break;
            case MeasureSpec.EXACTLY:
                wSize = wSize - getPaddingLeft() - getPaddingRight();
                break;
        }
        switch (hMode){
            case MeasureSpec.AT_MOST:
                hSize = 50;
                break;
            case MeasureSpec.UNSPECIFIED:
                hSize = 50;
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
        mRadius = mHeight / 3;
        mTrianglePath.reset();
        mTickPath.reset();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
//        drawTiming(canvas);
//        drawTriangle(canvas);
        drawTiming(canvas);

    }

    //绘制圆角背景
    private void drawBackground(Canvas canvas){
        RectF backgroundRect = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(backgroundRect, 10.0f, 10.0f, mBackgroundPaint);
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

    }
}
