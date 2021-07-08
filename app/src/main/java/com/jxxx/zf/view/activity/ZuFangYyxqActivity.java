package com.jxxx.zf.view.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.MagicIndicatorUtils;
import com.jxxx.zf.view.fragment.ZuFangYyxq1Fragment;
import com.jxxx.zf.view.fragment.ZuFangYyxq2Fragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZuFangYyxqActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private final String[] CHANNELS = new String[]{"订单详情", "订单状态"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    @Override
    public int intiLayout() {
        return R.layout.activity_zu_fang_yyxq;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "预约详情");
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
        Log.w("APPNAME_ENGLISH","APPNAME_ENGLISH:"+getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH));
        ZuFangYyxq1Fragment mZuFangYyxq1Fragment = new ZuFangYyxq1Fragment();
        mZuFangYyxq1Fragment.setArguments(mBundle);
        fragments.add(mZuFangYyxq1Fragment);

        ZuFangYyxq2Fragment mZuFangYyxq2Fragment = new ZuFangYyxq2Fragment();
        mZuFangYyxq2Fragment.setArguments(mBundle);
        fragments.add(mZuFangYyxq2Fragment);
        return fragments;
    }
    @Override
    public void initData() {
    }

}
