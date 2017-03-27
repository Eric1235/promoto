package scun2016.com.promoto.home;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:18
 * Email: EricLi1235@gmial.com
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import itemtouchhelperextension.ItemTouchHelperExtension;
import scun2016.com.promoto.R;
import scun2016.com.promoto.base.BaseFragment;
import scun2016.com.promoto.bean.PromotoBean;

/**
 * 主页
 */
public class HomeFragment extends BaseFragment{

    private RecyclerView mRecyclerView;
    private List<PromotoBean> mBeanList;
    private PromotoAdapter mAdapter;

    public ItemTouchHelperExtension mItemTouchHelper;
    public ItemTouchHelperExtension.Callback mCallback;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_promoto);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        //解决屏闪问题
        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter = new PromotoAdapter(getBaseActivity(), mBeanList);
        mCallback  = new ItemTouch(mAdapter);
        mItemTouchHelper = new ItemTouchHelperExtension(mCallback);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new scun2016.com.promoto.home.DividerItemDecoration(getBaseActivity()));
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        return view;
    }

    private void initData(){
        mBeanList = new ArrayList<>();
        PromotoBean bean = new PromotoBean();
        bean.setContent("吃饭");
        bean.setSelected(true);
        bean.setUrgent(true);
        bean.setPosition(1);
        mBeanList.add(bean);

        bean = new PromotoBean();
        bean.setContent("学习");
        bean.setSelected(true);
        bean.setPosition(2);
        bean.setUrgent(false);
        mBeanList.add(bean);

        bean = new PromotoBean();
        bean.setContent("睡觉");
        bean.setSelected(false);
        bean.setPosition(1);
        bean.setUrgent(true);
        mBeanList.add(bean);

        bean = new PromotoBean();
        bean.setContent("#编程");
        bean.setSelected(true);
        bean.setPosition(2);
        bean.setUrgent(false);
        mBeanList.add(bean);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
