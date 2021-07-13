package com.jxxx.zf.view.fragment;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.utils.RadioGroupSelectUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import butterknife.BindView;

public class HomeTwoFragment_1 extends BaseFragment implements LocationSource{

    @BindView(R.id.mMapView)
    MapView mMapView;
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
    @BindView(R.id.address)
    TextView address;
    AMap aMap;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_two;
    }

    @Override
    protected void initView() {
        new RadioGroupSelectUtils().setOnChangeListener(getActivity(),
                mMRadioGroup, mRbHomeSelect1, mRbHomeSelect2, mRbHomeSelect3, mRbHomeSelect4, new RadioGroupSelectUtils.DialogInterface() {
                    @Override
                    public void btnConfirm(String str) {

                    }
                });
        initMap();
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                121.612899,29.842965
                LatLonPoint latLonPoint = new LatLonPoint(29.842965,121.612899);
                PoiItem poiItem = new PoiItem("孵化园", latLonPoint, "", "");
                if (poiItem != null) {
                    if (!AppUtils.isAppInstalled("com.autonavi.minimap")
                            && !AppUtils.isAppInstalled("com.baidu.BaiduMap")
                            && !AppUtils.isAppInstalled("com.tencent.map")) {
                        ToastUtils.showShort("当前未安装地图软件");
                    } else {
                        SwitchMapFragment switchMapFragment = SwitchMapFragment.newInstance(poiItem);
                        switchMapFragment.show(getActivity().getSupportFragmentManager(), "SwitchPayDialogFragment");
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void initMap() {
        //这个功能是去掉地图的logo和放大缩小图标
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        UiSettings mUiSettings = aMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(false);
//        mUiSettings.setAllGesturesEnabled(false);

        // 设置定位监听
        aMap.setLocationSource(this);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
//        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);


        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i("lgq","dianjiddd===="+marker.getPeriod());//获取markerID
//                getBoxReceive(marker.getPeriod()+"");
                return true;
            }
        });
//        getLotList(aMapLocation.getLongitude(),aMapLocation.getLatitude());
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }



    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    @Override
    public void activate(OnLocationChangedListener listener) {

        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (mListener != null && aMapLocation != null) {
                        Log.e("AmapErrsHA1",sHA1(getActivity()));
                        if (aMapLocation.getErrorCode() == 0) {
                            mlocationClient.stopLocation();
                            mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
//                            initUiD(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                        } else {
                            String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                            Log.e("AmapErr", errText);
                        }
                    }
                }
            });
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    private void initUiD(double lng, double lat) {
        //绘制适应大小
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
        boundsBuilder.include(new LatLng(lng, lat));

        LatLng latLng = new LatLng(lat, lng);
        MarkerOptions mMarkerOptions = new MarkerOptions().position(latLng);
        mMarkerOptions.icon(BitmapDescriptorFactory.
                fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_back)));
//        mMarkerOptions.period(Integer.valueOf(data.getId()));
        aMap.addMarker(mMarkerOptions);
        boundsBuilder.include(latLng);//把所有点都include进去（LatLng类型）

        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 200));//第二个参数为四周留空宽度
    }
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }
    public static String sHA1(Context context){
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length()-1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}



