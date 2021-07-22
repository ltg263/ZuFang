package com.jxxx.zf.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.view.activity.payActivity.AdapterPayLogList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineHtNew4Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private AdapterPayLogList mAdapterPayLogList;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_new_4;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "新建合同");

    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mAdapterPayLogList = new AdapterPayLogList(null);
        mRvList.setAdapter(mAdapterPayLogList);
    }

    @OnClick({R.id.bnt_1, R.id.bnt_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_1:
                finish();
                break;
            case R.id.bnt_2:
                baseStartActivity(MineHtNew5Activity.class,null);
                break;
        }
    }
}
