package com.jxxx.zf.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.MainActivity;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MineHtJcOkActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_jc_ok;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "合同解除");

    }

    @Override
    public void initData() {
    }


    @OnClick({R.id.bnt_zdl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_zdl:
                baseStartActivity(MainActivity.class,null);
                break;
        }
    }
}
