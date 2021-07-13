package com.jxxx.zf.view.activity;


import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.ZuFangDetailsBase;
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
    boolean hideSame = false;
    private HomeFyAdapter mHomeFyAdapter;
    List<ZuFangDetailsBase> mData = new ArrayList<>();
    String houseIds = "";
    String houseNames = "";
    @Override
    public int intiLayout() {
        return R.layout.activity_zufang_list_bd;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "对比");
        mData.add(getIntent().getParcelableExtra("data"));
        houseIds = mData.get(0).getId();
        houseNames = mData.get(0).getName();
        mHomeFyAdapter = new HomeFyAdapter(mData);
        mRvList.setAdapter(mHomeFyAdapter);

        mHomeFyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent mIntent) {
        super.onActivityResult(requestCode, resultCode, mIntent);
        if(resultCode==0){
            ZuFangDetailsBase data = mIntent.getParcelableExtra("data");
            if(data!=null){
                if(!houseIds.contains(data.getId())){
                    houseIds = houseIds+","+data.getId();
                    houseNames = houseNames+","+data.getName();
                    mHomeFyAdapter.addData(data);
                }
            }
        }
    }

    @OnClick({R.id.bnt_tjfy, R.id.bnt_ksdb})
    public void onClick(View view) {
        Intent mIntent;
        switch (view.getId()) {

            case R.id.bnt_tjfy:
                mIntent = new Intent(this,MineListScActivity.class);
                mIntent.putExtra("type","1");
                startActivityForResult(mIntent, 1);
                break;
            case R.id.bnt_ksdb:
                if(!houseIds.contains(",")){
                    ToastUtils.showLong("无对比房源");
                    return;
                }
                mIntent = new Intent(this,ZuFangFybdActivity.class);
                mIntent.putExtra("houseNames",houseNames);
                mIntent.putExtra("houseIds",houseIds);
                startActivity(mIntent);
                break;
        }
    }
}



