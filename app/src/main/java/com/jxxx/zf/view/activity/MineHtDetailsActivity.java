package com.jxxx.zf.view.activity;


import android.content.Intent;
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
    @BindView(R.id.tv_htlx)
    TextView mTvHtlx;
    @BindView(R.id.tv_fkfs)
    TextView mTvFkfs;
    @BindView(R.id.tv_zj)
    TextView mTvZj;
    @BindView(R.id.bnt_jy)
    TextView mBntJy;
    @BindView(R.id.bnt_xy)
    TextView mBntXy;
    @BindView(R.id.bnt_dzht)
    TextView mBntDzht;
    @BindView(R.id.bnt_zd)
    TextView mBntZd;
    @BindView(R.id.tv_yj)
    TextView tv_yj;
    @BindView(R.id.tv_lxr)
    TextView tv_lxr;
    @BindView(R.id.tv_lxdh)
    TextView tv_lxdh;
    UserContractDetailsBean data;
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
        showLoading();
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
                            data = result.getData();
                            initUi();
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

    private void initUi() {
        mTvHtbh.setText("合同编号：" + data.getCertificateNumber());
        mTvQyzt.setText(data.getStatusStr());
        GlideImageLoader.loadImageViewRadius(this, data.getHouseImgUrl(), mHeadIcon);
        mTvTitle.setText(data.getHouseName());
        mYear.setText(data.getHouseAttribute());
        mTvLlcs.setText(data.getStartTime().replace(" 00:00:00", "") + "~" + data.getEndTime().replace(" 00:00:00", ""));
        mTvFwdz.setText(data.getHouseAddress());
        if(StringUtil.isNotBlank(data.getContractNature())){
            if (data.getContractNature().equals("1")){
                mTvHtlx.setText(data.getContractType().equals("1")?"新签电子合同":"续签电子合同");
            }else{
                mTvHtlx.setText(data.getContractType().equals("1")?"新签纸质合同":"续签纸质合同");
            }
        }

        mTvFkfs.setText(StringUtil.getHouseRenting(data.getRentType()));
        mTvZj.setText(data.getRentAmount());
        tv_yj.setText(data.getDeposit());
        tv_lxr.setText("紧急联系人关系："+data.getEmergencyRelationship());
        tv_lxdh.setText("紧急联系人电话："+data.getEmergencyPhone());
        switch (data.getStatus()) {
            case "0":
                mBntXy.setVisibility(View.VISIBLE);
                mBntXy.setText("签约");
                break;
            case "1":
                mBntJy.setVisibility(View.VISIBLE);
                mBntJy.setText("解约");
                mBntXy.setVisibility(View.VISIBLE);
                mBntXy.setText("续约");
                mBntDzht.setVisibility(View.VISIBLE);
                break;
            case "2":
            case "3":
                mBntDzht.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.bnt_jy, R.id.bnt_xy, R.id.bnt_zd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_jy:
                baseStartActivity(MineHtJyActivity.class, data.getId());
                break;
            case R.id.bnt_xy:
                if(mBntXy.getText().equals("签约")){
                    Intent mIntent = new Intent(MineHtDetailsActivity.this,MineHtNew3Activity.class);
                    mIntent.putExtra("appointmentId",data.getAppointmentId());
                    startActivity(mIntent);
                    return;
                }else{
                    Intent mIntent = new Intent(MineHtDetailsActivity.this,MineHtXqActivity.class);
                    mIntent.putExtra("realName",data.getRealName());
                    mIntent.putExtra("mobile",data.getMobile());
                    mIntent.putExtra("rentAmount",data.getRentAmount());
                    mIntent.putExtra("realName",data.getRealName());
                    mIntent.putExtra("startTime",data.getStartTime());
                    mIntent.putExtra("endTime",data.getEndTime());
                    mIntent.putExtra("contractId",data.getId());
                    startActivity(mIntent);
                }
                break;
            case R.id.bnt_dzht:

                break;
            case R.id.bnt_zd:
                Intent mIntent = new Intent(MineHtDetailsActivity.this,MineJfzdActivity.class);
                mIntent.putExtra("contractId",data.getId());
                startActivity(mIntent);
                break;
        }
    }
}
