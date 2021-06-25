package com.jxxx.zf.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.MineZfBean;
import com.jxxx.zf.utils.view.RangeSeekBar;
import com.jxxx.zf.view.adapter.HomeMineZfAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class MineZfSelectActivity extends BaseActivity {
    @BindView(R.id.mRangeSeekBar)
    RangeSeekBar mRangeSeekBar;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.mRecyclerView1)
    RecyclerView mMRecyclerView1;
    @BindView(R.id.mRecyclerView2)
    RecyclerView mMRecyclerView2;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.mRecyclerView3)
    RecyclerView mMRecyclerView3;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_zf_select;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "我的租房");
        mRangeSeekBar.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(float lowerRange, float upperRange) {
                Log.w("onRangeChanged", "" + (int) lowerRange + "~" + (int) upperRange);
            }
        });
    }

    @Override
    public void initData() {
        ArrayList<MineZfBean> lists_hx = new ArrayList<>();
        lists_hx.add(new MineZfBean("整租",false));
        lists_hx.add(new MineZfBean("合租",false));
        lists_hx.add(new MineZfBean("不限",false));
        mMRecyclerView1.setAdapter(new HomeMineZfAdapter(lists_hx));

        ArrayList<MineZfBean> lists_js = new ArrayList<>();
        lists_js.add(new MineZfBean("一室",false));
        lists_js.add(new MineZfBean("二室",false));
        lists_js.add(new MineZfBean("三室",false));
        lists_js.add(new MineZfBean("三室+",false));
        mMRecyclerView2.setAdapter(new HomeMineZfAdapter(lists_js));

        ArrayList<MineZfBean> lists_qt = new ArrayList<>();
        lists_qt.add(new MineZfBean("独卫1",false));
        lists_qt.add(new MineZfBean("独卫2",false));
        lists_qt.add(new MineZfBean("独卫3",false));
        lists_qt.add(new MineZfBean("独卫4",false));
        lists_qt.add(new MineZfBean("独卫5",false));
        lists_qt.add(new MineZfBean("独卫6",false));
        lists_qt.add(new MineZfBean("独卫7",false));
        lists_qt.add(new MineZfBean("独卫8",false));
        lists_qt.add(new MineZfBean("独卫9",false));
        lists_qt.add(new MineZfBean("独卫10",false));
        lists_qt.add(new MineZfBean("独卫11",false));
        mMRecyclerView3.setAdapter(new HomeMineZfAdapter(lists_qt));
    }

    @OnClick({R.id.tv_address, R.id.iv_delete, R.id.tv_bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_address:
                break;
            case R.id.iv_delete:
                mTvAddress.setText("");
                break;
            case R.id.tv_bnt:
                baseStartActivity(ZuFangListActivity.class,null);
                break;
        }
    }
}
