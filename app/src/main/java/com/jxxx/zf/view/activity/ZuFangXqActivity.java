package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.IntentUtils;
import com.jxxx.zf.utils.StatusBarUtil;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.view.activity.mapAddress.GeoCoderUtil;
import com.jxxx.zf.view.activity.mapAddress.LatLngEntity;
import com.jxxx.zf.view.adapter.HomeFyAdapter;
import com.jxxx.zf.view.adapter.ZfxqFwssAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZuFangXqActivity extends BaseActivity {
    @BindView(R.id.mMapView)
    MapView mMapView;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_jin_e)
    TextView mTvJinE;
    @BindView(R.id.name_type)
    TextView mNameType;
    @BindView(R.id.tv_liulan)
    TextView mTvLiulan;
    @BindView(R.id.tv_commission)
    TextView mTvCommission;
    @BindView(R.id.mRecyclerView1)
    RecyclerView mMRecyclerView1;
    @BindView(R.id.mRecyclerView2)
    RecyclerView mMRecyclerView2;
    @BindView(R.id.btn_bd)
    TextView mBtnBd;
    @BindView(R.id.btn_zx)
    TextView mBtnZx;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.btn_yykf)
    TextView mBtnYykf;
    @BindView(R.id.ll_b)
    LinearLayout mLlB;
    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    @BindView(R.id.tv_houseType)
    TextView mTvHouseType;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    @BindView(R.id.tv_orientation)
    TextView mTvOrientation;
    @BindView(R.id.tv_lables1)
    TextView mTvLables1;
    @BindView(R.id.tv_lables2)
    TextView mTvLables2;
    @BindView(R.id.tv_lables3)
    TextView mTvLables3;
    @BindView(R.id.tv_fyxx_1)
    TextView mTvFyxx1;
    @BindView(R.id.tv_fyxx_2)
    TextView mTvFyxx2;
    @BindView(R.id.tv_fyxx_3)
    TextView mTvFyxx3;
    @BindView(R.id.tv_fyxx_4)
    TextView mTvFyxx4;
    @BindView(R.id.tv_fyxx_5)
    TextView mTvFyxx5;
    @BindView(R.id.tv_fyxx_6)
    TextView mTvFyxx6;
    @BindView(R.id.tv_fyxx_7)
    TextView mTvFyxx7;
    @BindView(R.id.tv_details)
    TextView mTvDetails;
    @BindView(R.id.tv_hasVideo)
    TextView tv_hasVideo;

    @Override
    public int intiLayout() {
        StatusBarUtil.setTranslucentStatus(this);
        return R.layout.activity_zu_fang_xq;
    }

    @Override
    public void initView() {

        initMap();
    }

    private void initMap() {
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        AMap aMap = mMapView.getMap();
        final LatLng latLng = new LatLng(29.8877248806424, 121.523712565104);

        GeoCoderUtil.getInstance(this).geoAddress(new LatLngEntity(latLng.latitude, latLng.longitude), new GeoCoderUtil.GeoCoderAddressListener() {
            @Override
            public void onAddressResult(String result) {
//                aMap.addMarker(new MarkerOptions().position(latLng).snippet(result)).showInfoWindow();
//                Log.w("result","result:"+result);
                aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromBitmap(
                        BitmapFactory.decodeResource(getResources(), R.mipmap.poi_marker_1)))
                        .position(latLng));

            }
        });
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
    }

    ZuFangDetailsBase data;
    @Override
    public void initData() {
        houseDetails();
    }

    private void houseDetails() {
        RetrofitUtil.getInstance().apiService()
                .houseDetails(getIntent().getStringExtra("id"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ZuFangDetailsBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ZuFangDetailsBase> result) {
                        hideLoading();
                        if (isResultOk(result)) {
                            data = result.getData();
                            if (data.getImgUrls() != null) {
                                bannerConfig(data.getImgUrls());
                            }
                            mTvName.setText(data.getRentingType().equals("1") ? "合租·" + data.getName() : "整租·" + data.getName());
                            String str = "<font color=\"#FF4040\"><big><big>" + data.getRent() + "</big></big></font>" + "元/月(" + StringUtil.getHouseRenting(data.getRentingType()) + ")";
                            mTvJinE.setText(Html.fromHtml(str));
                            mTvLiulan.setText("约看" + data.getViewNum() + "人");
                            mTvCommission.setText("房东出佣金" + data.getCommission() + "元为奖励");
                            mTvHouseType.setText(StringUtil.getHouseTypeStr(data.getHouseType()));
                            tv_hasVideo.setVisibility(View.GONE);
                            if(data.getHasVideo().equals("1")){
                                tv_hasVideo.setVisibility(View.VISIBLE);
                            }
                            mTvArea.setText(data.getArea() + "m²");
                            mTvOrientation.setText(StringUtil.getHouseOrientation(data.getOrientation()));
                            if (data.getLables() != null) {
                                for (int i = 0; i < data.getLables().size(); i++) {
                                    if (i == 0) {
                                        mTvLables1.setVisibility(View.VISIBLE);
                                        mTvLables1.setText(data.getLables().get(0).getName());
                                    }
                                    if (i == 1) {
                                        mTvLables2.setVisibility(View.VISIBLE);
                                        mTvLables2.setText(data.getLables().get(1).getName());
                                    }
                                    if (i == 2) {
                                        mTvLables3.setVisibility(View.VISIBLE);
                                        mTvLables3.setText(data.getLables().get(2).getName());
                                    }
                                }
                            }
                            mTvFyxx1.setText(data.getArea() + "m²");
                            mTvFyxx2.setText(data.getHousingEstateName());
                            mTvFyxx3.setText(data.getFloor());
                            switch (data.getRenovationType()) {
                                case "1":
                                    mTvFyxx4.setText("精装");
                                    break;
                                case "2":
                                    mTvFyxx4.setText("简装");
                                    break;
                                case "3":
                                    mTvFyxx4.setText("毛坯");
                                    break;
                            }
                            Drawable dra1 = getResources().getDrawable(R.drawable.group_829);
                            dra1.setBounds( 0, 0, dra1.getMinimumWidth(),dra1.getMinimumHeight());
                            mTvLiulan.setCompoundDrawables(null, dra1,null,null);
                            if(data.getIsCollection().equals("1")){
                                Drawable dra = getResources().getDrawable(R.drawable.group_886);
                                dra.setBounds( 0, 0, dra.getMinimumWidth(),dra.getMinimumHeight());
                                mTvLiulan.setCompoundDrawables(null, dra,null,null);
                            }
                            mTvFyxx5.setText(data.getHasElevator().equals("1") ? "是" : "否");
                            mTvFyxx6.setText(data.getHasParking().equals("1") ? "是" : "否");
                            mTvFyxx7.setText(data.getOpenHomeTime());
                            mMRecyclerView1.setAdapter(new ZfxqFwssAdapter(data.getParams()));
                            mTvDetails.setText(data.getDetails());
                            mMRecyclerView2.setAdapter(new HomeFyAdapter(data.getNearHouses()));
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

    private void bannerConfig(String imgUrls) {
        List<String> bannerImg = new ArrayList<>();
        String[] imgArr = imgUrls.split(",");
        mTvNum.setText("1/" + imgArr.length);
        for (int i = 0; i < imgArr.length; i++) {
            bannerImg.add(imgArr[i]);
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
                        mTvNum.setText(position + "/" + imgArr.length);
                    }
                })
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    @OnClick({R.id.bnt_fh, R.id.bnt_fx, R.id.tv_liulan, R.id.btn_bd, R.id.btn_zx, R.id.btn_yykf})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_fh:
                finish();
                break;
            case R.id.bnt_fx:
                break;
            case R.id.tv_liulan:
                houseDoCollection();
                break;
            case R.id.btn_bd:
                Intent mIntent = new Intent(this,ZuFangListBdActivity.class);
                mIntent.putExtra("data",data);
                startActivity(mIntent);
                break;
            case R.id.btn_zx:
                break;
            case R.id.btn_yykf:
                ZuFangYyActivity.startActivityYyUi(ZuFangXqActivity.this,data);
                break;
        }
    }
    private void houseDoCollection(){

        RetrofitUtil.getInstance().apiService()
                .houseDoCollection(getIntent().getStringExtra("id"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> result) {
                        if(isResultOk(result)){
                            data.setIsCollection(result.getData());
                            Drawable dra;
                            if(result.getData().equals("1")){
                                dra = getResources().getDrawable(R.drawable.group_886);
                                dra.setBounds( 0, 0, dra.getMinimumWidth(),dra.getMinimumHeight());
                            }else{
                                dra = getResources().getDrawable(R.drawable.group_829);
                                dra.setBounds( 0, 0, dra.getMinimumWidth(),dra.getMinimumHeight());
                            }
                            mTvLiulan.setCompoundDrawables(null, dra,null,null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
