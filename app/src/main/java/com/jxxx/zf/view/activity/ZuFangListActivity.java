package com.jxxx.zf.view.activity;


import android.content.ContentValues;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.jxxx.zf.utils.StringUtil;
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
    @BindView(R.id.tv_not_data)
    TextView tv_not_data;

    int page = 1;

    private HomeFyAdapter mHomeFyAdapter;

    String str = "";
    private Intent mIntent;

    @Override
    public int intiLayout() {
        return R.layout.activity_zufang_list;
    }

    @Override
    public void initView() {
        mIntent = getIntent();
        if(StringUtil.isNotBlank(mIntent.getStringExtra("type"))){
            rentBegin = mIntent.getStringExtra("rentBegin");
            rentEnd = mIntent.getStringExtra("rentEnd");
            rentingType =  mIntent.getStringExtra("rentingType");
            houseType = mIntent.getStringExtra("houseType");
            lables = mIntent.getStringExtra("lables");
            if(rentingType==null){
                mRbHomeSelect2.setText("不限");
            }
            if(StringUtil.isNotBlank(rentingType)){
                if(rentingType.equals("1")){
                    mRbHomeSelect2.setText("合租");
                }
                if(rentingType.equals("2")){
                    mRbHomeSelect2.setText("整租");
                }
            }
        }
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

        new RadioGroupSelectUtils().setOnChangeListener(this,
                mMRadioGroup, mRbHomeSelect1, mRbHomeSelect2, mRbHomeSelect3, mRbHomeSelect4, new RadioGroupSelectUtils.DialogInterface() {
            @Override
            public void btnConfirm(String str) {
                ZuFangListActivity.this.str = str;
                page = 1;
                initData();
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

    String rentingType = null;
    String rentBegin = null;
    String rentEnd = null;
    String houseType = null;
    String lables = null;
    @Override
    public void initData() {
        switch (str){
            case "不限":
                rentingType = null;
                break;
            case "整租":
                rentingType = "2";
                break;
            case "合租":
                rentingType = "1";
                break;
            case "不限 ":
                rentBegin = null;
                rentEnd = null;
                break;
            case "500元以下":
                rentBegin = "0";
                rentEnd = "500";
                break;
            case "500-1000元":
                rentBegin = "500";
                rentEnd = "1000";
                break;
            case "1000-1500元":
                rentBegin = "1000";
                rentEnd = "1500";
                break;
            case "1500-2000元":
                rentBegin = "1500";
                rentEnd = "2000";
                break;
            case "2000-3000元":
                rentBegin = "2000";
                rentEnd = "3000";
                break;
            case "3000-5000元":
                rentBegin = "30000";
                rentEnd = "5000";
                break;
            case "5000元以上":
                rentBegin = "5000";
                break;
        }
        RetrofitUtil.getInstance().apiService()
                .HouseList(page, ConstValues.PAGE_SIZE,rentingType,rentBegin,rentEnd,houseType,lables,null)
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

                                if(mHomeFyAdapter.getData().size()>0){
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 0 && data!=null){
            if(StringUtil.isNotBlank(data.getStringExtra("type"))){
                rentBegin = data.getStringExtra("rentBegin");
                rentEnd = data.getStringExtra("rentEnd");
                rentingType =  data.getStringExtra("rentingType");
                houseType = data.getStringExtra("houseType");
                lables = data.getStringExtra("lables");
                if(rentingType==null){
                    mRbHomeSelect2.setText("不限");
                }
                if(StringUtil.isNotBlank(rentingType)){
                    if(rentingType.equals("1")){
                        mRbHomeSelect2.setText("合租");
                    }
                    if(rentingType.equals("2")){
                        mRbHomeSelect2.setText("整租");
                    }
                }
                initData();
            }
        }
    }


}



