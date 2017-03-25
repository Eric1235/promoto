package scun2016.com.promoto.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import scun2016.com.promoto.R;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:43
 * Email: EricLi1235@gmial.com
 */

/**
 * 自定义底部栏
 */
public class ViewIndicator extends LinearLayout implements View.OnClickListener {

    private OnIndicateChangeListener mOnIndicateChangeListener;

    private CenterIndicator mCenterIndicator;

    private View[] mIndicators;
    private List<ViewIndicatorParam> mParams = new ArrayList<>();
    private int mCurrentIndex;


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

    private View createIndicator(ViewIndicatorParam param){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_indicator, null);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(params);
        setNormal(view, param);
        return view;
    }

    private View createCenterIndicator(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_center_indicator, null);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        view.setLayoutParams(params);
        return view;
    }


    //初始化哦
    public void init(List<ViewIndicatorParam> params){
        if (params == null){
            return;
        }
        mParams.clear();
        mParams.addAll(params);
        int size = params.size();
        mIndicators = new View[size];
        for (int i = 0 ; i < size ; i ++){
            mIndicators[i] = createIndicator(params.get(i));
            mIndicators[i].setTag(i);
            mIndicators[i].setOnClickListener(this);
            addView(mIndicators[i]);
        }

        View view = createCenterIndicator();
        addView(view, 2);
        mCenterIndicator = (CenterIndicator) view.findViewById(R.id.center_indicator);
        mCurrentIndex = -1;
        setIndicator(0);

    }


    @Override
    public void onClick(View v) {
        int tag = (Integer) v.getTag();
        if(mCurrentIndex == tag){
            return;
        }
        setIndicator(tag);
        if(mOnIndicateChangeListener != null){
            mOnIndicateChangeListener.onIndicateChange(v, tag);
        }
    }

    public void setIndicator(int which){
        if (which == mCurrentIndex){
            return;
        }
        View currentView = mIndicators[which];
        ViewIndicatorParam currentParam = mParams.get(which);
        setPressed(currentView, currentParam);
        if (mCurrentIndex >= 0){
            View preView = mIndicators[mCurrentIndex];
            ViewIndicatorParam preParam = mParams.get(mCurrentIndex);
            setNormal(preView, preParam);
        }
        mCurrentIndex = which;
    }

    //获得中间指示器
    public CenterIndicator getCenterIndicator() {
        return mCenterIndicator;
    }

    private void setNormal(View view, ViewIndicatorParam param){
        ImageView imageView = (ImageView) view.findViewById(R.id.img_tab);
        imageView.setImageResource(param.getIcUnPressedId());
    }

    private void setPressed(View view, ViewIndicatorParam param){
        ImageView imageView = (ImageView) view.findViewById(R.id.img_tab);
        imageView.setImageResource(param.getIcPressedId());
    }



    /**
     * 其他地方的导航
     */
    public static class ViewIndicatorParam{
        int icPressedId;
        int icUnPressedId;

        public ViewIndicatorParam(){

        }

        public int getIcPressedId() {
            return icPressedId;
        }

        public void setIcPressedId(int icPressedId) {
            this.icPressedId = icPressedId;
        }

        public int getIcUnPressedId() {
            return icUnPressedId;
        }

        public void setIcUnPressedId(int icUnPressedId) {
            this.icUnPressedId = icUnPressedId;
        }
    }

    public interface OnIndicateChangeListener{
        void onIndicateChange(View v, int which);
    }

    public void setOnindicateChangeListener(OnIndicateChangeListener listener){
        mOnIndicateChangeListener = listener;
    }

}
