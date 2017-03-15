package scun2016.com.promoto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import scun2016.com.promoto.timing.TimingActivity;
import scun2016.com.promoto.view.CenterIndicator;
import scun2016.com.promoto.view.ViewIndicator;

public class MainActivity extends AppCompatActivity {

    private ViewIndicator mViewIndicator;
    private CenterIndicator mCenterIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewIndicator = (ViewIndicator) findViewById(R.id.view_indicator);
        initViewIndicator();
    }

    private void initViewIndicator(){
        List<ViewIndicator.ViewIndicatorParam> params = new ArrayList<>();
        ViewIndicator.ViewIndicatorParam param1 = new ViewIndicator.ViewIndicatorParam();
        param1.setIcPressedId(R.mipmap.ic_launcher);
        param1.setIcUnPressedId(R.mipmap.ic_launcher_round);
        params.add(param1);

        ViewIndicator.ViewIndicatorParam param2 = new ViewIndicator.ViewIndicatorParam();
        param2.setIcPressedId(R.mipmap.ic_launcher);
        param2.setIcUnPressedId(R.mipmap.ic_launcher_round);
        params.add(param2);

        ViewIndicator.ViewIndicatorParam param3 = new ViewIndicator.ViewIndicatorParam();
        param3.setIcPressedId(R.mipmap.ic_launcher);
        param3.setIcUnPressedId(R.mipmap.ic_launcher_round);
        params.add(param3);

        ViewIndicator.ViewIndicatorParam param4 = new ViewIndicator.ViewIndicatorParam();
        param4.setIcPressedId(R.mipmap.ic_launcher);
        param4.setIcUnPressedId(R.mipmap.ic_launcher_round);
        params.add(param4);

        mViewIndicator.init(params);

        mCenterIndicator = mViewIndicator.getCenterIndicator();
        mCenterIndicator.setStatus(1);

        mCenterIndicator.setOnCenterClickListener(new CenterIndicator.OnCenterClickListener() {
            @Override
            public void onCenterClick(int status) {
                mCenterIndicator.setStatus(2);
                startActivity(new Intent(MainActivity.this, TimingActivity.class));
            }
        });

        mViewIndicator.setOnindicateChangeListener(new ViewIndicator.OnIndicateChangeListener() {
            @Override
            public void onIndicateChange(View v, int which) {
                Toast.makeText(MainActivity.this, "IndicatorClick :" + which, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
