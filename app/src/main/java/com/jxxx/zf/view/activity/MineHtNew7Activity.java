package com.jxxx.zf.view.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.MainActivity;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.UserContractBean;
import com.jxxx.zf.utils.view.DrawView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineHtNew7Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.draw_view)
    DrawView mDrawView;
    UserContractBean.ListBean mUserContractBean;
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_new_7;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "合同签名");

//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ){
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
//        }

        mUserContractBean = getIntent().getParcelableExtra("mUserContractBean");
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.bnt_1,R.id.bnt_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_1:
                mDrawView.savek();
                postAdviserCreate();
                break;
            case R.id.bnt_2:
                mDrawView.clearAll();
                break;
        }
    }

    private void postAdviserCreate() {
        Log.w("mUserContractBean","mUserContractBean:"+mUserContractBean.toString());
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .postAdviserCreate(mUserContractBean)
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
                            baseStartActivity(MainActivity.class,null);
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
