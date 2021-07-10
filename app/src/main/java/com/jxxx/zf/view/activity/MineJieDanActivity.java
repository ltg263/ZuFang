package com.jxxx.zf.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxxx.zf.R;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.MagicIndicatorUtils;
import com.jxxx.zf.view.fragment.MineJieDan1Fragment;
import com.jxxx.zf.view.fragment.ZuFangYyxq1Fragment;
import com.jxxx.zf.view.fragment.ZuFangYyxq2Fragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MineJieDanActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private final String[] CHANNELS = new String[]{"新订单", "已接单", "已完成"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_jie_dan;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "我的接单");
        MagicIndicatorUtils.initMagicIndicator_1(this, mDataList, mMagicIndicator, mViewPager);
        initVP();
    }
    private void initVP() {
        getFragments();
        mViewPager.setOffscreenPageLimit(CHANNELS.length);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }
        });

        mViewPager.setCurrentItem(0);
    }

    //1,待支付;2,待发货;3,待收货;4,待评价;5,已完成;6,已取消;7,已过期;8,已结束
    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments() {

        Bundle mBundle = new Bundle();
        mBundle.putString("id",getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH));

        MineJieDan1Fragment mMineJieDan1Fragment1 = new MineJieDan1Fragment();
        mMineJieDan1Fragment1.setArguments(mBundle);
        fragments.add(mMineJieDan1Fragment1);

        MineJieDan1Fragment mMineJieDan1Fragment2 = new MineJieDan1Fragment();
        mMineJieDan1Fragment2.setArguments(mBundle);
        fragments.add(mMineJieDan1Fragment2);

        MineJieDan1Fragment mMineJieDan1Fragment3 = new MineJieDan1Fragment();
        mMineJieDan1Fragment3.setArguments(mBundle);
        fragments.add(mMineJieDan1Fragment3);

        return fragments;
    }
    @Override
    public void initData() {

    }

}
