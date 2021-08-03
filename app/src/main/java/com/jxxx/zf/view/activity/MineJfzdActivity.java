package com.jxxx.zf.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.zf.R;
import com.jxxx.zf.api.ApiService;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.ContractBillBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.StatusBarUtil;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.view.adapter.MineJfzdListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineJfzdActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_hjc)
    TextView mTvHjc;
    @BindView(R.id.tv_totalAmount)
    TextView mTvTotalAmount;
    @BindView(R.id.tv_syq)
    TextView mTvSyq;
    @BindView(R.id.tv_letfAmount)
    TextView mTvLetfAmount;
    @BindView(R.id.tv_withAmount)
    TextView mTvWithAmount;
    private MineJfzdListAdapter mMineJfzdListAdapter;
    String contractId,rentAmount,rentalDuration,startTime;

    @Override
    public int intiLayout() {
        StatusBarUtil.setTranslucentStatus(this);
        return R.layout.activity_mine_jfzd;
    }

    @Override
    public void initView() {
        mMyToolbar.setNavigationIcon(R.mipmap.icon_common_w);
        mMyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mMineJfzdListAdapter = new MineJfzdListAdapter(null);
        mRvList.setAdapter(mMineJfzdListAdapter);
        contractId = getIntent().getStringExtra("contractId");
        rentAmount = getIntent().getStringExtra("rentAmount");
        rentalDuration = getIntent().getStringExtra("rentalDuration");
        startTime = getIntent().getStringExtra("startTime");
    }

    @Override
    public void initData() {
        showLoading();
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<ContractBillBean>> mObservable;
        if(StringUtil.isNotBlank(contractId)){
            mObservable = mApiService.contractBill(contractId);
        }else{
            mObservable = mApiService.contractBillList();
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ContractBillBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ContractBillBean> result) {
                        hideLoading();
                        if (isResultOk(result) && result.getData() != null) {
                            mTvHjc.setText("合计"+result.getData().getBills().size()+"期应付(元)");
                            mTvTotalAmount.setText(result.getData().getTotalAmount());
                            mTvSyq.setText("剩余0期应付(元)");
                            mTvLetfAmount.setText(result.getData().getLetfAmount());
                            mTvWithAmount.setText(result.getData().getWithAmount());
                            mMineJfzdListAdapter.setNewData(result.getData().getBills());
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
