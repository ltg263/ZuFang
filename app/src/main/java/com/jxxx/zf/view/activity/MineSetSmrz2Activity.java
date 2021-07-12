package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.MainActivity;
import com.jxxx.zf.R;
import com.jxxx.zf.api.HttpsUtils;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.ApplyInfoBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.PictureSelectorUtils;
import com.jxxx.zf.utils.SharedUtils;
import com.jxxx.zf.utils.StringUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 实名认证
 * 编号：6140
 */
public class MineSetSmrz2Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;

    String smrz_name,smrz_url_zm, smrz_number, smrz_url_fm;
    @BindView(R.id.iv_zm)
    ImageView mIvZm;
    @BindView(R.id.iv_cm)
    ImageView mIvCm;

    boolean isTop = true;
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_set_smrz2;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "实名认证");
        smrz_name = getIntent().getStringExtra("smrz_name");
        smrz_number = getIntent().getStringExtra("smrz_number");
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.iv_zm, R.id.iv_cm,R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_zm:
                isTop = true;
                PictureSelectorUtils.selectImage(this,1);
                break;
            case R.id.iv_cm:
                isTop = false;
                PictureSelectorUtils.selectImage(this,1);
                break;
            case R.id.bnt:
                if(StringUtil.isBlank(smrz_url_zm)||StringUtil.isBlank(smrz_url_fm)){
                    ToastUtils.showLong("请上传省份证照片");
                    return;
                }
                realNameAuthentication();
                break;
        }
    }

    private void realNameAuthentication() {
        ApplyInfoBean.RealNameAuthentication mRealNameAuthentication = new ApplyInfoBean.RealNameAuthentication();
        mRealNameAuthentication.setBackImg(smrz_url_fm);
        mRealNameAuthentication.setCardNo(smrz_number);
        mRealNameAuthentication.setFrontImg(smrz_url_zm);
//        mRealNameAuthentication.setRealImg(smrz_url_zm);
        mRealNameAuthentication.setNickname(SharedUtils.singleton().get(ConstValues.NICK_NAME,""));
        mRealNameAuthentication.setRealName(smrz_name);
        RetrofitUtil.getInstance().apiService()
                .realNameAuthentication(mRealNameAuthentication)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        hideLoading();
                        if(isResultOk(result)){
                            finish();
                            baseStartActivity(MainActivity.class, null);
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
                    if(selectList!=null){
                        if(isTop){
                            smrz_url_zm = selectList.get(0).getCompressPath();
                            GlideImageLoader.loadImageViewRadius(MineSetSmrz2Activity.this,
                                    selectList.get(0).getCompressPath(),15,mIvZm);
                        }else{
                            smrz_url_fm = selectList.get(0).getCompressPath();
                            GlideImageLoader.loadImageViewRadius(MineSetSmrz2Activity.this,
                                    selectList.get(0).getCompressPath(),15,mIvCm);
                        }
                        HttpsUtils.uploadFiles(selectList.get(0).getCompressPath(), new HttpsUtils.UploadFileInterface() {
                            @Override
                            public void succeed(String path) {
                                Log.w("path","path:"+path);
                            }

                            @Override
                            public void failure() {

                            }
                        });
                    }
                    break;
                default:
            }
        }
    }
}
