package com.jxxx.zf.view.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.view.adapter.ZuFangYyxqZtAdapter;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZuFangYyxq2Fragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_zu_fang_yyxq_2;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String id = "";
        if (bundle != null) {
            id = bundle.getString("id");
        }
        RetrofitUtil.getInstance().apiService()
                .getAppointmentDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AppointmentDetailsBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AppointmentDetailsBase> result) {
                        hideLoading();
                        if (isResultOk(result) && result.getData() != null) {
                            if(result.getData().getStatusList()!=null){
                                mRvList.setAdapter(new ZuFangYyxqZtAdapter(result.getData().getStatusList()));
                            }
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
