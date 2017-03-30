package scun2016.com.promoto.home;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:18
 * Email: EricLi1235@gmial.com
 */

import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import itemtouchhelperextension.ItemTouchHelperExtension;
import scun2016.com.promoto.R;
import scun2016.com.promoto.base.BaseFragment;
import scun2016.com.promoto.bean.PromotoBean;
import scun2016.com.promoto.util.DensityUtil;

/**
 * 主页
 */
public class HomeFragment extends BaseFragment implements DialogInterface.OnDismissListener, EditText.OnEditorActionListener{

    private TextView tvAddPromoto;

    private RecyclerView mRecyclerView;
    private List<PromotoBean> mBeanList;
    private PromotoAdapter mAdapter;

    public ItemTouchHelperExtension mItemTouchHelper;
    public ItemTouchHelperExtension.Callback mCallback;

    private EditText mEditText;//输入新任务；
    private AlertDialog dialog;

    private int textViewX;
    private int textViewY;
    private int textViewHeight;
    private int statusBarHeight;


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


        tvAddPromoto = (TextView) view.findViewById(R.id.tv_new_task);

        tvAddPromoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到AddPromotoAvtivity
                AlertDialog.Builder builder = new AlertDialog.Builder(getBaseActivity(), R.style.Dialog);
                View view = View
                        .inflate(getActivity(), R.layout.activity_add_promoto, null);
                mEditText = (EditText) view.findViewById(R.id.et_new_task);
                mEditText.setOnEditorActionListener(HomeFragment.this);
                builder.setView(view);
                builder.setCancelable(true);
                builder.setOnDismissListener(HomeFragment.this);
                //取消或确定按钮监听事件处理
                dialog = builder.create();
                dialog.show();
                Window window = dialog.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                WindowManager.LayoutParams params = window.getAttributes();
                window.setGravity(Gravity.TOP);
                int deltaY = statusBarHeight + DensityUtil.dip2px(getBaseActivity(), 50) + textViewY - textViewHeight / 2;
                params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                params.x = textViewX;
                params.y = deltaY;
                dialog.onWindowAttributesChanged(params);
                toggleKeyBoard();
            }
        });

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
        bean.setTagName("#休息");
        bean.setTotalPromotoNum(10);
        bean.setFinishedPromotoNum(1);
        bean.setSelected(true);
        bean.setUrgent(true);
        bean.setPosition(1);
        mBeanList.add(bean);

        bean = new PromotoBean();
        bean.setContent("学习");
        bean.setTotalPromotoNum(10);
        bean.setFinishedPromotoNum(1);
        bean.setSelected(true);
        bean.setPosition(1);
        bean.setUrgent(false);
        mBeanList.add(bean);

        bean = new PromotoBean();
        bean.setTagName("#睡觉");
        bean.setTotalPromotoNum(10);
        bean.setFinishedPromotoNum(1);
        bean.setSelected(false);
        bean.setPosition(1);
        bean.setUrgent(true);
        mBeanList.add(bean);

        bean = new PromotoBean();
        bean.setTagName("#编程");
        bean.setSelected(true);
        bean.setPosition(1);
        bean.setUrgent(false);
        mBeanList.add(bean);

        bean = new PromotoBean();
        bean.setContent("写作");
        bean.setSelected(true);
        bean.setPosition(1);
        bean.setUrgent(false);
        mBeanList.add(bean);

    }

    @Override
    public void onStart() {
        super.onStart();
        //正确获取view的位置
        tvAddPromoto.post(new Runnable() {
            @Override
            public void run() {
                textViewX = (int)tvAddPromoto.getX();
                textViewY = (int)tvAddPromoto.getY();
                textViewHeight = tvAddPromoto.getHeight();
                Rect rect = new Rect();
                //获取状态栏高度
                getBaseActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                statusBarHeight = rect.top;

            }
        });
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

    @Override
    public void onDismiss(DialogInterface dialog) {
        toggleKeyBoard();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        String content;
        //监听enter键被按下
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
            content = mEditText.getText().toString();
            dialog.dismiss();
        }
        return false;
    }
}
