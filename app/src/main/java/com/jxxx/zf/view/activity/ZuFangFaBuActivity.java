package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.HouseEstateList;
import com.jxxx.zf.bean.ImageUrlBean;
import com.jxxx.zf.bean.RegionsStreetBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.AddressPickTask;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.PickerViewUtils;
import com.jxxx.zf.utils.PictureSelectorUtils;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.utils.ToastUtil;
import com.jxxx.zf.view.activity.mapAddress.ActivitySearchLocation;
import com.jxxx.zf.view.adapter.AddImageAdapter;
import com.jxxx.zf.view.adapter.ZuFangFaBuXxTextAdapter;
import com.jxxx.zf.view.adapter.ZuFangFaBuXxTextLablesAdapter;
import com.jxxx.zf.view.adapter.ZuFangFaBuXxTextSsAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ZuFangFaBuActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list_qtss)
    RecyclerView mRvListQtss;
    @BindView(R.id.rv_list_fwcx)
    RecyclerView mRvListFwcx;
    @BindView(R.id.rv_list_fkfs)
    RecyclerView mRvListFkfs;
    @BindView(R.id.rv_list_fwtp)
    RecyclerView mRvListFwtp;
    @BindView(R.id.rv_list_zlfs)
    RecyclerView mRvListZlfs;
    @BindView(R.id.rv_list_js)
    RecyclerView mRvListJs;
    @BindView(R.id.rv_list_qt)
    RecyclerView mRvListQt;
    @BindView(R.id.home_banner_tv)
    TextView mHomeBannerTv;
    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_mj)
    EditText mEtMj;
    @BindView(R.id.et_zj)
    EditText et_zj;
    @BindView(R.id.et_lc)
    EditText et_lc;
    @BindView(R.id.et_jd)
    TextView et_jd;
    @BindView(R.id.et_wz)
    TextView mEtWz;
    @BindView(R.id.bnt_zx)
    TextView mBntZx;
    @BindView(R.id.iv_dt)
    ImageView iv_dt;
    @BindView(R.id.iv_cw)
    ImageView iv_cw;
    @BindView(R.id.tv_kfsj)
    TextView mBntKfsj;
    @BindView(R.id.et_qy)
    TextView et_qy;
    @BindView(R.id.et_contact)
    EditText mEtContact;
