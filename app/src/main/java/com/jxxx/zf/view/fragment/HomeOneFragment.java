package com.jxxx.zf.view.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.jxxx.zf.view.activity.WebViewActivity;
import com.jxxx.zf.view.activity.ZuFangListActivity;
import com.jxxx.zf.view.activity.search.SearchGoodsActivity;
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
            case "??????":
                rentingType = null;
                break;
            case "??????":
                rentingType = "2";
                break;
            case "??????":
                rentingType = "1";
                break;
            case "?????? ":
                rentBegin = null;
                rentEnd = null;
                break;
            case "500?????????":
                rentBegin = "0";
                rentEnd = "500";
                break;
            case "500-1000???":
                rentBegin = "500";
                rentEnd = "1000";
                break;
            case "1000-1500???":
                rentBegin = "1000";
                rentEnd = "1500";
                break;
            case "1500-2000???":
                rentBegin = "1500";
                rentEnd = "2000";
                break;
            case "2000-3000???":
                rentBegin = "2000";
                rentEnd = "3000";
                break;
            case "3000-5000???":
                rentBegin = "30000";
                rentEnd = "5000";
                break;
            case "5000?????????":
                rentBegin = "5000";
                break;
        }
        RetrofitUtil.getInstance().apiService()
                .HouseList(page, ConstValues.PAGE_SIZE,rentingType,rentBegin,rentEnd,null,null,null)
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
//                baseStartActivity(WebViewActivity.class);
//                openBrowser(getActivity(),"https://elastech.nbqichen.com/zhongkeyan/html/download/");
                break;
            case R.id.tv_search:
                baseStartActivity(SearchGoodsActivity.class,null);
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
        //???????????????????????????????????????????????????????????????????????????
        mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //????????????????????????????????????????????????
        mHomeBanner.setImageLoader(new GlideImageLoader());
        //????????????????????????????????????
        mHomeBanner.setImages(bannerImg);
        //?????????????????????????????????????????????????????????????????????????????????????????????
        mHomeBanner.setBannerAnimation(Transformer.Default);
        //??????????????????????????????
//        banner_theme.setBannerTitles(themeTitles);
        //????????????????????????
        mHomeBanner.setDelayTime(3000);
        //???????????????????????????????????????????????????
        mHomeBanner.isAutoPlay(true);
        //???????????????????????????????????????????????????
        mHomeBanner.setIndicatorGravity(BannerConfig.CENTER)
                //???????????????????????????????????????????????????????????????????????????????????????????????????
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                    }
                })
                //????????????????????????????????????????????????
                .start();
    }
}



