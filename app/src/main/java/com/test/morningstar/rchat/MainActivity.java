package com.test.morningstar.rchat;

import android.widget.EditText;
import android.widget.TextView;

import com.test.morningstar.common.app.Activity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends Activity implements IView{
    @BindView(R.id.text_test)
    TextView mTestText;

    @BindView(R.id.edit_search)
    EditText mSearchText;

    private IPresenter mPresenter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new Presenter(this);
    }

    @OnClick(R.id.button_submit)
    void onSubmit(){
        mPresenter.search();
    }


    @Override
    public String getInputString() {
        return mSearchText.getText().toString();
    }

    @Override
    public void setResultString(String string) {
        mTestText.setText(string);
    }
}
