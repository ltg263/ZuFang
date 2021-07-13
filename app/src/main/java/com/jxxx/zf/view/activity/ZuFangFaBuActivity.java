package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.HomeZuFangListBase;
import com.jxxx.zf.bean.HouseParamListBean;
import com.jxxx.zf.bean.ImageUrlBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.PickerViewUtils;
import com.jxxx.zf.utils.PictureSelectorUtils;
import com.jxxx.zf.view.adapter.AddImageAdapter;
import com.jxxx.zf.view.adapter.ZuFangFaBuXxTextAdapter;
import com.jxxx.zf.view.adapter.ZuFangFaBuXxTextSsAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    @BindView(R.id.home_banner_tv)
    TextView mHomeBannerTv;
    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_mj)
    EditText mEtMj;
    @BindView(R.id.et_wz)
    EditText mEtWz;
    @BindView(R.id.et_lc)
    EditText mEtLc;
    @BindView(R.id.bnt_zx)
    TextView mBntZx;
    @BindView(R.id.bnt_dt)
    TextView mBntDt;
    @BindView(R.id.bnt_cw)
    TextView mBntCw;
    @BindView(R.id.bnt_kfsj)
    TextView mBntKfsj;
    @BindView(R.id.et_contact)
    EditText mEtContact;

    List<ImageUrlBean> imgListTop = new ArrayList<>();
    List<ImageUrlBean> imgListB = new ArrayList<>();
    private AddImageAdapter addImageAdapterB;
    ZuFangFaBuXxTextAdapter mAdapter_fkfs, mAdapter_fwcx;
    ZuFangFaBuXxTextSsAdapter mAdapter_jcss;

    List<String> houseParams = new ArrayList<>();
    private boolean isTop = true;

    @Override
    public int intiLayout() {
        return R.layout.activity_zu_fang_fa_bu;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "录入房源");
        mAdapter_fwcx = new ZuFangFaBuXxTextAdapter(Arrays.asList(ConstValues.HOUSE_ORIENTATION));
        mRvListFwcx.setAdapter(mAdapter_fwcx);
        mAdapter_fwcx.setOnItemClickListener((adapter, view, position) -> {
            mAdapter_fwcx.setCurPos(position);
            mAdapter_fwcx.notifyDataSetChanged();
        });

        mAdapter_jcss = new ZuFangFaBuXxTextSsAdapter(null);
        mRvListQtss.setAdapter(mAdapter_jcss);
        mAdapter_jcss.setOnItemClickListener((adapter, view, position) -> {
            if (houseParams.contains(mAdapter_jcss.getData().get(position).getId())) {
                houseParams.remove(position);
            } else {
                houseParams.add(mAdapter_jcss.getData().get(position).getId());
            }
            ((TextView) view).setTextColor(getResources().getColor(R.color.white));
            ((TextView) view).setBackground(getResources().getDrawable(R.drawable.circle_solid_theme_25));
        });

        mAdapter_fkfs = new ZuFangFaBuXxTextAdapter(Arrays.asList(ConstValues.HOUSE_RENT_TYPE));
        mRvListFkfs.setAdapter(mAdapter_fkfs);
        mAdapter_fkfs.setOnItemClickListener((adapter, view, position) -> {
            mAdapter_fkfs.setCurPos(position);
            mAdapter_fkfs.notifyDataSetChanged();
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
                .subscribe(new Observer<Result<List<HouseParamListBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<HouseParamListBean>> result) {
                        hideLoading();
                        if (isResultOk(result) && result.getData() != null) {
                            mAdapter_jcss.setNewData(result.getData());
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
                default:
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
    @OnClick({R.id.home_banner_tv, R.id.bnt_zx, R.id.bnt_dt,R.id.bnt_cw,R.id.bnt_kfsj,R.id.bnt})
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
            case R.id.bnt_dt:
                pickerStrs.clear();
                pickerStrs.add("有");
                pickerStrs.add("无");
                PickerViewUtils.selectorCustom(this, pickerStrs, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mBntZx.setText(pickerStrs.get(pos));
                    }
                });
                break;
            case R.id.bnt_cw:
                pickerStrs.clear();
                pickerStrs.add("有");
                pickerStrs.add("无");
                PickerViewUtils.selectorCustom(this, pickerStrs, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mBntCw.setText(pickerStrs.get(pos));
                    }
                });
                break;
            case R.id.bnt_kfsj:
                PickerViewUtils.selectorDate(this, new boolean[]{true, true, true, false, false, false}, new PickerViewUtils.GetTimeInterface() {
                    @Override
                    public void getTime(Date time) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        mBntKfsj.setText(simpleDateFormat.format(time));
                    }
                });
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
        mZuFangDetailsBase.setAddress(mEtWz.getText().toString());
        String zx = mBntZx.getText().toString();
        if(zx.equals("精装")){
            mZuFangDetailsBase.setRenovationType("1");
        }else if(zx.equals("简装")){
            mZuFangDetailsBase.setRenovationType("2");
        }else if(zx.equals("毛坯")){
            mZuFangDetailsBase.setRenovationType("3");
        }
        mZuFangDetailsBase.setHasParking(mBntCw.getText().toString().equals("是")?"1":"0");
//        mZuFangDetailsBase.setmBntKfsj
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
