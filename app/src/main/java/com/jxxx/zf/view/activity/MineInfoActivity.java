package com.jxxx.zf.view.activity;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;

public class MineInfoActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar myToolbar;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_info;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "个人信息");
    }

    @Override
    public void initData() {

    }
}
