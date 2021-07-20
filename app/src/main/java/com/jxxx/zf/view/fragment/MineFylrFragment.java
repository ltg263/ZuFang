package com.jxxx.zf.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.DialogUtils;
import com.jxxx.zf.view.activity.MineFytjActivity;
import com.jxxx.zf.view.activity.ZuFangFaBuActivity;
import com.jxxx.zf.view.activity.ZuFangXqActivity;
import com.jxxx.zf.view.adapter.MineListFylrAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineFylrFragment extends BaseFragment {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private MineListFylrAdapter mMineListFylrAdapter;

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    int page = 1;
    String status = null;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        Log.w("bundle","bundle"+bundle);
        if(bundle!=null){
            status = bundle.getString("status");
        }
        mMineListFylrAdapter = new MineListFylrAdapter(null);
        mRvList.setAdapter(mMineListFylrAdapter);

        mMineListFylrAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(mContext, ZuFangXqActivity.class);
                mIntent.putExtra("id",mMineListFylrAdapter.getData().get(position).getId());
                mContext.startActivity(mIntent);
            }
        });
        mMineListFylrAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.bnt_1:
                        baseStartActivity(MineFytjActivity.class, null);
                        break;
                    case R.id.bnt_2:
                        Intent mIntent = new Intent(mContext,ZuFangFaBuActivity.class);
                        mIntent.putExtra("ZuFangDetailsBase",mMineListFylrAdapter.getData().get(position));
                        startActivity(mIntent);
                        break;
                    case R.id.bnt_3:
                        String statu = mMineListFylrAdapter.getData().get(position).getStatus();
                        if(statu.equals("1")){
                            DialogUtils.showDialogHint(mContext, "确定下架此房源吗？", false, new DialogUtils.ErrorDialogInterface() {
                                @Override
                                public void btnConfirm() {
                                    updateShelves(mMineListFylrAdapter.getData().get(position).getId(),"2");
                                }
                            });
                        }else{
                            DialogUtils.showDialogHint(mContext, "确定上架此房源吗？", false, new DialogUtils.ErrorDialogInterface() {
                                @Override
                                public void btnConfirm() {
                                    updateShelves(mMineListFylrAdapter.getData().get(position).getId(),"1");
                                }
                            });
                        }
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

    public void setNotifyDataSetChanged(boolean isBianJi) {
        mMineListFylrAdapter.setBianji(isBianJi);
        mMineListFylrAdapter.notifyDataSetChanged();
    }

    private void updateShelves(String id,String status) {
        RetrofitUtil.getInstance().apiService()
                .updateShelves(id,status)
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
                            initData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                        hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                        hideLoading();
                    }
                });
    }

    @Override
    protected void initData() {
        RetrofitUtil.getInstance().apiService()
                .userHouseList(status,page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<HouseListBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<HouseListBase> result) {
                        hideLoading();
                        if (isResultOk(result) && result.getData() != null) {
                            if (result.getData().getList() != null) {
                                mRefreshLayout.finishRefresh();
                                mRefreshLayout.finishLoadMore();
                                if (page == 1) {
                                    mMineListFylrAdapter.setNewData(result.getData().getList());
                                } else {
                                    mMineListFylrAdapter.addData(result.getData().getList());
                                }

                                if (result.getData().getCount() <= mMineListFylrAdapter.getData().size()) {
                                    mRefreshLayout.setNoMoreData(true);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                        hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                        hideLoading();
                    }
                });
    }
}
