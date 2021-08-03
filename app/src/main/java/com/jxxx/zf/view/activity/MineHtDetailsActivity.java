package com.jxxx.zf.view.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.UserContractDetailsBean;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineHtDetailsActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.tv_htbh)
    TextView mTvHtbh;
    @BindView(R.id.tv_qyzt)
    TextView mTvQyzt;
    @BindView(R.id.head_icon)
    ImageView mHeadIcon;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.year)
    TextView mYear;
    @BindView(R.id.tv_llcs)
    TextView mTvLlcs;
    @BindView(R.id.tv_fwdz)
    TextView mTvFwdz;
    @BindView(R.id.tv_htrq)
    TextView mTvHtrq;
    @BindView(R.id.tv_fkfs)
    TextView mTvFkfs;
    @BindView(R.id.tv_zj)
    TextView mTvZj;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_details;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "我的合同");

    }

    @Override
    public void initData() {
        RetrofitUtil.getInstance().apiService()
                .getUserContractDetails(getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserContractDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserContractDetailsBean> result) {
                        hideLoading();
                        if (isResultOk(result) && result.getData() != null) {
                            initUi(result.getData());
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

    private void initUi(UserContractDetailsBean data) {
        mTvHtbh.setText("合同编号：" + data.getCertificateNumber());
        mTvQyzt.setText(data.getStatusStr());
        GlideImageLoader.loadImageViewRadius(this, data.getHouseImgUrl(), mHeadIcon);
        mTvTitle.setText(data.getHouseName());
        mYear.setText(data.getHouseAttribute());
        mTvLlcs.setText(data.getStartTime().replace(" 00:00:00", "") + "-" + data.getEndTime().replace(" 00:00:00", ""));
        mTvFwdz.setText(data.getHouseAddress());
        mTvHtrq.setText(data.getCreateTime());
        mTvFkfs.setText(StringUtil.getHouseRenting(data.getRentType()));
        mTvZj.setText(data.getRentAmount());
    }

    @OnClick({R.id.bnt_jy, R.id.bnt_xy, R.id.bnt_zd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_jy:
                baseStartActivity(MineHtJyActivity.class, null);
                break;
            case R.id.bnt_xy:
                baseStartActivity(MineHtNew1Activity.class, null);
                break;
            case R.id.bnt_zd:
                baseStartActivity(MineJfzdActivity.class, null);
                break;
        }
    }
}
