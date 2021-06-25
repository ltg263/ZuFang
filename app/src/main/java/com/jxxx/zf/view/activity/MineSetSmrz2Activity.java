package com.jxxx.zf.view.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 实名认证
 * 编号：6140
 */
public class MineSetSmrz2Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_set_smrz2;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "实名认证");
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.bnt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bnt:
                baseStartActivity(MineHtJcOkActivity.class,null);
                break;
        }
    }


}
