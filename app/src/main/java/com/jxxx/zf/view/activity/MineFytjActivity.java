package com.jxxx.zf.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.StatusBarUtil;
import com.jxxx.zf.view.adapter.MineFytjListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineFytjActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MineFytjListAdapter mMineFytjListAdapter;

    @Override
    public int intiLayout() {
        StatusBarUtil.setTranslucentStatus(this);
        return R.layout.activity_mine_fytj;
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
        mMineFytjListAdapter = new MineFytjListAdapter(list);
        mRvList.setAdapter(mMineFytjListAdapter);
    }

    @Override
    public void initData() {

    }
}
