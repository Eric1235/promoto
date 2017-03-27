package scun2016.com.promoto.home;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import scun2016.com.promoto.R;

/**
 * Created by EricLi.
 * on 2017/3/26 in 下午6:39
 * Email: EricLi1235@gmial.com
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int DEFAULT_DIIDER_HEIGHT = 1;
    private static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    private static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    protected int mOrientation;
    protected int padding;
    protected int startPadding;
    protected int endPadding;
    protected int dividerHeight;
    protected Context mContext;
    protected Paint mPaddingPaint;
    protected Paint mDividerPaint;

    public DividerItemDecoration(Context context){
        this(context, VERTICAL_LIST, -1, -1);
    }

    public DividerItemDecoration(Context context, int orientation, int padding, int dividerHeight){
        setOrientation(orientation);
        mContext = context;

        init();

        if (padding != -1){
            this.padding = padding;
        }
        updatePadding();
        if (dividerHeight != -1){
            this.dividerHeight = dividerHeight;
        }
    }

    public DividerItemDecoration(Context context, int orientation, int startPadding, int endPadding, int dividerHeight){
        setOrientation(orientation);
        mContext = context;

        init();
        if (startPadding != -1){
            this.startPadding = startPadding;
        }
        if (endPadding != -1){
            this.endPadding = endPadding;
        }

        if (dividerHeight != -1){
            this.dividerHeight = dividerHeight;
        }
    }

    private void updatePadding(){
        startPadding = padding;
        endPadding = padding;
    }

    private void init(){
        padding = mContext.getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin);
        updatePadding();
        dividerHeight = DEFAULT_DIIDER_HEIGHT;

        mPaddingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaddingPaint.setColor(ContextCompat.getColor(mContext, R.color.gray));
        mPaddingPaint.setStyle(Paint.Style.FILL);

        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerPaint.setColor(ContextCompat.getColor(mContext, R.color.gray));
        mDividerPaint.setStyle(Paint.Style.FILL);

    }

    public void setOrientation(int orientation){
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw new IllegalArgumentException("Invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == HORIZONTAL_LIST){
            drawHorizontal(c, parent);
        } else {
            drawVertical(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent){
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0 ; i < childCount; i ++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + dividerHeight;
            //左边距
            c.drawRect(left, top, left + startPadding, bottom, mPaddingPaint);
            //右边距
            c.drawRect(right - endPadding, top, right, bottom, mPaddingPaint);
            //底部线
            c.drawRect(left + startPadding, top, right - endPadding, bottom, mDividerPaint);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent){
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0 ; i < childCount ; i ++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin +
                    Math.round(ViewCompat.getTranslationX(child));
            final int right = left + dividerHeight;
            c.drawRect(left, top, right, top + startPadding,mPaddingPaint);
            c.drawRect(left, bottom - endPadding, right, bottom, mPaddingPaint);
            c.drawRect(left, top + startPadding, right, bottom - endPadding, mDividerPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == VERTICAL_LIST){
            //是否最后一个
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1){
                outRect.set(0, 0, 0, dividerHeight);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        } else {
            //是否最后一个
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1){
                outRect.set(0, 0, dividerHeight, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }
}
