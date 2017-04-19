package scun2016.com.promoto.util.sp;

import android.content.Context;
import android.content.SharedPreferences;

import scun2016.com.promoto.util.constants.ProjectConfig;

/**
 * Created by EricLi.
 * on 2017/4/18 in 上午11:58
 * Email: EricLi1235@gmial.com
 */

public class AppPreferences extends SharedPrefencesImpl implements IAppConfig{
    private static volatile AppPreferences instance;

    private AppPreferences(){

    }
    @Override
    SharedPreferences getPreferences() {
        return ProjectConfig.sContext.getSharedPreferences("app_config",
                Context.MODE_PRIVATE);
    }

    public static AppPreferences getInstance(){
        if (instance == null){
            synchronized (AppPreferences.class){
                if (instance == null){
                    instance = new AppPreferences();
                }
            }
        }
        return instance;
    }

    @Override
    public void setShakeState(boolean b) {
        setBooleanConfig(SHAKE_STATE, b);
    }

    @Override
    public boolean getShakeState() {
        return getBooleanConfig(SHAKE_STATE, true);
    }
}
