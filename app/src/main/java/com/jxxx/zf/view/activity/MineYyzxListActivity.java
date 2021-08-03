package com.jxxx.zf.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.AppointmentList;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.DialogUtils;
import com.jxxx.zf.utils.IntentUtils;
import com.jxxx.zf.view.adapter.MineListYyzxAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineYyzxListActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    int page = 1;
    private MineListYyzxAdapter mMineListYyzxAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_refresh_list;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "预约中心");

        mMineListYyzxAdapter = new MineListYyzxAdapter(null);
        mRvList.setAdapter(mMineListYyzxAdapter);

        mMineListYyzxAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZuFangYyxqActivity.startActivity_zf(MineYyzxListActivity.this,
                        mMineListYyzxAdapter.getData().get(position).getId(),0);
            }
        });
        mMineListYyzxAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView tv = (TextView)view;
                switch (view.getId()) {
                    case R.id.bnt_1:
                    case R.id.bnt_2:
                    case R.id.bnt_3:
                        ZuFangYyxqActivity.startActivity_zf(MineYyzxListActivity.this,
                                mMineListYyzxAdapter.getData().get(position).getId(),0);
//                        setOnClickListener(tv.getText().toString(),mMineListYyzxAdapter.getData(),position);
                        break;

                }
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
            }
        });
    }

    private void setOnClickListener(String str, List<AppointmentDetailsBase> data, int position) {
        switch (str){
            case "评价":
                MineYypjActivity.startActivity_pj(this,data.get(position).getAdviserId(),data.get(position).getId());
                break;
            case "取消预约":
                DialogUtils.showDialogHint(this, "确定取消预约吗？", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        getAppointmentCancel(data.get(position).getId(),position);
                    }
                });
                break;
            case "更改预约":
                ZuFangYyActivity.startActivityYyUpdata(this,data.get(position));
                break;
            case "联系对方":
                IntentUtils.startActivityPhone(MineYyzxListActivity.this, mMineListYyzxAdapter.getData().get(position).getAdviserMobile());
                break;
        }
    }


    private void getAppointmentCancel(String id, int position) {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .getAppointmentCancel(id)
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
                            ToastUtils.showLong("取消成功");
                            mMineListYyzxAdapter.remove(position);
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
    public void initData() {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .getUserAppointmentList(page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AppointmentList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AppointmentList> result) {
                        hideLoading();
                        if (isResultOk(result) && result.getData() != null) {
                            if(result.getData().getList()!=null){
                                mRefreshLayout.finishRefresh();
                                mRefreshLayout.finishLoadMore();
                                if(page==1){
                                    mMineListYyzxAdapter.setNewData(result.getData().getList());
                                } else{
                                    mMineListYyzxAdapter.addData(result.getData().getList());
                                }

                                if(result.getData().getCount()<=mMineListYyzxAdapter.getData().size()){
                                    mRefreshLayout.setNoMoreData(true);
                                }
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
