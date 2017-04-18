package scun2016.com.promoto.util.sp;

import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by EricLi.
 * on 2017/4/18 in 上午11:38
 * Email: EricLi1235@gmial.com
 */

public abstract class SharedPrefencesImpl {

    abstract SharedPreferences getPreferences();

    public void setStringConfig(String key, String value){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringConfig(String key, String defaultValue){
        if (getPreferences() == null){
            return defaultValue;
        }
        String result = getPreferences().getString(key, defaultValue);
        return result;
    }

    public void setLongConfig(String key, long value){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLongConfig(String key, long defaultValue){
        if (getPreferences() == null){
            return defaultValue;
        }
        long result = getPreferences().getLong(key, defaultValue);
        return result;
    }

    public void setIntConfig(String key, int value){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getIntConfig(String key, int defaultValue){
        if (getPreferences() == null){
            return defaultValue;
        }
        int result = getPreferences().getInt(key, defaultValue);
        return result;
    }

    public void setBooleanConfig(String key, boolean value){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanConfig(String key, boolean defaultValue){
        if (getPreferences() == null){
            return defaultValue;
        }
        boolean result = getPreferences().getBoolean(key, defaultValue);
        return result;
    }

    public void setFloatConfig(String key, float value){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public float getFloatConfig(String key, float defaultValue){
        if (getPreferences() == null){
            return defaultValue;
        }
        float result = getPreferences().getFloat(key, defaultValue);
        return result;
    }

    public HashMap<String, String> getMapConfig(String key){
        HashMap<String, String> params = new HashMap<>();
        params.put(key, getPreferences().getString(key, ""));
        return params;
    }

    public void removeConfig(String key){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.remove(key);
        editor.apply();
    }

}
