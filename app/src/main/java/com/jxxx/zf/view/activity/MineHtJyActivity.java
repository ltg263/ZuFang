package com.jxxx.zf.view.activity;


import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MineHtJyActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_jy;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "解约合同");

    }


    @Override
    public void initData() {

    }

    @OnClick({R.id.bnt_tjjyht})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_tjjyht:
                baseStartActivity(MineHtJcOkActivity.class,null);
                break;
        }
    }
}
