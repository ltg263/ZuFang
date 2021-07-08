package com.jxxx.zf.view.activity;


import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.HouseCompareBean;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.view.adapter.HomeFyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZuFangListBdActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    boolean hideSame = false;
    private HomeFyAdapter mHomeFyAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_zufang_list_bd;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "对比");
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mHomeFyAdapter = new HomeFyAdapter(null);
        mRvList.setAdapter(mHomeFyAdapter);

        mHomeFyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void initData() {
        RetrofitUtil.getInstance().apiService()
                .houseCompare("1,2,3,4,5,6",hideSame?"1":null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null){

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

    @OnClick({R.id.bnt_tjfy, R.id.bnt_ksdb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_tjfy:
                baseStartActivity(MineListScActivity.class,null);
                break;
            case R.id.bnt_ksdb:
                baseStartActivity(ZuFangFybdActivity.class,null);
                break;
        }
    }
}



