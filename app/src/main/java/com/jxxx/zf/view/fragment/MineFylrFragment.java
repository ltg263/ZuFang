package com.jxxx.zf.view.fragment;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.view.activity.MineFytjActivity;
import com.jxxx.zf.view.activity.ZuFangFaBuActivity;
import com.jxxx.zf.view.activity.ZuFangXqActivity;
import com.jxxx.zf.view.adapter.MineListFylrAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineFylrFragment extends BaseFragment {
    private MineListFylrAdapter mMineListFylrAdapter;

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
        mMineListFylrAdapter = new MineListFylrAdapter(list);
        mRvList.setAdapter(mMineListFylrAdapter);

        mMineListFylrAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                baseStartActivity(ZuFangXqActivity.class,null);
            }
        });
        mMineListFylrAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.bnt_1:
                        baseStartActivity(MineFytjActivity.class,null);
                        break;
                    case R.id.bnt_2:
                        baseStartActivity(ZuFangFaBuActivity.class,null);
                        break;
                    case R.id.bnt_3:

                        break;

                }
            }
        });
    }

    public void setNotifyDataSetChanged(boolean isBianJi){
        mMineListFylrAdapter.setBianji(isBianJi);
        mMineListFylrAdapter.notifyDataSetChanged();
    }
    @Override
    protected void initData() {

    }
}
