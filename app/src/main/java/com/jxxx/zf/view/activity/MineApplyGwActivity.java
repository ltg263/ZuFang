package com.jxxx.zf.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.MainActivity;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.ApplyInfoBean;
import com.jxxx.zf.bean.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineApplyGwActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_apply_gw;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "申请顾问");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt:
                realNameAdviser();
                break;
        }
    }

    private void realNameAdviser() {
        ApplyInfoBean.RealNameAdviser mRealNameAuthentication = new ApplyInfoBean.RealNameAdviser();
        RetrofitUtil.getInstance().apiService()
                .realNameAdviser(mRealNameAuthentication)
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
}
