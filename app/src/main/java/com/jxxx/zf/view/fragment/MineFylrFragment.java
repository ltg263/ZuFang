package com.jxxx.zf.view.fragment;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.view.activity.MineFytjActivity;
import com.jxxx.zf.view.activity.ZuFangFaBuActivity;
import com.jxxx.zf.view.activity.ZuFangXqActivity;
import com.jxxx.zf.view.adapter.MineListFylrAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {
        mMineListFylrAdapter = new MineListFylrAdapter(null);
        mRvList.setAdapter(mMineListFylrAdapter);

        mMineListFylrAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                baseStartActivity(ZuFangXqActivity.class, null);
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
                        baseStartActivity(ZuFangFaBuActivity.class, null);
                        break;
                    case R.id.bnt_3:

                        break;

                }
            }
        });
    }

    public void setNotifyDataSetChanged(boolean isBianJi) {
        mMineListFylrAdapter.setBianji(isBianJi);
        mMineListFylrAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        RetrofitUtil.getInstance().apiService()
                .HouseList(page, ConstValues.PAGE_SIZE, null, null, null, null, null,null)
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
