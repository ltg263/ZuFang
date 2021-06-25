package com.jxxx.zf.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineHtNew5Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_new_5;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "合同详情");

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt:
                baseStartActivity(MineHtNew6Activity.class,null);
                break;
        }
    }
}
