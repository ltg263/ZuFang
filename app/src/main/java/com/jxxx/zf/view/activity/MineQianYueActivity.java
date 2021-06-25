package com.jxxx.zf.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MineQianYueActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_qian_yue;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "签约");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt:
                baseStartActivity(MineHtNew1Activity.class,null);
                break;
        }
    }
}
