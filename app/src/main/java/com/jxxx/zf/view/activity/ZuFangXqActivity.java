package com.jxxx.zf.view.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.StatusBarUtil;
import com.jxxx.zf.view.activity.mapAddress.ActivitySearchLocation;
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
import butterknife.OnClick;

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
    @BindView(R.id.mRecyclerView1)
    RecyclerView mMRecyclerView1;
    @BindView(R.id.mRecyclerView2)
    RecyclerView mMRecyclerView2;
    @BindView(R.id.btn_bd)
    TextView mBtnBd;
    @BindView(R.id.btn_zx)
    TextView mBtnZx;
    @BindView(R.id.btn_yykf)
    TextView mBtnYykf;
    @BindView(R.id.ll_b)
    LinearLayout mLlB;
    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    private HomeFyAdapter mHomeFyAdapter;

    @Override
    public int intiLayout() {
        StatusBarUtil.setTranslucentStatus(this);
        return R.layout.activity_zu_fang_xq;
    }

    @Override
    public void initView() {

        initMap();

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
        mMRecyclerView1.setAdapter(new ZfxqFwssAdapter(list));

        mHomeFyAdapter = new HomeFyAdapter(null);
        mMRecyclerView2.setAdapter(mHomeFyAdapter);


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


    List<String> bannerImg = new ArrayList<>();
    @Override
    public void initData() {
        bannerImg.add("http://img.netbian.com/file/2021/0527/1f20f9804cb7390efc842f02f4765901.jpg");
        bannerImg.add("http://img.netbian.com/file/2021/0527/1f20f9804cb7390efc842f02f4765901.jpg");
        bannerImg.add("http://img.netbian.com/file/2021/0527/1f20f9804cb7390efc842f02f4765901.jpg");
        bannerImg.add("http://img.netbian.com/file/2021/0527/1f20f9804cb7390efc842f02f4765901.jpg");
        bannerConfig();
    }
    private void bannerConfig() {

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
    @OnClick({R.id.bnt_fh, R.id.bnt_fx, R.id.tv_liulan, R.id.btn_bd, R.id.btn_zx, R.id.btn_yykf})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_fh:
                finish();
                break;
            case R.id.bnt_fx:
                break;
            case R.id.tv_liulan:
                break;
            case R.id.btn_bd:
                baseStartActivity(ZuFangListBdActivity.class,null);
                break;
            case R.id.btn_zx:
                break;
            case R.id.btn_yykf:
                baseStartActivity(ZuFangYyActivity.class,null);
                break;
        }
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
