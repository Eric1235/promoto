package scun2016.com.promoto.timing;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
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

    private AlarmManager am;

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
        am = (AlarmManager)getSystemService(ALARM_SERVICE);
        long firstTime = SystemClock.elapsedRealtime();
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 1000, sender);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
