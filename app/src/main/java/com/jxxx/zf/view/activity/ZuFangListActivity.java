package com.jxxx.zf.view.activity;


import android.content.ContentValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.HomeZuFangListBase;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.RadioGroupSelectUtils;
import com.jxxx.zf.utils.view.CustomPopWindow;
import com.jxxx.zf.view.adapter.HomeFyAdapter;
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

public class ZuFangListActivity extends BaseActivity {

    @BindView(R.id.rb_home_select1)
    RadioButton mRbHomeSelect1;
    @BindView(R.id.rb_home_select2)
    RadioButton mRbHomeSelect2;
    @BindView(R.id.rb_home_select3)
    RadioButton mRbHomeSelect3;
    @BindView(R.id.rb_home_select4)
    RadioButton mRbHomeSelect4;
    @BindView(R.id.mRadioGroup)
    RadioGroup mMRadioGroup;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_home_list)
    RecyclerView mRvHomeList;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;

    int page = 1;

    private HomeFyAdapter mHomeFyAdapter;



    @Override
    public int intiLayout() {
        return R.layout.activity_zufang_list;
    }

    @Override
    public void initView() {
        iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mHomeFyAdapter = new HomeFyAdapter(null);
        mRvHomeList.setAdapter(mHomeFyAdapter);

        mHomeFyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        new RadioGroupSelectUtils().setOnChangeListener(this,mMRadioGroup,mRbHomeSelect1,mRbHomeSelect2,mRbHomeSelect3,mRbHomeSelect4);
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

    @Override
    public void initData() {
        RetrofitUtil.getInstance().apiService()
                .HouseList(page, ConstValues.PAGE_SIZE)
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
                                    mHomeFyAdapter.setNewData(result.getData().getList());
                                } else{
                                    mHomeFyAdapter.addData(result.getData().getList());
                                }

                                if(result.getData().getCount()<=mHomeFyAdapter.getData().size()){
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



