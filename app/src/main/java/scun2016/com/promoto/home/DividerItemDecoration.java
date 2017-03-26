package scun2016.com.promoto.home;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        if (dividerHeight != -1){
            this.dividerHeight = dividerHeight;
        }
    }

    public DividerItemDecoration(Context context, int orientation, int startPadding, int endPadding, int dividerHeight){
        setOrientation(orientation);
        mContext = context;

        init();

    }
}
