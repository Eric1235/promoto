package scun2016.com.promoto.timing;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:17
 * Email: EricLi1235@gmial.com
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import scun2016.com.promoto.R;
import scun2016.com.promoto.base.BaseActivity;

/**
 * 倒计时activity
 */
public class TimingActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);
//        registerReceiver(new AlarmReceiver(), null);
        initView();
    }


    private void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.timing_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_timing, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
