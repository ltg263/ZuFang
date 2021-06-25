package com.jxxx.zf.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.IntentUtils;
import com.jxxx.zf.view.adapter.MineListYyzxAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineYyzxListActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private MineListYyzxAdapter mMineListYyzxAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_refresh_list;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "预约中心");
    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mMineListYyzxAdapter = new MineListYyzxAdapter(list);
        mRvList.setAdapter(mMineListYyzxAdapter);

        mMineListYyzxAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                baseStartActivity(ZuFangYyxqActivity.class,null);
            }
        });
        mMineListYyzxAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.bnt_1:
                        if(position==1){
                            baseStartActivity(MineYypjActivity.class,null);
                        }
                        break;
                    case R.id.bnt_2:

                        break;
                    case R.id.bnt_3:
                        IntentUtils.startActivityPhone(MineYyzxListActivity.this,"17774004352");
                        break;

                }
            }
        });
    }
}
