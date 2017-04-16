package scun2016.com.promoto.util;

import android.content.Context;

/**
 * Created by EricLi.
 * on 2017/3/30 in 下午2:44
 * Email: EricLi1235@gmial.com
 */

//单位转换工具
public class DensityUtil {

    /**
     * 不允许工具类进行实例化
     */
    private DensityUtil(){
        //抛出错误
        throw new Error("不要实例化单位转换工具类");
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