//5200
    List<ImageUrlBean> imgListTop = new ArrayList<>();
    List<ImageUrlBean> imgListB = new ArrayList<>();
    private AddImageAdapter addImageAdapterB;
    ZuFangFaBuXxTextAdapter mAdapter_zlfs,mAdapter_js, mAdapter_fkfs,mAdapter_fwcx;
    ZuFangFaBuXxTextSsAdapter mAdapter_qtss;
    ZuFangFaBuXxTextLablesAdapter mAdapter_qt;

    List<ZuFangDetailsBase.ParamsBean> houseParams = new ArrayList<>();
    List<ZuFangDetailsBase.LablesBean> houseLables = new ArrayList<>();
    private boolean isTop = true;

    @Override
    public int intiLayout() {
        return R.layout.activity_zu_fang_fa_bu;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "????????????");
        ZuFangDetailsBase mZuFangDetailsBase = getIntent().getParcelableExtra("ZuFangDetailsBase");
        //????????????
        mAdapter_zlfs = new ZuFangFaBuXxTextAdapter(Arrays.asList(ConstValues.HOUSE_RENTING_TYPE));
        mAdapter_zlfs.setCurPos(0);
        mRvListZlfs.setAdapter(mAdapter_zlfs);
        mAdapter_zlfs.setOnItemClickListener((adapter, view, position) -> {
            mAdapter_zlfs.setCurPos(position);
            mAdapter_zlfs.notifyDataSetChanged();
        });

        //????????????
        mAdapter_js = new ZuFangFaBuXxTextAdapter(Arrays.asList(ConstValues.HOUSE_TYPE));
        mAdapter_js.setCurPos(0);
        mRvListJs.setAdapter(mAdapter_js);
        mAdapter_js.setOnItemClickListener((adapter, view, position) -> {
            mAdapter_js.setCurPos(position);
            mAdapter_js.notifyDataSetChanged();
        });

        //????????????
        mAdapter_fwcx = new ZuFangFaBuXxTextAdapter(Arrays.asList(ConstValues.HOUSE_ORIENTATION));
        mAdapter_fwcx.setCurPos(0);
        mRvListFwcx.setAdapter(mAdapter_fwcx);
        mAdapter_fwcx.setOnItemClickListener((adapter, view, position) -> {
            mAdapter_fwcx.setCurPos(position);
            mAdapter_fwcx.notifyDataSetChanged();
        });

        //????????????
        mAdapter_qtss = new ZuFangFaBuXxTextSsAdapter(null,houseParams);
        mRvListQtss.setAdapter(mAdapter_qtss);
        mAdapter_qtss.setOnItemClickListener((adapter, view, position) -> {
            if (houseParams.contains(mAdapter_qtss.getData().get(position))){
                houseParams.remove(mAdapter_qtss.getData().get(position));
            } else {
                houseParams.add(mAdapter_qtss.getData().get(position));
            }
            mAdapter_qtss.notifyDataSetChanged();
        });

        //????????????
        mAdapter_fkfs = new ZuFangFaBuXxTextAdapter(Arrays.asList(ConstValues.HOUSE_RENT_TYPE));
        mRvListFkfs.setAdapter(mAdapter_fkfs);
        mAdapter_fkfs.setOnItemClickListener((adapter, view, position) -> {
            mAdapter_fkfs.setCurPos(position);
            mAdapter_fkfs.notifyDataSetChanged();
        });

        //??????
        mAdapter_qt = new ZuFangFaBuXxTextLablesAdapter(null,houseLables);
        mRvListQt.setAdapter(mAdapter_qt);
        mAdapter_qt.setOnItemClickListener((adapter, view, position) -> {
            if (houseLables.contains(mAdapter_qt.getData().get(position))){
                houseLables.remove(mAdapter_qt.getData().get(position));
            } else {
                houseLables.add(mAdapter_qt.getData().get(position));
            }
            mAdapter_qt.notifyDataSetChanged();
        });

        imgListB.add(null);
        addImageAdapterB = new AddImageAdapter(imgListB);
        mRvListFwtp.setAdapter(addImageAdapterB);

        addImageAdapterB.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (addImageAdapterB.getData().get(position) == null) {
                    isTop = false;
                    PictureSelectorUtils.selectImage(ZuFangFaBuActivity.this, 10);
                }
            }
        });

        addImageAdapterB.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    addImageAdapterB.remove(position);
                    imgListB.add(null);
                    addImageAdapterB.notifyDataSetChanged();
                } catch (Exception e) {
                }
            }
        });
        if(mZuFangDetailsBase!=null){
            this.mZuFangDetailsBase = mZuFangDetailsBase;
            mEtName.setText(mZuFangDetailsBase.getName());
        }
    }

    @Override
    public void initData() {
        RetrofitUtil.getInstance().apiService()
                .getHouseParamAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<ZuFangDetailsBase.ParamsBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<ZuFangDetailsBase.ParamsBean>> result) {
                        hideLoading();
                        if (isResultOk(result) && result.getData() != null) {
                            mAdapter_qtss.setNewData(result.getData());
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

        RetrofitUtil.getInstance().apiService()
                .getHouseLable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<ZuFangDetailsBase.LablesBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<ZuFangDetailsBase.LablesBean>> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null) {
                            mAdapter_qt.setNewData(result.getData());
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);

//                    addImageAdapter.setNewData(selectList);
//                    imageList.addAll(selectList);
//                    addImageAdapter.notifyDataSetChanged();
//                    if (selectList.size() != 0) {
//                        showLoading("???????????????...");
//                        imgc = selectList.size();
//                        x = 0;
//                    }
                    for (LocalMedia media : selectList) {
                        String imagepath = media.getCompressPath();
                        if (isTop) {
                            imgListTop.add(new ImageUrlBean(imagepath));
                        } else {
                            imgListB.add(imgListB.size() - 1, new ImageUrlBean(imagepath));
                        }
//                        uploadImage(imagepath);
                    }
                    if (isTop) {
                        bannerConfig();

                    } else {
                        if (imgListB.size() >= 11) {
                            imgListB.remove(null);
                        }
                        addImageAdapterB.notifyDataSetChanged();
                    }

                    break;
                case 3:
                        mEtWz.setText(data.getStringExtra("address"));
//                        latLng = data.getParcelableExtra("lat");
                    break;
            }
        }
    }

    private void bannerConfig() {

        List<String> bannerImg = new ArrayList<>();
        for(int i=0;i<imgListTop.size();i++){
            bannerImg.add(imgListTop.get(i).getImgUrl());
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
    private void uploadImage(final String imgPath) {
        File file = new File(imgPath);//????????????????????????????????????????????????sdcdrd????????????????????????
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file1", file.getName(), requestFile);

//        Map<String, RequestBody> map = new HashMap<>();
//        int fileType = 2;
//        map.put("fileType", RequestBody.create(null, String.valueOf(fileType)));

//        RetrofitManager.build().uploadImage(body)
//                .compose(RxSchedulers.<BaseData<String>>compose())
//                .as(AutoDispose.<BaseData<String>>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
//                .subscribe(
//                        new BaseObserver<String>() {
//                            @Override
//                            public void onHandleSuccess(BaseData<String> t) throws Exception {
//                                imgList.add(new ImageUrlBean(t.getData()));
//                                addImageAdapter.notifyDataSetChanged();
//                                ++x;
//                                if (x == imgc)
//                                    hideLoading();
//                            }
//
//                            @Override
//                            public void onHandleError(String msg) {
//                                hideLoading();
//                                super.onHandleError(msg);
//                            }
//                        }
//                );
    }

    List<String> pickerStrs = new ArrayList<>();
    @OnClick({R.id.home_banner_tv, R.id.bnt_zx, R.id.iv_dt,R.id.iv_cw,R.id.tv_kfsj,R.id.et_wz,R.id.et_jd,R.id.et_qy,R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_banner_tv:
                isTop = true;
                PictureSelectorUtils.selectImage(ZuFangFaBuActivity.this, 5);
                break;
            case R.id.bnt_zx:
                pickerStrs.clear();
                pickerStrs.add("??????");
                pickerStrs.add("??????");
                pickerStrs.add("??????");
                PickerViewUtils.selectorCustom(this, pickerStrs, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mBntZx.setText(pickerStrs.get(pos));
                    }
                });
                break;
            case R.id.iv_dt:
                iv_dt.setSelected(!iv_dt.isSelected());
                break;
            case R.id.iv_cw:
                iv_cw.setSelected(!iv_cw.isSelected());
                break;
            case R.id.tv_kfsj:
                pickerStrs.clear();
                pickerStrs.add("????????????");
                pickerStrs.add("????????????");
                PickerViewUtils.selectorCustom(this, pickerStrs, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mBntKfsj.setText(pickerStrs.get(pos));
                    }
                });
                break;
            case R.id.et_wz:
                if(StringUtil.isBlank(streetId)){
                    ToastUtils.showLong("???????????????");
                    return;
                }
                getHousingEstateList();
