package com.jxxx.zf.view.fragment;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.view.activity.ChatActivity;
import com.jxxx.zf.view.activity.MineJdxqActivity;
import com.jxxx.zf.view.activity.ZuFangXqActivity;
import com.jxxx.zf.view.adapter.MineListJdAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineJieDan1Fragment extends BaseFragment {
    private MineListJdAdapter mMineListJdAdapter;

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mMineListJdAdapter = new MineListJdAdapter(list);
        mRvList.setAdapter(mMineListJdAdapter);

        mMineListJdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                baseStartActivity(ZuFangXqActivity.class,null);
            }
        });
        mMineListJdAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.bnt_lx:
                        baseStartActivity(ChatActivity.class,null);
                        break;
                    case R.id.bnt_jd:
                        baseStartActivity(MineJdxqActivity.class,null);
                        break;

                }
            }
        });
    }

    @Override
    protected void initData() {

    }
}
