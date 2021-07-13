package com.jxxx.zf.view.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.utils.RadioGroupSelectUtils;
import com.jxxx.zf.utils.view.DrawerLayout;
import com.jxxx.zf.utils.view.MyRecyclerView;
import com.jxxx.zf.view.adapter.HomeFyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 1000D 订单管理
 */
public class HomeTwoFragment extends BaseFragment {


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
    @BindView(R.id.drawerHandle)
    TextView mDrawerHandle;
    @BindView(R.id.drawerContent)
    LinearLayout mDrawerContent;
    @BindView(R.id.drawer2)
    RelativeLayout mDrawer2;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.dial_drawer)
    DrawerLayout mDialDrawer;
    @BindView(R.id.rv_list)
    MyRecyclerView mRvList;
    @BindView(R.id.mMapView)
    MapView mMapView;
    AMap mAMap;
    private HomeFyAdapter mHomeFyAdapter;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_two;
    }

    @Override
    protected void initView() {
        new RadioGroupSelectUtils().setOnChangeListener(getActivity(),
                mMRadioGroup, mRbHomeSelect1, mRbHomeSelect2, mRbHomeSelect3, mRbHomeSelect4,
                new RadioGroupSelectUtils.DialogInterface() {
                    @Override
                    public void btnConfirm(String str) {

                    }
                });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        initMap();
        mDrawer2.setVisibility(View.INVISIBLE);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mHomeFyAdapter = new HomeFyAdapter(null);
        mRvList.setAdapter(mHomeFyAdapter);

        mDialDrawer.setInitialState(DrawerLayout.State.Close); //set drawer initial state: open or close
        mDialDrawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void drawerOpened() {

            }

            @Override
            public void drawerClosed() {

            }
        });
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IntentUtils.getInstence().intent(getActivity(), SearchGoodsActivity.class, "searchType", 2);
            }
        });

    }

    @Override
    protected void initData() {

    }
    private AMapLocationClient locationClient = null;//定位类
    private void initMap() {
        //这个功能是去掉地图的logo和放大缩小图标
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        //初始化client
        locationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        locationClient.startLocation();
    }

    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(false); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //解析定位结果
                String city = loc.getCity();
                Log.e("yufs","當前经度"+loc.getLongitude()+"当前维度："+loc.getLatitude());
//                initUiD(loc.getLatitude(), loc.getLongitude());

                //初始化地图对象
                if (mAMap == null) {
                    mAMap = mMapView.getMap();
                    mAMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            Log.i("lgq","dianjiddd===="+marker.getPeriod());//获取markerID
                            List<String> list = new ArrayList<>();
                            list.add("");
                            list.add("");
                            list.add("");
                            list.add("");
                            list.add("");
//                            mHomeFyAdapter.setNewData(list);
                            mDrawer2.setVisibility(View.VISIBLE);
                            return false;
                        }});
                    mAMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromView(getBubble("房子名称(5)套")))
                                                               .position(new LatLng(29.807416,121.560213)).period(5));

                    mAMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromBitmap(
                                                               BitmapFactory.decodeResource(getResources(), R.mipmap.poi_marker_1)))
                                                               .position(new LatLng(loc.getLatitude(), loc.getLongitude())));

                                                   }
                mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(loc.getLatitude(), loc.getLongitude()), 14));
            } else {
                Toast.makeText(getActivity(), "定位失败，请打开位置权限", Toast.LENGTH_SHORT).show();
            }
        }
    };
    public View getBubble(String userhead) {
        View view = this.getLayoutInflater().inflate(R.layout.layout_map_fy,null);
        TextView mTvFyName = view.findViewById(R.id.tv_fyName);
        mTvFyName.setText(userhead);
        return view;
    }

//    private void initUiD(double lat, double lng) {
//        //绘制适应大小
//        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
//        boundsBuilder.include(new LatLng(lat,lng));
//        for (int i = 0; i < infos.size(); i++) {
//            LotListBean.ListBean info = infos.get(i);
//            LatLng latLng = new LatLng(Double.valueOf(info.getLat()), Double.valueOf(info.getLng()));
//            MarkerOptions mMarkerOptions = new MarkerOptions().position(latLng);
//            mMarkerOptions.icon(BitmapDescriptorFactory.
//                    fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_tcc)));
//            mMarkerOptions.period(Integer.valueOf(info.getId()));
//            aMap.addMarker(mMarkerOptions);
//            boundsBuilder.include(latLng);//把所有点都include进去（LatLng类型）
//
//        }
//        mAMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 200));//第二个参数为四周留空宽度
//    }


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

}