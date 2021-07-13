package com.jxxx.zf.view.activity;

import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.MainActivity;
import com.jxxx.zf.R;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ZuFangYyOkActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;

    @Override
    public int intiLayout() {
        return R.layout.activity_zu_fang_yy_ok;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "预约成功");

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.bnt_ckqtfy, R.id.bnt_ckyyxx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_ckqtfy:
                baseStartActivity(MainActivity.class,null);
                break;
            case R.id.bnt_ckyyxx:
                ZuFangYyxqActivity.startActivity_zf(this,
                        getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH),0);
                break;
        }
    }
}
