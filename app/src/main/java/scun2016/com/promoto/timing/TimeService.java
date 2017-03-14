package scun2016.com.promoto.timing;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:13
 * Email: EricLi1235@gmial.com
 */

/**
 * 计时服务类
 */
public class TimeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
