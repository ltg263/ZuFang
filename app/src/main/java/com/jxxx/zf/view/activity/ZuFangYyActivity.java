package com.jxxx.zf.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ZuFangYyActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.iv_select)
    ImageView mIvSelect;
    @BindView(R.id.ll_dkgw)
    LinearLayout mLlDkgw;

    @Override
    public int intiLayout() {
        return R.layout.activity_zu_fang_yy;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "房源预约");

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_select, R.id.ll_dkgw,R.id.bnt_kf})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_select:
                if(mIvSelect.isSelected()){
                    mLlDkgw.setVisibility(View.GONE);
                    mIvSelect.setSelected(false);
                }else{
                    mLlDkgw.setVisibility(View.VISIBLE);
                    mIvSelect.setSelected(true);
                }

                break;
            case R.id.ll_dkgw:
                baseStartActivity(UserInfoListActivity.class,null);
                break;
            case R.id.bnt_kf:
                baseStartActivity(ZuFangYyOkActivity.class,null);
                break;
        }
    }
}
