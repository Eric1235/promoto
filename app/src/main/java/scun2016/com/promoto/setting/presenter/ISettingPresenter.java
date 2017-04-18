package scun2016.com.promoto.setting.presenter;

import scun2016.com.promoto.base.BasePresenter;

/**
 * Created by EricLi.
 * on 2017/4/18 in 上午11:33
 * Email: EricLi1235@gmial.com
 */

public interface ISettingPresenter extends BasePresenter {
    void gotoLogin();

    void gotoPro();

    void gotoSync();

    void gotoSoundEffect();

    void gotoPromotoClock();

    void gotoShieldApp();

    void gotoTarget();

    void gotoUIControl();

    void gotoSmartDevice();

    void gotoSupportCenter();

    void gotoUpdate();

    void gotoAbout();

    void setShakeState(boolean b);
}
