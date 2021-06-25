package com.jxxx.zf.view.activity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.view.GridDivider;
import com.jxxx.zf.view.adapter.ZfxqFwssAdapter;
import com.jxxx.zf.view.adapter.ZuFangDbAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ZuFangFybdActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @Override
    public int intiLayout() {
        return R.layout.activity_zu_fang_fybd;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "房源对比");

    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("WIFI");
        list.add("床");
        list.add("衣柜");
        list.add("沙发");
        list.add("桌椅");
        list.add("洗衣机");
        list.add("冰箱");
        list.add("暖气");
        list.add("热水器");
        list.add("做饭");
        list.add("电视");
        list.add("空调");
        list.add("阳台");
        list.add("WIFI");
        list.add("床");
        list.add("衣柜");
        list.add("沙发");
        list.add("桌椅");
        list.add("洗衣机");
        list.add("冰箱");
        list.add("暖气");
        list.add("热水器");
        list.add("做饭");
        list.add("电视");
        list.add("空调");
        list.add("阳台");
        list.add("WIFI");
        list.add("床");
        list.add("衣柜");
        list.add("沙发");
        list.add("桌椅");
        list.add("洗衣机");
        list.add("冰箱");
        list.add("暖气");
        list.add("热水器");
        list.add("做饭");
        list.add("电视");
        list.add("空调");
        list.add("阳台");
        mRvList.setAdapter(new ZuFangDbAdapter(list));
        mRvList.addItemDecoration(new GridDivider(this, 1, 5,this.getResources().getColor(R.color.colorAccent)));
    }
}
