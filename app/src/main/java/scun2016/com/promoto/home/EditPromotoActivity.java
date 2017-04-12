package scun2016.com.promoto.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import scun2016.com.promoto.R;
import scun2016.com.promoto.base.BaseActivity;
import scun2016.com.promoto.bean.PromotoBean;
import scun2016.com.promoto.widget.CustomAlertDialog;
import scun2016.com.promoto.widget.NumberKeyboardDialog;
import scun2016.com.promoto.widget.OnNumberConfirmListener;

/**
 * Created by EricLi.
 * on 2017/3/27 in 下午9:21
 * Email: EricLi1235@gmial.com
 */
//重新编辑任务
public class EditPromotoActivity extends BaseActivity implements View.OnClickListener{

    public static final String PROMOTO_BENA = "promoto_bean";

    //传递过来的数据
    private PromotoBean mBean;

    private EditText etTask;
    private ImageButton btnEmerge;
    private ImageButton btnSelected;

    private TextView tvPromotoCount;//预计番茄数

    private ImageButton btnConfirm;

    private RelativeLayout mRemindTimeLayout;
    private RelativeLayout mAddSubTaskLayout;
    private RelativeLayout mPromotoCount;
    private RelativeLayout mEditTaskLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_promoto);
        getData();
        initView();
        initListener();
        initData();
    }

    private void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mRemindTimeLayout = (RelativeLayout)findViewById(R.id.item_remind_time);
        mAddSubTaskLayout = (RelativeLayout)findViewById(R.id.item_add_sub_task);
        mPromotoCount = (RelativeLayout) findViewById(R.id.item_promoto_count);
        mEditTaskLayout = (RelativeLayout)findViewById(R.id.item_edit_task);
        etTask = (EditText) mEditTaskLayout.findViewById(R.id.et_task);
        btnEmerge = (ImageButton) mEditTaskLayout.findViewById(R.id.btn_emerge);
        btnSelected = (ImageButton) mEditTaskLayout.findViewById(R.id.btn_check);
        btnConfirm = (ImageButton) findViewById(R.id.btn_confirm);
    }

    private void initListener(){
        mAddSubTaskLayout.setOnClickListener(this);
        mRemindTimeLayout.setOnClickListener(this);
        mPromotoCount.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        btnSelected.setOnClickListener(this);
        btnEmerge.setOnClickListener(this);
    }

    private void getData(){
        Intent i = getIntent();
        mBean = i.getParcelableExtra(PROMOTO_BENA);
    }

    private void initData(){
        etTask.setText(mBean.getContent());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_remind_time:
                showUpgradeDialog();
                break;
            case R.id.item_promoto_count:
                showNumberDialog();
                break;
            case R.id.item_add_sub_task:
                showUpgradeDialog();
                break;
            case R.id.btn_confirm:
                break;
            case R.id.btn_check:
                break;
            case R.id.btn_emerge:
                break;
            default:
                break;
        }
    }

    //弹出升级框
    private void showUpgradeDialog(){
        CustomAlertDialog.showAlertDialog(EditPromotoActivity.this,
                getString(R.string.pro_tip_title),
                getString(R.string.pro_tip_content),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
    }

    //弹出数字框
    private void showNumberDialog(){
        NumberKeyboardDialog.sowNumberDialog(EditPromotoActivity.this,
                new OnNumberConfirmListener() {
                    @Override
                    public void getNumber(String number) {
                        Toast.makeText(EditPromotoActivity.this, number,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //检查参数
    private boolean checkParamBeforeSubmit(){
        return false;
    }
}
