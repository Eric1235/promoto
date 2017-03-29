package scun2016.com.promoto.home;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import scun2016.com.promoto.R;
import scun2016.com.promoto.base.BaseActivity;
import scun2016.com.promoto.bean.PromotoBean;

/**
 * Created by EricLi.
 * on 2017/3/27 in 下午9:17
 * Email: EricLi1235@gmial.com
 */
//添加新的任务
public class AddPromotoActivity extends BaseActivity {
    private EditText etAddPromoto;

    private String mContent;

    private PromotoBean mPromotoBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState,
            @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_promoto);
        initVew();
        initListener();
    }

    private void initVew(){
        etAddPromoto = (EditText) findViewById(R.id.et_new_task);
    }

    private void initListener(){
        etAddPromoto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getContent(){

    }
}
