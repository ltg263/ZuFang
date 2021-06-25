package com.jxxx.zf.view.activity;


import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.view.adapter.HomeFyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ZuFangListBdActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private HomeFyAdapter mHomeFyAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_zufang_list_bd;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "对比");
    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mHomeFyAdapter = new HomeFyAdapter(list);
        mRvList.setAdapter(mHomeFyAdapter);

        mHomeFyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @OnClick({R.id.bnt_tjfy, R.id.bnt_ksdb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_tjfy:
                baseStartActivity(MineListScActivity.class,null);
                break;
            case R.id.bnt_ksdb:
                baseStartActivity(ZuFangFybdActivity.class,null);
                break;
        }
    }
}



