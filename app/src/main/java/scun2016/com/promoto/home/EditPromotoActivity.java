package scun2016.com.promoto.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import scun2016.com.promoto.R;
import scun2016.com.promoto.base.BaseActivity;

/**
 * Created by EricLi.
 * on 2017/3/27 in 下午9:21
 * Email: EricLi1235@gmial.com
 */
//重新编辑任务
public class EditPromotoActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_promoto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
