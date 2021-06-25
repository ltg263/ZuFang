package com.jxxx.zf.view.activity.payActivity;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;

public class ActivityPayTiXianDetails extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @Override
    public int intiLayout() {
        return R.layout.activity_pay_ti_xian_details;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "提现详情");

    }

    @Override
    public void initData() {

    }
}
