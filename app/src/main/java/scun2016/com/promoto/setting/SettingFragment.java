package scun2016.com.promoto.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import scun2016.com.promoto.R;
import scun2016.com.promoto.base.BaseFragment;
import scun2016.com.promoto.setting.presenter.SettingPresenter;
import scun2016.com.promoto.setting.view.ISettingView;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:17
 * Email: EricLi1235@gmial.com
 */

public class SettingFragment extends BaseFragment implements ISettingView, View.OnClickListener{

    private SettingPresenter mSettingPresenter = new SettingPresenter(this);

    private RelativeLayout loginLayout;//登陆
    private RelativeLayout proLayout;//高级版
    private RelativeLayout syncLayout;//同步

    private RelativeLayout soundEffectLayout;
    private RelativeLayout promotoClockLayout;
    private RelativeLayout shieldAppLayout;
    private RelativeLayout targetLayout;
    private RelativeLayout uiSettingLayout;
    private RelativeLayout smartDeviceLayout;

    private RelativeLayout supportCenterLayout;
    private RelativeLayout shakeAndFeedbackLayout;
    private RelativeLayout updateLayout;
    private RelativeLayout aboutLayout;

    private Switch mShakeSwitch;//摇一摇开关

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        loginLayout = (RelativeLayout) view.findViewById(R.id.login_layout);
        proLayout = (RelativeLayout) view.findViewById(R.id.pro_layout);
        syncLayout = (RelativeLayout) view.findViewById(R.id.sync_layout);

        soundEffectLayout = (RelativeLayout) view.findViewById(R.id.sound_effect_layout);
        promotoClockLayout = (RelativeLayout) view.findViewById(R.id.promoto_clock_layout);
        shieldAppLayout = (RelativeLayout) view.findViewById(R.id.shield_app_layout);
        targetLayout = (RelativeLayout) view.findViewById(R.id.target_layout);
        uiSettingLayout = (RelativeLayout) view.findViewById(R.id.ui_control_layout);
        smartDeviceLayout = (RelativeLayout) view.findViewById(R.id.smart_device_layout);

        supportCenterLayout = (RelativeLayout) view.findViewById(R.id.support_center_layout);
        shakeAndFeedbackLayout = (RelativeLayout) view.findViewById(R.id.shake_feedback_layout);
        updateLayout = (RelativeLayout) view.findViewById(R.id.update_layout);
        aboutLayout = (RelativeLayout) view.findViewById(R.id.about_layout);
        mShakeSwitch = (Switch) view.findViewById(R.id.shake_switch) ;
        initListener();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_setting, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initListener(){
        loginLayout.setOnClickListener(this);
        proLayout.setOnClickListener(this);
        syncLayout.setOnClickListener(this);

        soundEffectLayout.setOnClickListener(this);
        promotoClockLayout.setOnClickListener(this);
        shieldAppLayout.setOnClickListener(this);
        targetLayout.setOnClickListener(this);
        uiSettingLayout.setOnClickListener(this);
        smartDeviceLayout.setOnClickListener(this);

        supportCenterLayout.setOnClickListener(this);
        shakeAndFeedbackLayout.setOnClickListener(this);
        updateLayout.setOnClickListener(this);
        aboutLayout.setOnClickListener(this);

        mShakeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSettingPresenter.setShakeState(isChecked);
            }
        });
    }

    private void initData(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_layout:
                Toast.makeText(getBaseActivity(), "login click", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void initShakeSwitch(boolean b) {
        mShakeSwitch.setChecked(b);
    }
}
