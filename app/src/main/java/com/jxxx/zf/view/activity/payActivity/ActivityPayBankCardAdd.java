package com.jxxx.zf.view.activity.payActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.BankListBean;
import com.jxxx.zf.bean.BankListUserBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.PickerViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActivityPayBankCardAdd extends BaseActivity {


    @BindView(R.id.include)
    Toolbar toolbar;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.cardNo)
    EditText cardNo;
    @BindView(R.id.bankName)
    EditText bankName;
    @BindView(R.id.bankType)
    TextView bankType;
    @BindView(R.id.iv)
    ImageView mIv;

    String status = "1";
    boolean isUpdate = false;
    String id;
    BankListUserBean mBankListUserBean = new BankListUserBean();
    List<String> list = new ArrayList<>();
    List<BankListBean> dataB;
    @Override
    public int intiLayout() {
        return R.layout.activity_pay_bank_card_add;
    }

    @Override
    public void initView() {
        setToolbar(toolbar,"添加银行卡");

        isUpdate = getIntent().getBooleanExtra("isUpdate",false);
        id = getIntent().getStringExtra("id");
    }

    @Override
    public void initData() {
        RetrofitUtil.getInstance().apiService()
                .getBankListAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<BankListBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<BankListBean>> result) {
                        hideLoading();
                        if (isResultOk(result)) {
                            dataB = result.getData();
                            for (int i=0;i<dataB.size();i++){
                                list.add(dataB.get(i).getBankName());
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
        if (isUpdate) {
            showLoading();
            RetrofitUtil.getInstance().apiService()
                    .getBankDetails(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Result<BankListUserBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Result<BankListUserBean> result) {
                            hideLoading();
                            if (isResultOk(result)) {
                                mBankListUserBean = result.getData();
                                username.setText(mBankListUserBean.getRealname());
                                phone.setText(mBankListUserBean.getMobile());
                                cardNo.setText(mBankListUserBean.getBankNo());
                                bankType.setText(mBankListUserBean.getBankName());
                                status = mBankListUserBean.getStatus();
                                if(status.equals("1")){
                                    mIv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_circle_yes_l));
                                }
//                            GlideImageLoader.loadImageAndDefault(AddBankCardActivity.this,request.getImgUrl(),cardImage);
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

    @OnClick({R.id.btnSubmit,R.id.iv,R.id.bankType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bankType:
                PickerViewUtils.selectorCustom(this, list, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mBankListUserBean.setBankId(dataB.get(pos).getId());
                        mBankListUserBean.setBankName(dataB.get(pos).getBankName());
                        bankType.setText(list.get(pos));
                    }
                });
                break;
            case R.id.iv:
                if(status.equals("1")){
                    status = "2";
                    mIv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_circle_no));
                }else{
                    status = "1";
                    mIv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_circle_yes_l));
                }
                break;
            case R.id.btnSubmit:
                String mobile = phone.getText().toString();
                String realName = username.getText().toString();
                String accountNo = cardNo.getText().toString();
                String bank = bankType.getText().toString();
                if (StringUtils.isEmpty(mobile) ||
                        StringUtils.isEmpty(realName)
                        ||StringUtils.isEmpty(bank)
                        ||StringUtils.isEmpty(accountNo)){
                    ToastUtils.showShort("请填写完整的信息");
                    return;
                }
                mBankListUserBean.setBankName(bank);
                mBankListUserBean.setBankNo(accountNo);
//                mBankListUserBean.setRealname(realName);
                mBankListUserBean.setMobile(mobile);
                if (isUpdate) {
                    showLoading();
                    RetrofitUtil.getInstance().apiService()
                            .postBankUpdate(mBankListUserBean)
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
                                        ToastUtils.showShort("修改成功");
                                        finish();
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

                } else {
                    showLoading();
                    RetrofitUtil.getInstance().apiService()
                            .postBankAdd(mBankListUserBean)
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
                                        ToastUtils.showShort("添加成功");
                                        finish();
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
                break;
        }
    }
}