//                ActivityUtils.startActivityForResult(this, ActivitySearchLocation.class, 3);
                break;
            case R.id.et_jd:
                if(StringUtil.isBlank(districtId)){
                    ToastUtils.showLong("???????????????");
                    return;
                }
                getStreet();
                break;
            case R.id.et_qy:
                onAddressPicker();
                break;
            case R.id.bnt:
                addUserHouse();
                break;
        }
    }
    String districtId,streetId;
    public void onAddressPicker() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                ToastUtils.showLong("?????????????????????");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                streetId = null;
                et_jd.setText("");
                mEtWz.setText("");
                mZuFangDetailsBase.setProvinceId(province.getAreaId());
                mZuFangDetailsBase.setCityId(city.getAreaId());
                if (county == null) {
                    et_qy.setText(province.getAreaName() + "," + city.getAreaName());
                    districtId = city.getAreaId();
                } else {
                    et_qy.setText(province.getAreaName() + "," + city.getAreaName() + "," + county.getAreaName());
                    districtId = county.getAreaId();
                    mZuFangDetailsBase.setDistrictId(county.getAreaId());
                }
            }
        });
        task.execute("??????", "??????", "??????");
    }

    private void getStreet() {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .getStreet(districtId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<RegionsStreetBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<RegionsStreetBean>> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null) {
                            pickerStrs.clear();
                            for(int i = 0;i<result.getData().size();i++){
                                pickerStrs.add(result.getData().get(i).getStreetName());
                            }
                            if(pickerStrs.size()==0){
                                ToastUtils.showLong("????????????");
                                return;
                            }
                            PickerViewUtils.selectorCustom(ZuFangFaBuActivity.this, pickerStrs,
                                    "", new PickerViewUtils.ConditionInterfacd() {
                                @Override
                                public void setIndex(int pos) {
                                    streetId = result.getData().get(pos).getStreetId();
                                    et_jd.setText(pickerStrs.get(pos));
                                    mZuFangDetailsBase.setStreetId(result.getData().get(pos).getStreetId());
                                    mZuFangDetailsBase.setLat(result.getData().get(pos).getLat());
                                    mZuFangDetailsBase.setLon(result.getData().get(pos).getLng());

                                }
                            });
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

    private void getHousingEstateList() {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .getHousingEstateList(streetId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<HouseEstateList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<HouseEstateList> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null) {
                            pickerStrs.clear();
                            for(int i = 0;i<result.getData().getList().size();i++){
                                pickerStrs.add(result.getData().getList().get(i).getEstateName());
                            }
                            if(pickerStrs.size()==0){
                                ToastUtils.showLong("????????????");
                                return;
                            }
                            PickerViewUtils.selectorCustom(ZuFangFaBuActivity.this, pickerStrs,
                                    "", new PickerViewUtils.ConditionInterfacd() {
                                @Override
                                public void setIndex(int pos) {
                                    mEtWz.setText(pickerStrs.get(pos));
                                    mZuFangDetailsBase.setHouseEstateId(result.getData().getList().get(pos).getId());
                                }
                            });
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

    ZuFangDetailsBase mZuFangDetailsBase = new ZuFangDetailsBase();
    private void addUserHouse() {
        mZuFangDetailsBase.setName(mEtName.getText().toString());
        mZuFangDetailsBase.setArea(mEtMj.getText().toString());
        mZuFangDetailsBase.setRent(et_zj.getText().toString());
        mZuFangDetailsBase.setFloor(et_lc.getText().toString());
        mZuFangDetailsBase.setOpenHomeTime(mBntKfsj.getText().toString());
        mZuFangDetailsBase.setAddress(mEtWz.getText().toString());
        String zx = mBntZx.getText().toString();
        if(zx.equals("??????")){
            mZuFangDetailsBase.setRenovationType("1");
        }else if(zx.equals("??????")){
            mZuFangDetailsBase.setRenovationType("2");
        }else if(zx.equals("??????")){
            mZuFangDetailsBase.setRenovationType("3");
        }
        mZuFangDetailsBase.setHasElevator(iv_dt.isSelected()?"1":"0");
        mZuFangDetailsBase.setHasParking(iv_cw.isSelected()?"1":"0");
        mZuFangDetailsBase.setAddress(mEtWz.getText().toString());
        mZuFangDetailsBase.setRentingType(mAdapter_zlfs.getCurPos()==0?"2":"1");
        mZuFangDetailsBase.setHouseType(String.valueOf(mAdapter_js.getCurPos()+1));
        mZuFangDetailsBase.setOrientation(String.valueOf(mAdapter_fwcx.getCurPos()+1));

        String houseParamsStr = "";
        for(int i= 0;i<houseParams.size();i++){
            if(i==houseParams.size()-1){
                houseParamsStr = houseParamsStr+houseParams.get(i).getId();
            }else{
                houseParamsStr = houseParamsStr+houseParams.get(i).getId()+",";
            }
        }
//      mZuFangDetailsBase.setParams(houseParams);
        mZuFangDetailsBase.setHouseParams(houseParamsStr);

        String houseLablesStr = "";
        for(int i= 0;i<houseLables.size();i++){
            if(i==houseParams.size()-1){
                houseLablesStr = houseLablesStr+houseLables.get(i).getId();
            }else{
                houseLablesStr = houseLablesStr+houseLables.get(i).getId()+",";
            }
        }
//      mZuFangDetailsBase.setLables(houseLables);
        mZuFangDetailsBase.setHouseLables(houseLablesStr);

        mZuFangDetailsBase.setRentType(String.valueOf(mAdapter_fkfs.getCurPos()+1));
        mZuFangDetailsBase.setDetails(mEtContact.getText().toString());
        Log.w("--->>>>","mZuFangDetailsBase:"+mZuFangDetailsBase.toString());
        RetrofitUtil.getInstance().apiService()
                .addUserHouse(mZuFangDetailsBase)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        hideLoading();
                        if (isResultOk(result) && result.getData() != null) {

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
}
