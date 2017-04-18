package scun2016.com.promoto.base;

import org.litepal.LitePalApplication;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:24
 * Email: EricLi1235@gmial.com
 */

public class BaseApplication extends LitePalApplication{
    private static BaseApplication instance = null;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static synchronized BaseApplication getInstance() {
        if (null == instance) {
            instance = new BaseApplication();
        }
        return instance;
    }
}
