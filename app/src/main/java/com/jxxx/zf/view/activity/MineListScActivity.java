package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.view.adapter.MineListScAdapter;
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

public class MineListScActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    int page = 1;
    private MineListScAdapter mMineListScAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_refresh_list;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "我的收藏");

        mMineListScAdapter = new MineListScAdapter(null);
        mRvList.setAdapter(mMineListScAdapter);

        mMineListScAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                baseStartActivity(ZuFangXqActivity.class,null);
            }
        });
        mMineListScAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.bnt_qxsc:
                        houseDoCollection(mMineListScAdapter.getData(),position);
                        break;
                    case R.id.bnt_yykf:
                        ZuFangYyActivity.startActivityYyUi(MineListScActivity.this,mMineListScAdapter.getData().get(position));
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

    private void houseDoCollection(List<ZuFangDetailsBase> data, int position){

        RetrofitUtil.getInstance().apiService()
                .houseDoCollection(data.get(position).getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> result) {
                        if(isResultOk(result)){
                            mMineListScAdapter.remove(position);
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
    public void initData() {
        RetrofitUtil.getInstance().apiService()
                .collectionHouseList(page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<HouseListBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<HouseListBase> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null){
                            if(result.getData().getList()!=null){
                                mRefreshLayout.finishRefresh();
                                mRefreshLayout.finishLoadMore();
                                if(page==1){
                                    mMineListScAdapter.setNewData(result.getData().getList());
                                } else{
                                    mMineListScAdapter.addData(result.getData().getList());
                                }

                                if(result.getData().getCount()<=mMineListScAdapter.getData().size()){
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
