package scun2016.com.promoto.util.sp;

import android.content.Context;
import android.content.SharedPreferences;

import scun2016.com.promoto.base.BaseApplication;

/**
 * Created by EricLi.
 * on 2017/4/18 in 上午11:57
 * Email: EricLi1235@gmial.com
 */

public class AccountPreferences extends SharedPrefencesImpl{
    private static volatile AccountPreferences instance = null;

    private AccountPreferences() {
    }

    public static AccountPreferences getInstance() {
        if (instance == null) {
            synchronized (AccountPreferences.class) {
                if (instance == null) {
                    instance = new AccountPreferences();
                }
            }
        }
        return instance;
    }
    @Override
    SharedPreferences getPreferences() {
        return BaseApplication.getInstance().getSharedPreferences("account", Context.MODE_PRIVATE);
    }


}
