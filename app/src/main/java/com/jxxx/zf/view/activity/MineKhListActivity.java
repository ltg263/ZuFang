
package com.jxxx.zf.view.activity;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.MyCustomersBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.UserContractBean;
import com.jxxx.zf.utils.IntentUtils;
import com.jxxx.zf.view.adapter.MineListKhAdapter;
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

public class MineKhListActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.tv_not_data)
    TextView tv_not_data;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    int page = 1;

    private MineListKhAdapter mMineListKhAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_refresh_list;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "我的客户");

        mMineListKhAdapter = new MineListKhAdapter(null);
        mRvList.setAdapter(mMineListKhAdapter);

        mMineListKhAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                baseStartActivity(ZuFangYyxqActivity.class,null);
            }
        });
        mMineListKhAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_dh:
                        IntentUtils.startActivityPhone(MineKhListActivity.this,
                                mMineListKhAdapter.getData().get(position).getMobile());
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
        showLoading();
    }

    @Override
    public void initData() {
        RetrofitUtil.getInstance().apiService()
                .getMyCustomers(page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MyCustomersBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MyCustomersBean> result) {
                        hideLoading();
                        if (isResultOk(result) && result.getData() != null) {
                            if (result.getData().getList() != null) {
                                mRefreshLayout.finishRefresh();
                                mRefreshLayout.finishLoadMore();
                                if (page == 1) {
                                    mMineListKhAdapter.setNewData(result.getData().getList());
                                } else {
                                    mMineListKhAdapter.addData(result.getData().getList());
                                }

                                if (result.getData().getCount() <= mMineListKhAdapter.getData().size()) {
                                    mRefreshLayout.setNoMoreData(true);
                                }

                                if(mMineListKhAdapter.getData().size()>0){
                                    tv_not_data.setVisibility(View.GONE);
                                    mRefreshLayout.setVisibility(View.VISIBLE);
                                }else{
                                    tv_not_data.setVisibility(View.VISIBLE);
                                    mRefreshLayout.setVisibility(View.GONE);
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
