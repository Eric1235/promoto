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

    private OnindicateChangeListener mOnindicateChangeListener;

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

    public void init(){

    }

    @Override
    public void onClick(View v) {

    }

    public void setIndicator(int which){

    }

    private void setNormal(View view, ViewIndicatorParam param){

    }

    private void setPressed(){

    }

    /**
     * 中间的指示器，需要传入自定义控件
     * 不能加入到切换中去哦
     */
    public static class CenterViewIndicatorParam{
        CenterIndicator centerIndecator;

        public CenterViewIndicatorParam(){}

        public CenterIndicator getCenterIndecator() {
            return centerIndecator;
        }

        public void setCenterIndecator(CenterIndicator centerIndecator) {
            this.centerIndecator = centerIndecator;
        }
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

    public interface OnindicateChangeListener{
        void onIndicateChange(View v, int which);
    }

    public void setOnindicateChangeListener(OnindicateChangeListener listener){
        mOnindicateChangeListener = listener;
    }


}
