package scun2016.com.promoto.setting.presenter;

import scun2016.com.promoto.base.BasePresenter;
import scun2016.com.promoto.bean.User;
import scun2016.com.promoto.setting.view.ISettingView;

/**
 * Created by EricLi.
 * on 2017/4/18 in 上午9:37
 * Email: EricLi1235@gmial.com
 */

public class SettingPresenter implements BasePresenter{
    private User mUser;
    private ISettingView mSettingView;
    public SettingPresenter(ISettingView settingView){
        mSettingView = settingView;
    }

    public void setShakeState(boolean b){

    }

    public void gotoLogin(){

    }

    @Override
    public void onCreate() {
        mUser = new User();
        mUser.setName("Eric");
        mUser.setPro(true);

        mSettingView.initAccount(true, mUser.getName());
        mSettingView.initPro(mUser.isPro());
        mSettingView.initShakeSwitch(true);
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
}
