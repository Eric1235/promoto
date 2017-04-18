package scun2016.com.promoto.setting.presenter;

import android.content.Context;
import android.content.Intent;

import scun2016.com.promoto.bean.User;
import scun2016.com.promoto.home.EditPromotoActivity;
import scun2016.com.promoto.setting.view.ISettingView;
import scun2016.com.promoto.util.sp.AppPreferences;

/**
 * Created by EricLi.
 * on 2017/4/18 in 上午9:37
 * Email: EricLi1235@gmial.com
 */

public class SettingPresenter implements ISettingPresenter{
    private User mUser;
    private ISettingView mSettingView;
    private Context mContext;

    private boolean shakeState;
    public SettingPresenter(ISettingView settingView){
        mSettingView = settingView;
    }

    @Override
    public void setShakeState(boolean b) {
        AppPreferences.getInstance().setShakeState(b);
    }

    @Override
    public void gotoLogin() {
        toLogin();
    }

    @Override
    public void gotoPro() {

    }

    @Override
    public void gotoSync() {

    }

    @Override
    public void gotoSoundEffect() {

    }

    @Override
    public void gotoPromotoClock() {

    }

    @Override
    public void gotoShieldApp() {

    }

    @Override
    public void gotoTarget() {

    }

    @Override
    public void gotoUIControl() {

    }

    @Override
    public void gotoSmartDevice() {

    }

    @Override
    public void gotoSupportCenter() {

    }

    @Override
    public void gotoUpdate() {

    }

    @Override
    public void gotoAbout() {

    }

    @Override
    public void onCreate() {
        mUser = new User();
        mUser.setName("Eric");
        mUser.setPro(true);

        mContext = mSettingView.getSettingContext();
        shakeState = AppPreferences.getInstance().getShakeState();

        //刷view
        mSettingView.initAccount(true, mUser.getName());
        mSettingView.initPro(mUser.isPro());
        mSettingView.initShakeSwitch(shakeState);


    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    private void toLogin(){
        Intent i = new Intent(mContext, EditPromotoActivity.class);
        mContext.startActivity(i);
    }
}
