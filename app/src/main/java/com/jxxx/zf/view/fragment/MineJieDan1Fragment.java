package com.jxxx.zf.view.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.AppointmentList;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.DialogUtils;
import com.jxxx.zf.utils.IntentUtils;
import com.jxxx.zf.view.activity.ZuFangYyxqActivity;
import com.jxxx.zf.view.adapter.MineListJdAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineJieDan1Fragment extends BaseFragment {
    private MineListJdAdapter mMineListJdAdapter;

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_not_data)
    TextView mTvNotData;
    int page = 1;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {
        mMineListJdAdapter = new MineListJdAdapter(null);
        mRvList.setAdapter(mMineListJdAdapter);

        mMineListJdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZuFangYyxqActivity.startActivity_zf(mContext,
                        mMineListJdAdapter.getData().get(position).getId(),1);
            }
        });
        mMineListJdAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String str = ((TextView)view).getText().toString();
//                setOnClickListener(str,mMineListJdAdapter.getData(),position);
                ZuFangYyxqActivity.startActivity_zf(mContext,
                        mMineListJdAdapter.getData().get(position).getId(),1);
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
        showLoading();
    }

    private void setOnClickListener(String str, List<AppointmentDetailsBase> data, int position) {
        switch (str){
            case "????????????":
                IntentUtils.startActivityPhone(mContext, data.get(position).getMobile());
                break;
            case "?????????":
                DialogUtils.showDialogHint(mContext, "???????????????????????????", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        adviserUpdate(data.get(position),"4");
                    }
                });
                break;
            case "?????????":
                DialogUtils.showDialogHint(mContext, "??????????????????", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        adviserUpdate(data.get(position),"3");
                    }
                });
                break;
            case "??????":
                DialogUtils.showDialogHint(mContext, "??????????????????", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        receivingOrder(data.get(position).getId());
                    }
                });
                break;
            case "?????????":
                break;
            case "????????????":
                break;
            case "?????????":
                break;
        }
    }
    private void adviserUpdate(AppointmentDetailsBase mData, String status){
        RetrofitUtil.getInstance().apiService()
                .updateStatus(mData.getId(),status)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isResultOk(result)) {
                            initData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void receivingOrder(String appointmentId){
        RetrofitUtil.getInstance().apiService()
                .receivingOrder(appointmentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isResultOk(result)) {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    @Override
    protected void initData() {
        RetrofitUtil.getInstance().apiService()
                .getUserAppointmentJdList(page, ConstValues.PAGE_SIZE, getArguments().getString("status"))
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
                                    mMineListJdAdapter.setNewData(result.getData().getList());
                                } else{
                                    mMineListJdAdapter.addData(result.getData().getList());
                                }
                                if(mMineListJdAdapter.getData().size()>0){
                                    mTvNotData.setVisibility(View.GONE);
                                    mRefreshLayout.setVisibility(View.VISIBLE);
                                }else{
                                    mTvNotData.setVisibility(View.VISIBLE);
                                    mRefreshLayout.setVisibility(View.GONE);
                                }

                                if(result.getData().getCount()<=mMineListJdAdapter.getData().size()){
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
