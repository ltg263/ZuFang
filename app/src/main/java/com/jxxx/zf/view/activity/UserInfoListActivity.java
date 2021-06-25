package com.jxxx.zf.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.view.adapter.UserInfoListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UserInfoListActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private UserInfoListAdapter mUserInfoListAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_user_info_list;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "选择带看");

    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mUserInfoListAdapter = new UserInfoListAdapter(list);
        mRvList.setAdapter(mUserInfoListAdapter);

        mUserInfoListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                finish();
            }
        });
    }
}
