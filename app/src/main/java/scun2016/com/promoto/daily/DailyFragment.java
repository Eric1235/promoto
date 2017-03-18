package scun2016.com.promoto.daily;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:19
 * Email: EricLi1235@gmial.com
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import scun2016.com.promoto.R;
import scun2016.com.promoto.base.BaseFragment;

/**
 * 日常使用情况记录
 */
public class DailyFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily, null);
        return view;
    }
}
