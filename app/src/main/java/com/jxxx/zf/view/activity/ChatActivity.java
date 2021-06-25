package com.jxxx.zf.view.activity;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;

public class ChatActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @Override
    public int intiLayout() {
        return R.layout.activity_chat;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "联系人");
    }

    @Override
    public void initData() {

    }
}
