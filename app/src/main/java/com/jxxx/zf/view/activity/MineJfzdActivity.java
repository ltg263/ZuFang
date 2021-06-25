package com.jxxx.zf.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.StatusBarUtil;
import com.jxxx.zf.view.adapter.MineJfzdListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineJfzdActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MineJfzdListAdapter mMineJfzdListAdapter;

    @Override
    public int intiLayout() {
        StatusBarUtil.setTranslucentStatus(this);
        return R.layout.activity_mine_jfzd;
    }

    @Override
    public void initView() {
        mMyToolbar.setNavigationIcon(R.mipmap.icon_common_w);
        mMyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mMineJfzdListAdapter = new MineJfzdListAdapter(list);
        mRvList.setAdapter(mMineJfzdListAdapter);
    }

    @Override
    public void initData() {

    }
}
