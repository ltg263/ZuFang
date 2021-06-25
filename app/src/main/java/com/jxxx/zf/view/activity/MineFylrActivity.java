package com.jxxx.zf.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.MagicIndicatorUtils;
import com.jxxx.zf.view.fragment.MineFylrFragment;

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
        setToolbar(mMyToolbar, "房源录入","批量下架");
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
        fragments.add(new MineFylrFragment());
        fragments.add(new MineFylrFragment());
        fragments.add(new MineFylrFragment());
        return fragments;
    }
    @Override
    public void initData() {

    }

}
