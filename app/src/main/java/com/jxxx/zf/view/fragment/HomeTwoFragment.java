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
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.utils.RadioGroupSelectUtils;
import com.jxxx.zf.utils.SharedUtils;
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
    private void initMap() {
        //这个功能是去掉地图的logo和放大缩小图标
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
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

        String lon = SharedUtils.singleton().get(ConstValues.LOCATION_LONGITUDE, "");
        String lat = SharedUtils.singleton().get(ConstValues.LOCATION_LATITUDE, "");

        mAMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(getResources(), R.mipmap.poi_marker_1)))
                .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon))));

        mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)), 14));
    }

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