package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.PickerViewUtils;
import com.jxxx.zf.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineHtXqActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.et_realName)
    EditText mEtRealName;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_zj)
    EditText mEtZj;
    @BindView(R.id.tv_qzsj)
    TextView mTvQzsj;
    @BindView(R.id.tv_xzsj)
    TextView mTvXzsj;
    @BindView(R.id.tv_bnt)
    TextView mTvBnt;
    private Intent mIntent;
    List<String> listStr = new ArrayList<>();
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_xq;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "实名认证");
        mIntent = getIntent();
        mEtRealName.setText(mIntent.getStringExtra("realName"));
        mEtMobile.setText(mIntent.getStringExtra("mobile"));
        mEtZj.setText(mIntent.getStringExtra("rentAmount"));
        mTvQzsj.setText(mIntent.getStringExtra("startTime").replace(" 00:00:00",""));
//        mTvXzsj.setText(mIntent.getStringExtra("endTime").replace(" 00:00:00",""));
    }

    @Override
    public void initData() {
        listStr.add("3个月");
        listStr.add("6个月");
        listStr.add("9个月");
        listStr.add("12个月");
        listStr.add("24个月");
    }

    @OnClick({R.id.tv_qzsj, R.id.tv_xzsj, R.id.tv_bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_qzsj:

                break;
            case R.id.tv_xzsj:
//                PickerViewUtils.selectorDate(this, new boolean[]{true, true, true, false, false, false}, new PickerViewUtils.GetTimeInterface() {
//                    @Override
//                    public void getTime(Date time) {
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                        mTvXzsj.setText(simpleDateFormat.format(time));
//                    }
//                });
                PickerViewUtils.selectorCustom(this, listStr, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvXzsj.setText(listStr.get(pos));
                    }
                });
                break;
            case R.id.tv_bnt:
               contractRenewal();
                break;
        }
    }

    private void contractRenewal() {
       String realName = mEtRealName.getText().toString().trim();
       String mobile = mEtMobile.getText().toString().trim();
       String rentAmount = mEtZj.getText().toString().trim();
       String rentalDuration = mTvXzsj.getText().toString().trim();
       if(StringUtil.isBlank(mobile) ||StringUtil.isBlank(rentAmount) ||StringUtil.isBlank(rentalDuration)){
           ToastUtils.showShort("信息不全");
           return;
       }
       showLoading();
        RetrofitUtil.getInstance().apiService()
                .contractRenewal(mIntent.getStringExtra("contractId"),
                        mobile,rentAmount,rentalDuration.replace("个月",""))
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
//                            baseStartActivity(MineHtJcOkActivity.class,null);
                            ToastUtils.showLong("修改成功");
                            finish();
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
