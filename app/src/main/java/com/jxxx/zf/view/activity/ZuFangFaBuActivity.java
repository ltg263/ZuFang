package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.ImageUrlBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.PickerViewUtils;
import com.jxxx.zf.utils.PictureSelectorUtils;
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
        setToolbar(mMyToolbar, "录入房源");
        //租赁方式
        mAdapter_zlfs = new ZuFangFaBuXxTextAdapter(Arrays.asList(ConstValues.HOUSE_RENTING_TYPE));
        mAdapter_zlfs.setCurPos(0);
        mRvListZlfs.setAdapter(mAdapter_zlfs);
        mAdapter_zlfs.setOnItemClickListener((adapter, view, position) -> {
            mAdapter_zlfs.setCurPos(position);
            mAdapter_zlfs.notifyDataSetChanged();
        });

        //居室类型
        mAdapter_js = new ZuFangFaBuXxTextAdapter(Arrays.asList(ConstValues.HOUSE_TYPE));
        mAdapter_js.setCurPos(0);
        mRvListJs.setAdapter(mAdapter_js);
        mAdapter_js.setOnItemClickListener((adapter, view, position) -> {
            mAdapter_js.setCurPos(position);
            mAdapter_js.notifyDataSetChanged();
        });

        //房屋朝向
        mAdapter_fwcx = new ZuFangFaBuXxTextAdapter(Arrays.asList(ConstValues.HOUSE_ORIENTATION));
        mAdapter_fwcx.setCurPos(0);
        mRvListFwcx.setAdapter(mAdapter_fwcx);
        mAdapter_fwcx.setOnItemClickListener((adapter, view, position) -> {
            mAdapter_fwcx.setCurPos(position);
            mAdapter_fwcx.notifyDataSetChanged();
        });

        //其他设施
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

        //付款方式
        mAdapter_fkfs = new ZuFangFaBuXxTextAdapter(Arrays.asList(ConstValues.HOUSE_RENT_TYPE));
        mRvListFkfs.setAdapter(mAdapter_fkfs);
        mAdapter_fkfs.setOnItemClickListener((adapter, view, position) -> {
            mAdapter_fkfs.setCurPos(position);
            mAdapter_fkfs.notifyDataSetChanged();
        });

        //其他
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
//                        showLoading("上传图片中...");
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
    private void uploadImage(final String imgPath) {
        File file = new File(imgPath);//访问手机端的文件资源，保证手机端sdcdrd中必须有这个文件
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
    @OnClick({R.id.home_banner_tv, R.id.bnt_zx, R.id.iv_dt,R.id.iv_cw,R.id.tv_kfsj,R.id.et_wz,R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_banner_tv:
                isTop = true;
                PictureSelectorUtils.selectImage(ZuFangFaBuActivity.this, 5);
                break;
            case R.id.bnt_zx:
                pickerStrs.clear();
                pickerStrs.add("精装");
                pickerStrs.add("简装");
                pickerStrs.add("毛坯");
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
                pickerStrs.add("随时看房");
                pickerStrs.add("提前预约");
                PickerViewUtils.selectorCustom(this, pickerStrs, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mBntKfsj.setText(pickerStrs.get(pos));
                    }
                });
                break;
            case R.id.et_wz:
                ActivityUtils.startActivityForResult(this, ActivitySearchLocation.class, 3);
                break;
            case R.id.bnt:
                addUserHouse();
                break;
        }
    }

    private void addUserHouse() {
        ZuFangDetailsBase mZuFangDetailsBase = new ZuFangDetailsBase();
        mZuFangDetailsBase.setName(mEtName.getText().toString());
        mZuFangDetailsBase.setArea(mEtMj.getText().toString());
        mZuFangDetailsBase.setRent(et_zj.getText().toString());
        mZuFangDetailsBase.setFloor(et_lc.getText().toString());
        mZuFangDetailsBase.setOpenHomeTime(mBntKfsj.getText().toString());
        mZuFangDetailsBase.setAddress(mEtWz.getText().toString());
        String zx = mBntZx.getText().toString();
        if(zx.equals("精装")){
            mZuFangDetailsBase.setRenovationType("1");
        }else if(zx.equals("简装")){
            mZuFangDetailsBase.setRenovationType("2");
        }else if(zx.equals("毛坯")){
            mZuFangDetailsBase.setRenovationType("3");
        }
        mZuFangDetailsBase.setHasElevator(iv_dt.isSelected()?"1":"0");
        mZuFangDetailsBase.setHasParking(iv_cw.isSelected()?"1":"0");
        mZuFangDetailsBase.setAddress(mEtWz.getText().toString());
        mZuFangDetailsBase.setRentingType(mAdapter_zlfs.getCurPos()==0?"2":"1");
        mZuFangDetailsBase.setHouseType(String.valueOf(mAdapter_js.getCurPos()+1));
        mZuFangDetailsBase.setOrientation(String.valueOf(mAdapter_fwcx.getCurPos()+1));
//        mZuFangDetailsBase.setParams(houseParams);
        String houseParamsStr = "";
        for(int i= 0;i<houseParams.size();i++){
            if(i==houseParams.size()-1){
                houseParamsStr = houseParams.get(i).getId();
            }else{
                houseParamsStr = houseParams.get(i).getId()+",";
            }
        }
        mZuFangDetailsBase.setHouseParams(houseParamsStr);

//        mZuFangDetailsBase.setLables(houseLables);
        String houseLablesStr = "";
        for(int i= 0;i<houseLables.size();i++){
            if(i==houseParams.size()-1){
                houseLablesStr = houseLables.get(i).getId();
            }else{
                houseLablesStr = houseLables.get(i).getId()+",";
            }
        }
        mZuFangDetailsBase.setHouseLables(houseLablesStr);

        mZuFangDetailsBase.setRentType(String.valueOf(mAdapter_fkfs.getCurPos()+1));
        mZuFangDetailsBase.setDetails(mEtContact.getText().toString());
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
