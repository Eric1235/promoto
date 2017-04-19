package scun2016.com.promoto.home;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:18
 * Email: EricLi1235@gmial.com
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
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
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import itemtouchhelperextension.ItemTouchHelperExtension;
import scun2016.com.promoto.R;
import scun2016.com.promoto.base.BaseFragment;
import scun2016.com.promoto.bean.PromotoBean;
import scun2016.com.promoto.util.DensityUtil;
import scun2016.com.promoto.widget.SnackBarUtil;

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

    private Snackbar mSnackbar;//提示条

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
                        .inflate(getActivity(), R.layout.item_edittext, null);
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
        mAdapter.setOnItemDeleteListener(mOnItemDeleteListener);
        mCallback  = new ItemTouch(mAdapter);
        mItemTouchHelper = new ItemTouchHelperExtension(mCallback);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new scun2016.com.promoto.home.DividerItemDecoration(getBaseActivity()));
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        return view;
    }

    private void initData(){
        //在数据库查询数据
        mBeanList = DataSupport.findAll(PromotoBean.class);
        if (mAdapter != null){
            mAdapter.updateData(mBeanList);
        }
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
    public void onResume() {
        super.onResume();
        initData();
    }

    //存储数据
    @Override
    public void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_give_up_promoto:
                Toast.makeText(getBaseActivity(),"放弃番茄", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_history:
                Toast.makeText(getBaseActivity(),"任务历史", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        hideKeyBoard();
    }

    private void saveData(){
        for (PromotoBean bean : mBeanList){
            bean.save();
        }
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        String content;
        //监听enter键被按下
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
            content = mEditText.getText().toString();
            Log.d("Eric", "content = " + content);
            if (content != null && !content.equals("")){
                savePromoto(content);
            }
            dialog.dismiss();

            //刷新数据
            initData();
        }
        return false;
    }


    private PromotoAdapter.onItemDeleteListener mOnItemDeleteListener =
            new PromotoAdapter.onItemDeleteListener() {
        @Override
        public void onItemDelete(final int position, final PromotoBean bean) {
            mSnackbar = SnackBarUtil.LongSnackbar(mRecyclerView,"删除成功", SnackBarUtil.Info);
            mSnackbar.setAction(getString(R.string.cancel), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBeanList.add(position, bean);
                    mAdapter.updateData(mBeanList);
                }
            });

            mSnackbar.show();

        }
    };

    //存储番茄任务
    private void savePromoto(String content){
        PromotoBean bean = getBaseBean();
        //有标签
        if (content.startsWith("#")){
            int len = content.indexOf(" ");
            //有两个内容
            if (len != -1){
                String tagName = content.substring(0, len);
                //进行切割
                String contentName = content.substring(len+1, content.length());
                bean.setTagName(tagName);
                bean.setContent(contentName);
            } else {
                bean.setTagName(content);
            }
        } else {
            bean.setContent(content);
        }
        bean.save();
    }

    //预先填充好数据
    private PromotoBean getBaseBean(){
        PromotoBean bean = new PromotoBean();
        bean.setPosition(1);
        bean.setUrgent(false);
        bean.setFinishedPromotoNum(0);
        bean.setTotalPromotoNum(0);
        bean.setSelected(false);
        bean.setState(0);
        bean.setFinishedTime(0);
        return bean;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
