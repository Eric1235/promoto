package scun2016.com.promoto.daily;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:19
 * Email: EricLi1235@gmial.com
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import scun2016.com.promoto.R;
import scun2016.com.promoto.base.BaseFragment;

/**
 * 日常使用情况记录
 */
public class DailyFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily, null);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_daily, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
