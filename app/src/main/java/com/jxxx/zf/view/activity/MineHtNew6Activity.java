package com.jxxx.zf.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MineHtNew6Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_new_6;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "短信验证");

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt:
                baseStartActivity(MineHtNew7Activity.class,null);
                break;
        }
    }
}
