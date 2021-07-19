package com.jxxx.zf.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxxx.zf.R;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.MagicIndicatorUtils;
import com.jxxx.zf.view.fragment.MineFylrFragment;
import com.jxxx.zf.view.fragment.MineJieDan1Fragment;
import com.jxxx.zf.view.fragment.ZuFangYyxq1Fragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MineFylrActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.ll_b)
    LinearLayout ll_b;
    @BindView(R.id.iv_add_fy)
    ImageView mIvAddFy;

    boolean isBianJi = false;

    private final String[] CHANNELS = new String[]{"待受理", "已发布", "审核失败"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_fylr;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "房源状态","批量下架");
        MagicIndicatorUtils.initMagicIndicator_1(this, mDataList, mMagicIndicator, mViewPager);
        initVP();
        mIvAddFy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseStartActivity(ZuFangFaBuActivity.class,null);
            }
        });
    }

    @Override
    protected void onTitleRightClickListener() {
        super.onTitleRightClickListener();
        MineFylrFragment mMineFylrFragment= (MineFylrFragment) fragments.get(mViewPager.getCurrentItem());
        if(isBianJi){
            isBianJi = false;
            ll_b.setVisibility(View.GONE);
        }else{
            isBianJi = true;
            ll_b.setVisibility(View.VISIBLE);
        }
        if(mMineFylrFragment!=null){
            mMineFylrFragment.setNotifyDataSetChanged(isBianJi);
        }
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


    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments() {

        Bundle mBundle1 = new Bundle();
        mBundle1.putString("status","0");
        MineFylrFragment mMineFylrFragment1 = new MineFylrFragment();
        mMineFylrFragment1.setArguments(mBundle1);
        fragments.add(mMineFylrFragment1);

        Bundle mBundle2 = new Bundle();
        mBundle2.putString("status","4");
        MineFylrFragment mMineFylrFragment2 = new MineFylrFragment();
        mMineFylrFragment2.setArguments(mBundle2);
        fragments.add(mMineFylrFragment2);

        Bundle mBundle3 = new Bundle();
        mBundle3.putString("status","3");
        MineFylrFragment mMineFylrFragment3 = new MineFylrFragment();
        mMineFylrFragment3.setArguments(mBundle3);
        fragments.add(mMineFylrFragment3);


        MineJieDan1Fragment mMineJieDan1Fragment1 = new MineJieDan1Fragment();
        return fragments;
    }
    @Override
    public void initData() {

    }

}
