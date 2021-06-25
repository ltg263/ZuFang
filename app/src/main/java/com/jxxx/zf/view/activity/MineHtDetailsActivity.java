package com.jxxx.zf.view.activity;


import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MineHtDetailsActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_details;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "我的合同");

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.bnt_jy, R.id.bnt_xy, R.id.bnt_zd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_jy:
                baseStartActivity(MineHtJyActivity.class,null);
                break;
            case R.id.bnt_xy:
                baseStartActivity(MineHtNew1Activity.class,null);
                break;
            case R.id.bnt_zd:
                baseStartActivity(MineJfzdActivity.class,null);
                break;
        }
    }
}
