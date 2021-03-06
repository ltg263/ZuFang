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

    }

    private void initMap(String lat, String lon) {
        //???activity??????onCreate?????????mMapView.onCreate(savedInstanceState)???????????????
        mMapView.onCreate(savedInstanceState);
        AMap aMap = mMapView.getMap();
        final LatLng latLng = new LatLng(Double.valueOf(lat), Double.valueOf(lon));

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
        showLoading();
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

                            initMap(data.getLat(),data.getLon());
                            String rentingType = StringUtil.getRentingType(data.getRentingType());
                            mTvName.setText(rentingType + data.getName());
                            String str = "<font color=\"#FF4040\"><big><big>" + data.getRent() + "</big></big></font>" + "???/???(" + StringUtil.getHouseRenting(data.getRentingType()) + ")";
                            mTvJinE.setText(Html.fromHtml(str));
                            mTvLiulan.setText("??????" + data.getViewNum() + "???");
                            mTvCommission.setText("???????????????" + data.getCommission() + "????????????");
                            mTvHouseType.setText(StringUtil.getHouseTypeStr(data.getHouseType()));
                            tv_hasVideo.setVisibility(View.GONE);
                            if(data.getHasVideo().equals("1")){
                                tv_hasVideo.setVisibility(View.VISIBLE);
                            }
                            mTvArea.setText(data.getArea() + "m??");
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
                            mTvFyxx1.setText(data.getArea() + "m??");
                            mTvFyxx2.setText(data.getHousingEstateName());
                            mTvFyxx3.setText(data.getFloor());
                            switch (data.getRenovationType()) {
                                case "1":
                                    mTvFyxx4.setText("??????");
                                    break;
                                case "2":
                                    mTvFyxx4.setText("??????");
                                    break;
                                case "3":
                                    mTvFyxx4.setText("??????");
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
                            mTvFyxx5.setText(data.getHasElevator().equals("1") ? "???" : "???");
                            mTvFyxx6.setText(data.getHasParking().equals("1") ? "???" : "???");
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
                        mTvNum.setText(position + "/" + imgArr.length);
                    }
                })
                //????????????????????????????????????????????????
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
        //???activity??????onDestroy?????????mMapView.onDestroy()???????????????
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //???activity??????onResume?????????mMapView.onResume ()???????????????????????????
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //???activity??????onPause?????????mMapView.onPause ()????????????????????????
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //???activity??????onSaveInstanceState?????????mMapView.onSaveInstanceState (outState)??????????????????????????????
        mMapView.onSaveInstanceState(outState);
    }
}
