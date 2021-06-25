package com.jxxx.zf.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.view.adapter.MineHtNew2Adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineHtNew2Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MineHtNew2Adapter mMineHtNew2Adapter;


    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_new_2;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "物品清单确认");
    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("房间家电");
        list.add("房间家具");
        mMineHtNew2Adapter = new MineHtNew2Adapter(list);
        mRvList.setAdapter(mMineHtNew2Adapter);
    }

    @OnClick({R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt:
                baseStartActivity(MineHtNew3Activity.class, null);
                break;
        }
    }
}
