package scun2016.com.promoto.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

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

    public static final String BEAN_ID = "beanid";

    //传递过来的数据
    private PromotoBean mBean;

    private EditText etTask;
    private ImageButton btnEmerge;
    private ImageButton btnSelected;

    private TextView tvPromotoCount;//预计番茄数

    private ImageButton btnConfirm;//确认修改

    private CardView btnDelete;

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
        tvPromotoCount = (TextView)mPromotoCount.findViewById(R.id.tv_promoto_num);
        btnDelete = (CardView) findViewById(R.id.car_view_delete);
    }

    private void initListener(){
        mAddSubTaskLayout.setOnClickListener(this);
        mRemindTimeLayout.setOnClickListener(this);
        mPromotoCount.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        btnSelected.setOnClickListener(this);
        btnEmerge.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void getData(){
        Intent i = getIntent();
        long id = i.getLongExtra(BEAN_ID, 0);
        mBean = DataSupport.find(PromotoBean.class, id);
    }

    private void initData(){
        String tag = mBean.getTagName();
        String content = mBean.getContent();
        String result = assamblePromoto(tag, content);
        etTask.setText(result);
        etTask.setSelection(result.length());
        tvPromotoCount.setText(String.valueOf(mBean.getTotalPromotoNum()));

        btnEmerge.setSelected(mBean.isUrgent());
        btnSelected.setSelected(mBean.isSelected());
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
                if (checkParamBeforeSubmit()){
                    mBean.saveAsync().listen(new SaveCallback() {
                        @Override
                        public void onFinish(boolean success) {
                            finish();
                        }
                    });

                } else {
                    Toast.makeText(EditPromotoActivity.this, getString(R.string.task_not_null), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_check:
                toggleSelect();
                break;
            case R.id.btn_emerge:
                toggleEmerge();
                break;
            case R.id.car_view_delete:
                mBean.delete();
                finish();
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
        //设置最长的输入为4位数
        NumberKeyboardDialog.sowNumberDialog(EditPromotoActivity.this, 4,
                new OnNumberConfirmListener() {
                    @Override
                    public void getNumber(String number) {
                        mBean.setTotalPromotoNum(Integer.parseInt(number));
                        tvPromotoCount.setText(number);
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //检查参数
    private boolean checkParamBeforeSubmit(){
        String result = etTask.getText().toString();
        if (result.equals("") || result.equals(" ")){
            return false;
        }
        return true;
    }

    /**
     * 组装bean内容
     * @param tag
     * @param content
     * @return
     */
    private String assamblePromoto(String tag, String content){
        StringBuffer result = new StringBuffer();
        if (tag != null){
            result.append(tag);
        }

        if (content != null){
            result.append(" ");
            result.append(content);
        }

        return result.toString();
    }

    private void toggleSelect(){
        mBean.setSelected(!mBean.isSelected());
        btnSelected.setSelected(mBean.isSelected());
    }

    private void toggleEmerge(){
        mBean.setUrgent(!mBean.isUrgent());
        btnEmerge.setSelected(mBean.isUrgent());
    }

}
