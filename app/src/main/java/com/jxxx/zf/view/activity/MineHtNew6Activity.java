package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.UserContractBean;

import butterknife.BindView;
import butterknife.OnClick;

public class MineHtNew6Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    UserContractBean.ListBean mUserContractBean;
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_new_6;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "短信验证");
        mUserContractBean = getIntent().getParcelableExtra("mUserContractBean");

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt:
                Intent mIntent = new Intent(this,MineHtNew7Activity.class);
                mIntent.putExtra("mUserContractBean",mUserContractBean);
                startActivity(mIntent);
                break;
        }
    }
}
