package com.jxxx.zf.view.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.bean.HomeZuFangListBase;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.RadioGroupSelectUtils;
import com.jxxx.zf.view.activity.MineZfSelectActivity;
import com.jxxx.zf.view.activity.ZuFangListActivity;
import com.jxxx.zf.view.adapter.HomeFyAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeOneFragment extends BaseFragment {

    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    @BindView(R.id.rb_home_select1)
    RadioButton mRbHomeSelect1;
    @BindView(R.id.rb_home_select2)
    RadioButton mRbHomeSelect2;
    @BindView(R.id.rb_home_select3)
    RadioButton mRbHomeSelect3;
    @BindView(R.id.rb_home_select4)
    RadioButton mRbHomeSelect4;
    @BindView(R.id.mRadioGroup)
    RadioGroup mMRadioGroup;
    @BindView(R.id.rv_home_list)
    RecyclerView mRvHomeList;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    int page = 1;
    private HomeFyAdapter mHomeFyAdapter;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_one;
    }

    @Override
    protected void initView() {
        GlideImageLoader.loadImageViewRadiusNoCenter(getActivity(),"http://img.netbian.com/file/2021/0527/1f20f9804cb7390efc842f02f4765901.jpg",iv_icon);
        new RadioGroupSelectUtils().setOnChangeListener(getActivity(),
                mMRadioGroup, mRbHomeSelect1, mRbHomeSelect2, mRbHomeSelect3, mRbHomeSelect4
                , new RadioGroupSelectUtils.DialogInterface() {
                    @Override
                    public void btnConfirm(String str) {
                        page = 1;
                        HouseList(str);
                    }
                });


        mHomeFyAdapter = new HomeFyAdapter(null);
        mRvHomeList.setAdapter(mHomeFyAdapter);

        mHomeFyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }
    String rentingType = null;
    String rentBegin = null;
    String rentEnd = null;
    private void HouseList(String str){
        switch (str){
            case "不限":
                rentingType = null;
                break;
            case "整租":
                rentingType = "2";
                break;
            case "合租":
                rentingType = "1";
                break;
            case "不限 ":
                rentBegin = null;
                rentEnd = null;
                break;
            case "500元以下":
                rentBegin = "0";
                rentEnd = "500";
                break;
            case "500-1000元":
                rentBegin = "500";
                rentEnd = "1000";
                break;
            case "1000-1500元":
                rentBegin = "1000";
                rentEnd = "1500";
                break;
            case "1500-2000元":
                rentBegin = "1500";
                rentEnd = "2000";
                break;
            case "2000-3000元":
                rentBegin = "2000";
                rentEnd = "3000";
                break;
            case "3000-5000元":
                rentBegin = "30000";
                rentEnd = "5000";
                break;
            case "5000元以上":
                rentBegin = "5000";
                break;
        }
        RetrofitUtil.getInstance().apiService()
                .HouseList(page, ConstValues.PAGE_SIZE,rentingType,rentBegin,rentEnd,null,null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<HouseListBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<HouseListBase> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null){
                            if(result.getData().getList()!=null){
                                mHomeFyAdapter.setNewData(result.getData().getList());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        hideLoading();
                    }
                });
    }

    @Override
    protected void initData() {
        RetrofitUtil.getInstance().apiService()
                .homeGetHome()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<HomeZuFangListBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<HomeZuFangListBase> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null){
                            if(result.getData().getBanners()!=null){
                                bannerConfig(result.getData().getBanners());
                            }
                            if(result.getData().getHouses()!=null){
                                mHomeFyAdapter.setNewData(result.getData().getHouses());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        hideLoading();
                    }
                });
    }

    @OnClick({R.id.address, R.id.tv_search, R.id.ll_home_top1, R.id.ll_home_top2, R.id.ll_home_top3, R.id.ll_home_top4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.address:

                break;
            case R.id.tv_search:

                break;
            case R.id.ll_home_top1:
                baseStartActivity(ZuFangListActivity.class,null);
                break;
            case R.id.ll_home_top2:
                baseStartActivity(ZuFangListActivity.class,null);
                break;
            case R.id.ll_home_top3:
                baseStartActivity(ZuFangListActivity.class,null);
                break;
            case R.id.ll_home_top4:
                baseStartActivity(MineZfSelectActivity.class,null);
                break;
        }
    }

    private void bannerConfig(List<HomeZuFangListBase.BannersBean> banners) {

        List<String> bannerImg = new ArrayList<>();
        for(int i=0;i<banners.size();i++){
            bannerImg.add(banners.get(i).getImgUrl());
        }
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        mHomeBanner.setImageLoader(new GlideImageLoader());
        //设置图片网址或地址的集合
        mHomeBanner.setImages(bannerImg);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        mHomeBanner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
//        banner_theme.setBannerTitles(themeTitles);
        //设置轮播间隔时间
        mHomeBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        mHomeBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        mHomeBanner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                    }
                })
                //必须最后调用的方法，启动轮播图。
                .start();
    }
}



