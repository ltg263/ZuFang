package com.jxxx.zf.view.activity;


import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.UserContractBean;
import com.jxxx.zf.bean.UserContractDetailsBean;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineHtDetailsActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

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

    @OnClick({R.id.bnt_jy, R.id.bnt_xy, R.id.bnt_zd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_jy:
                baseStartActivity(MineHtJyActivity.class,null);
                break;
            case R.id.bnt_xy:
                baseStartActivity(MineHtNew1Activity.class,null);
                break;
            case R.id.bnt_zd:
                baseStartActivity(MineJfzdActivity.class,null);
                break;
        }
    }
}
