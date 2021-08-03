package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.UserContractBean;
import com.jxxx.zf.utils.PickerViewUtils;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineHtNew3Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_fyName)
    TextView mTvFyName;
    @BindView(R.id.tv_zklx)
    TextView mTvZklx;
    @BindView(R.id.tv_zjzp)
    TextView mTvZjzp;
    @BindView(R.id.et_xm)
    EditText mEtXm;
    @BindView(R.id.tv_xb)
    TextView mTvXb;
    @BindView(R.id.et_sjh)
    EditText mEtSjh;
    @BindView(R.id.tv_zjlx)
    TextView mTvZjlx;
    @BindView(R.id.et_zjhm)
    EditText mEtZjhm;
    @BindView(R.id.et_zj)
    EditText et_zj;
    @BindView(R.id.et_yj)
    EditText et_yj;
    @BindView(R.id.et_lxr)
    EditText mEtLxr;
    @BindView(R.id.et_lxtdh)
    EditText mEtLxtdh;
    @BindView(R.id.tv_qzsj)
    TextView mTvQzsj;
    @BindView(R.id.tv_dqsj)
    TextView mTvDqsj;
    @BindView(R.id.tv_zqfs)
    TextView tv_zqfs;
    @BindView(R.id.tv_zqsc)
    TextView mTvZqsc;
    Intent mIntent;
    String rentType;
    String appointmentId;
    AppointmentDetailsBase data;
    private List<String> listStr = new ArrayList<>();
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_new_3;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "新建合同");
        mIntent = getIntent();
        appointmentId = mIntent.getStringExtra("appointmentId");

    }

    @Override
    public void initData() {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .getAppointmentDetails(appointmentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AppointmentDetailsBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AppointmentDetailsBase> result) {
                        hideLoading();
                        if (isResultOk(result) && result.getData() != null) {
                            data = result.getData();
                            initUi();
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

    private void initUi() {

        mTvFyName.setText(data.getHouse().getName());
        et_zj.setText(data.getHouse().getRent());
        mEtXm.setText(data.getRealName());
        mTvXb.setText(data.getGender().equals("1") ? "男" : "女");
        mEtSjh.setText(data.getMobile());
        et_yj.setText(data.getHouse().getCommission());
        tv_zqfs.setText(StringUtil.getHouseRenting(data.getHouse().getRentType()));
        mTvZjlx.setText("身份证");
    }

    @OnClick({R.id.tv_zqfs, R.id.tv_zjzp,R.id.tv_xb, R.id.tv_zjlx, R.id.tv_qzsj, R.id.tv_zqsc,R.id.tv_dqsj, R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_zqfs:
                PickerViewUtils.selectorCustom(this, Arrays.asList(ConstValues.HOUSE_RENT_TYPE), "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        rentType = (pos+1)+"";
                        tv_zqfs.setText(ConstValues.HOUSE_RENT_TYPE[pos]);
                    }
                });
                break;
            case R.id.tv_zjzp:
                break;
            case R.id.tv_xb:
                listStr.clear();
                listStr.add("男");
                listStr.add("女");
                PickerViewUtils.selectorCustom(this, listStr, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvXb.setText(listStr.get(pos));
                    }
                });
                break;
            case R.id.tv_zjlx:
                listStr.clear();
                listStr.add("身份证");
                PickerViewUtils.selectorCustom(this, listStr, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvZjlx.setText(listStr.get(pos));
                    }
                });
                break;
            case R.id.tv_qzsj:
                PickerViewUtils.selectorDate(this, new boolean[]{true, true, true, false, false, false}, new PickerViewUtils.GetTimeInterface() {
                    @Override
                    public void getTime(Date time) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        mTvQzsj.setText(simpleDateFormat.format(time));
                    }
                });
                break;
            case R.id.tv_dqsj:
                PickerViewUtils.selectorDate(this, new boolean[]{true, true, true, false, false, false}, new PickerViewUtils.GetTimeInterface() {
                    @Override
                    public void getTime(Date time) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        mTvDqsj.setText(simpleDateFormat.format(time));
                    }
                });
                break;
            case R.id.tv_zqsc:
                listStr.clear();
                listStr.add("3个月");
                listStr.add("6个月");
                listStr.add("9个月");
                listStr.add("12个月");
                listStr.add("24个月");
                PickerViewUtils.selectorCustom(this, listStr, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvZqsc.setText(listStr.get(pos));
                    }
                });
                break;
            case R.id.bnt:
                if(data==null){
                    ToastUtils.showShort("为获取到房源信息");
                    return;
                }
                UserContractBean.ListBean mUserContractBean = new UserContractBean.ListBean();
                mUserContractBean.setAdviserId(data.getAdviserId());
                mUserContractBean.setAppointmentId(appointmentId);
                mUserContractBean.setCertificateNumber(mEtZjhm.getText().toString());
                mUserContractBean.setCertificatePhoto("jztp.jpn");
                mUserContractBean.setCertificateType("1");
                mUserContractBean.setDeposit(et_yj.getText().toString());
                mUserContractBean.setRentAmount(et_zj.getText().toString());
                mUserContractBean.setHouseId(data.getHouseId());
                mUserContractBean.setRentType(rentType);
                String rentalDuration = mTvZqsc.getText().toString();
                if(StringUtil.isNotBlank(rentalDuration) && rentalDuration .contains("个月")){
                    rentalDuration = rentalDuration.replace("个月","");
                    mUserContractBean.setRentalDuration(rentalDuration);
                }
                mUserContractBean.setEmergencyRelationship(mEtLxr.getText().toString());
                mUserContractBean.setEmergencyPhone(mEtLxtdh.getText().toString());
                mUserContractBean.setStartTime(mTvQzsj.getText().toString()+" 00:00:00");
                mUserContractBean.setEndTime(mTvDqsj.getText().toString()+" 00:00:00");
                Intent mIntent = new Intent(this,MineHtNew2Activity.class);
                mIntent.putExtra("mUserContractBean",mUserContractBean);
                startActivity(mIntent);
                break;
        }
    }
}
