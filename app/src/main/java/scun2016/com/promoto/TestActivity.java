package scun2016.com.promoto;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import scun2016.com.promoto.view.CenterIndicator;

/**
 * Created by EricLi.
 * on 2017/3/15 in 上午10:45
 * Email: EricLi1235@gmial.com
 */

public class TestActivity extends Activity {

    private CenterIndicator mCenterIndicator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mCenterIndicator = (CenterIndicator) findViewById(R.id.view);
        mCenterIndicator.setProgress(50);
    }
}
