package scun2016.com.promoto.util.sp;

/**
 * Created by EricLi.
 * on 2017/4/18 in 下午2:34
 * Email: EricLi1235@gmial.com
 */

public interface IAppConfig {

    public static final String SHAKE_STATE = "shake_state";

    void setShakeState(boolean b);

    boolean getShakeState();
}
