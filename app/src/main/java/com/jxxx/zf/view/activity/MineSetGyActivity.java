package com.jxxx.zf.view.activity;

import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.DialogHelper;
import com.jxxx.zf.utils.IntentUtils;

import butterknife.BindView;

public class MineSetGyActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.tv_dqbb)
    TextView tv_dqbb;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_set_gy;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "设置");
        tv_dqbb.setText("V"+ DialogHelper.getVersionName(this));
    }

    @Override
    public void initData() {

    }
}
