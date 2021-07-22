package com.jxxx.zf.view.activity.payActivity;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.AccountBillBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 我的钱包
 */
public class ActivityPayHomeQb extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.rv_yz_list)
    RecyclerView mRvYzList;
    @BindView(R.id.tv_ye)
    TextView mTvYe;
    @BindView(R.id.tv_freezeAmount)
    TextView tv_freezeAmount;
    @BindView(R.id.tv_totalAmount)
    TextView tv_totalAmount;
    @BindView(R.id.tv_yz_rq)
    TextView tv_yz_rq;
    private AdapterPayLogList mAdapterPayLogList;

    @Override
    public int intiLayout() {
        return R.layout.activity_pay_home_pb;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "我的钱包");
    }

    @Override
    public void initData() {
        getAccount();
    }

    private void getAccount() {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .getAccount("1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AccountBillBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AccountBillBean> result) {
                        hideLoading();
                        if (isResultOk(result)) {
                            mTvYe.setText(result.getData().getBalance());
                            tv_freezeAmount.setText(result.getData().getFreezeAmount());
                            tv_totalAmount.setText(result.getData().getTotalAmount());
                            tv_yz_rq.setText("本月账单共"+result.getData().getLogs().size()+"笔");
                            initOrder(result.getData().getLogs());
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

    @OnClick({R.id.tv_yz_tx, R.id.tv_yz_zz, R.id.tv_yz_rq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_yz_tx:
            case R.id.tv_yz_zz:
                baseStartActivity(ActivityPayTiXian.class,null);
                break;
            case R.id.tv_yz_rq:
//                baseStartActivity(MineWdyzDdcxActivity.class,null);
                break;
        }
    }

    private void initOrder(List<AccountBillBean.LogsBean> logs) {
        mRvYzList.setFocusable(false);
        mRvYzList.setLayoutManager(new LinearLayoutManager(this));
        mAdapterPayLogList = new AdapterPayLogList(logs);
        mRvYzList.setAdapter(mAdapterPayLogList);
        mAdapterPayLogList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                MineWdyzDetailsActivity.startIntentActivity(MineWdqbHomeActivity.this);
            }
        });
    }

}
