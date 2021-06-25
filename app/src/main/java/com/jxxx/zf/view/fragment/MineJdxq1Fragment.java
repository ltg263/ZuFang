package com.jxxx.zf.view.fragment;

import android.view.View;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.view.activity.ChatActivity;
import com.jxxx.zf.view.activity.MineQianYueActivity;

import butterknife.OnClick;

public class MineJdxq1Fragment extends BaseFragment {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_mine_jdxq_1;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.bnt_lxyx, R.id.bnt_qrz})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_lxyx:
                baseStartActivity(ChatActivity.class, null);
                break;
            case R.id.bnt_qrz:
                baseStartActivity(MineQianYueActivity.class, null);
                break;
        }
    }
}
