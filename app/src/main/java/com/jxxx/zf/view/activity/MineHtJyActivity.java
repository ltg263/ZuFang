package com.jxxx.zf.view.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.versionedparcelable.ParcelUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.RescindContractBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.DialogUtils;
import com.jxxx.zf.utils.PickerViewUtils;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineHtJyActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.tv_applyTime)
    TextView mTvApplyTime;
    @BindView(R.id.tv_contractEndTime)
    TextView mTvContractEndTime;
    @BindView(R.id.tv_rentingAmount)
    TextView mTvRentingAmount;
    @BindView(R.id.tv_serverAmount)
    TextView mTvServerAmount;
    @BindView(R.id.tv_totalAmount)
    TextView mTvTotalAmount;
    @BindView(R.id.tv_penalty)
    TextView mTvPenalty;
    String applyTime;
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_jy;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "解约合同");

    }


    @Override
    public void initData() {
    }

    @OnClick({R.id.bnt_tjjyht,R.id.ll_applyTime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_applyTime:
                PickerViewUtils.selectorDate(this, new boolean[]{true, true, true, false, false, false}, new PickerViewUtils.GetTimeInterface() {
                    @Override
                    public void getTime(Date time) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        mTvApplyTime.setText(simpleDateFormat.format(time));
                        getShowRescindContract(simpleDateFormat.format(time));
                    }
                });
                break;
            case R.id.bnt_tjjyht:
                if(StringUtil.isBlank(applyTime)){
                    ToastUtils.showShort("请先选择申请退租日期");
                    return;
                }
                DialogUtils.showDialogHint(this, "确定解约本合同吗？", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        postRescindContract();
                    }
                });
                break;
        }
    }

    private void getShowRescindContract(String format) {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .getShowRescindContract(getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH),format)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RescindContractBean>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RescindContractBean> result) {
                        hideLoading();
                        if (isResultOk(result)) {
                            RescindContractBean mData = result.getData();
                            mTvApplyTime.setText(mData.getApplyTime().replace(" 00:00:00", "").trim());
                            applyTime = mTvApplyTime.getText().toString().trim();
                            mTvContractEndTime.setText(mData.getContractEndTime().replace(" 00:00:00", ""));
                            mTvRentingAmount.setText(mData.getRentingAmount());
                            mTvServerAmount.setText(mData.getServerAmount());
                            mTvTotalAmount.setText(mData.getTotalAmount());
                            mTvPenalty.setText(mData.getPenalty());

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


    private void postRescindContract() {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .postRescindContract(getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH),mTvApplyTime.getText().toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        hideLoading();
                        if (isResultOk(result)) {
                            baseStartActivity(MineHtJcOkActivity.class, null);
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
