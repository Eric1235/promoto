package scun2016.com.promoto.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import scun2016.com.promoto.R;
import scun2016.com.promoto.base.BaseActivity;
import scun2016.com.promoto.bean.PromotoBean;

/**
 * Created by EricLi.
 * on 2017/3/27 in 下午9:21
 * Email: EricLi1235@gmial.com
 */
//重新编辑任务
public class EditPromotoActivity extends BaseActivity{

    public static final String PROMOTO_BENA = "promoto_bean";

    //传递过来的数据
    private PromotoBean mBean;

    private EditText etTask;
    private ImageButton btnEmerge;
    private ImageButton btnSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_promoto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getData(){
        Intent i = getIntent();
        mBean = i.getParcelableExtra(PROMOTO_BENA);
    }

    private void initData(){

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
