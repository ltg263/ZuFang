package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.UserContractBean;

import butterknife.BindView;
import butterknife.OnClick;

public class MineHtNew1Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.bnt_2)
    TextView mBnt2;
    UserContractBean.ListBean mUserContractBean;
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_new_1;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "确认事项");
    }

    @Override
    public void initData() {
        mUserContractBean = getIntent().getParcelableExtra("mUserContractBean");
    }

    @OnClick({R.id.bnt_1, R.id.bnt_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_1:
                break;
            case R.id.bnt_2:
                Intent mIntent = new Intent(this,MineHtNew6Activity.class);
                mIntent.putExtra("mUserContractBean",mUserContractBean);
                startActivity(mIntent);
//              postAdviserCreate();
                break;
        }
    }
}
