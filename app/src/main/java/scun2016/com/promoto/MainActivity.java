package scun2016.com.promoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import scun2016.com.promoto.base.BaseActivity;
import scun2016.com.promoto.base.BaseFragment;
import scun2016.com.promoto.daily.DailyFragment;
import scun2016.com.promoto.diagram.DiagramFragment;
import scun2016.com.promoto.home.HomeFragment;
import scun2016.com.promoto.setting.SettingFragment;
import scun2016.com.promoto.timing.TimingActivity;
import scun2016.com.promoto.view.CenterIndicator;
import scun2016.com.promoto.view.ViewIndicator;

public class MainActivity extends BaseActivity {

    private ViewIndicator mViewIndicator;
    private CenterIndicator mCenterIndicator;
    private ViewPager mViewPager;

    private List<BaseFragment> mFragments;

    private android.support.v7.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewIndicator = (ViewIndicator) findViewById(R.id.view_indicator);

        initToolBar();

        initViewIndicator();
        initFragments();
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
        initListener();
    }

    private void initToolBar(){
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);

        mToolbar.setTitle("Main");
        setSupportActionBar(mToolbar);
    }

    private void initFragments(){
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new DailyFragment());
        mFragments.add(new DiagramFragment());
        mFragments.add(new SettingFragment());
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
                startActivity(new Intent(MainActivity.this, TimingActivity.class));
                mCenterIndicator.setStatus(2);
            }
        });


    }

    private void initListener(){
        mViewIndicator.setOnindicateChangeListener(new ViewIndicator.OnIndicateChangeListener() {
            @Override
            public void onIndicateChange(View v, int which) {
                Toast.makeText(MainActivity.this, "IndicatorClick :" + which, Toast.LENGTH_SHORT).show();
                mViewPager.setCurrentItem(which);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPixels) {
                mViewIndicator.setIndicator(mViewPager.getCurrentItem());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_give_up_promoto:
                Toast.makeText(MainActivity.this, "Give up", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_history:
                Toast.makeText(MainActivity.this, "History", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class MainFragmentAdapter extends FragmentPagerAdapter{

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
