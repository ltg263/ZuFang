package com.jxxx.zf.view.activity.payActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.AccountBillBean;
import com.jxxx.zf.bean.BankListBean;
import com.jxxx.zf.bean.BankListUserBean;
import com.jxxx.zf.bean.BankListUserBeans;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.DialogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActivityPayBankCardList extends BaseActivity {


    @BindView(R.id.include)
    Toolbar myToolbar;
    @BindView(R.id.cardRv)
    RecyclerView cardRv;

    @BindView(R.id.tv_not_data)
    TextView tv_not_data;
    AdapterPayBankCardList mAdapterPayBankCardList;
    //    List<BankCardBean.ListBean> listBeans = new ArrayList<>();
    boolean isSelect = false;

    @Override
    public int intiLayout() {
        return R.layout.activity_pay_bank_card_list;
    }

    @Override
    public void initView() {

        setToolbar(myToolbar, "银行卡管理");

        isSelect = getIntent().getBooleanExtra("isSelect",false);
        mAdapterPayBankCardList = new AdapterPayBankCardList(null);
        cardRv.setAdapter(mAdapterPayBankCardList);
        if (isSelect) {
            mAdapterPayBankCardList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(ActivityPayBankCardList.this, ActivityPayBankCardAdd.class);
                    intent.putExtra("item", ActivityPayBankCardList.this.mAdapterPayBankCardList.getData().get(position));
                    setResult(2,intent);
                    finish();
                }
            });
        } else {
            mAdapterPayBankCardList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(ActivityPayBankCardList.this, ActivityPayBankCardAdd.class);
                    intent.putExtra("isUpdate", true);
                    intent.putExtra("id", mAdapterPayBankCardList.getData().get(position).getId());
                    startActivity(intent);
                }
            });
        }

        mAdapterPayBankCardList.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                if(view.getId()==R.id.iv_mr){
                    if(mAdapterPayBankCardList.getData().get(position).getStatus().equals("1")){
                        return;
                    }
//                    showLoading();
//                    RetrofitUtil.getInstance().apiService()
//                            .getBankDelete(mAdapterPayBankCardList.getData().get(position).getId())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribeOn(Schedulers.io())
//                            .subscribe(new Observer<Result>() {
//                                @Override
//                                public void onSubscribe(Disposable d) {
//
//                                }
//
//                                @Override
//                                public void onNext(Result result) {
//                                    hideLoading();
//                                    if (isResultOk(result)) {
//                                        getBankListUserAll();
//                                        ToastUtils.showShort("修改成功");
//                                    }
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    hideLoading();
//                                }
//
//                                @Override
//                                public void onComplete() {
//                                    hideLoading();
//                                }
//                            });
                    return;
                }
                DialogUtils.showDialogHint(ActivityPayBankCardList.this, "删除银行卡将无法恢复，确认删除吗",
                        false, () -> {
                            showLoading();
                            RetrofitUtil.getInstance().apiService()
                                    .getBankDelete(mAdapterPayBankCardList.getData().get(position).getId())
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
                                                mAdapterPayBankCardList.remove(position);
                                                mAdapterPayBankCardList.notifyDataSetChanged();
                                                ToastUtils.showShort("删除成功");
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
                        });


            }
        });
    }

    @Override
    public void initData() {
    }

    @OnClick(R.id.addBankCard)
    public void onViewClicked() {
        baseStartActivity(ActivityPayBankCardAdd.class,null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBankListUserAll();
    }

    private void getBankListUserAll() {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .getBankListUserAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<BankListUserBeans>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<BankListUserBeans> result) {
                        hideLoading();
                        if (isResultOk(result)) {
                            mAdapterPayBankCardList.setNewData(result.getData().getList());
                            if (mAdapterPayBankCardList.getData().size()>0) {
                                tv_not_data.setVisibility(View.GONE);
                                cardRv.setVisibility(View.VISIBLE);
                            }else{
                                tv_not_data.setVisibility(View.VISIBLE);
                                cardRv.setVisibility(View.GONE);
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
