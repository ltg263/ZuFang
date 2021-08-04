package com.jxxx.zf.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.DialogUtils;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.IntentUtils;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.utils.ToastUtil;
import com.jxxx.zf.view.activity.MineHtNew1Activity;
import com.jxxx.zf.view.activity.MineHtNew3Activity;
import com.jxxx.zf.view.activity.MineQianYueActivity;
import com.jxxx.zf.view.activity.MineYypjActivity;
import com.jxxx.zf.view.activity.UserInfoListActivity;
import com.jxxx.zf.view.activity.ZuFangYyActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZuFangYyxq1Fragment extends BaseFragment {
    @BindView(R.id.head_icon)
    ImageView mHeadIcon;
    @BindView(R.id.name_type)
    TextView mNameType;
    @BindView(R.id.year)
    TextView mYear;
    @BindView(R.id.tv_lables_1)
    TextView mTvLables1;
    @BindView(R.id.tv_lables_2)
    TextView mTvLables2;
    @BindView(R.id.ll_type)
    LinearLayout mLlType;
    @BindView(R.id.tv_je)
    TextView mTvJe;
    @BindView(R.id.tv_je1)
    TextView mTvJe1;
    @BindView(R.id.tv_llcs)
    TextView mTvLlcs;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_bz)
    TextView mTvBz;
    @BindView(R.id.tv_dkrxm)
    TextView mTvDkrxm;
    @BindView(R.id.tv_yyr)
    TextView tv_yyr;
    @BindView(R.id.bnt_1)
    TextView mBnt1;
    @BindView(R.id.bnt_2)
    TextView mBnt2;
    @BindView(R.id.bnt_3)
    TextView mBnt3;
    @BindView(R.id.hasVideo)
    TextView hasVideo;
    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.tv_hasVideo)
    TextView tv_hasVideo;
    @BindView(R.id.iv_status_1)
    ImageView mIvStatus1;
    @BindView(R.id.iv_status_2)
    ImageView mIvStatus2;
    @BindView(R.id.iv_status_3)
    ImageView mIvStatus3;
    @BindView(R.id.iv_status_4)
    ImageView mIvStatus4;
    int type = 0;
    String id = "";
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_zu_fang_yyxq_1;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();

        if (bundle != null) {
            id = bundle.getString("id");
            type = bundle.getInt("type");
        }
    }

    @Override
    protected void initData() {
        RetrofitUtil.getInstance().apiService()
                .getAppointmentDetails(id)
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
                            initUi(result.getData());
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

    AppointmentDetailsBase data;

    private void initUi(AppointmentDetailsBase data) {
        this.data = data;
        ZuFangDetailsBase dataHouse = data.getHouse();
        if (dataHouse != null) {
            GlideImageLoader.loadImageAndDefault(getActivity(), dataHouse.getImgUrl(), mHeadIcon);
            String rentingType = StringUtil.getRentingType(dataHouse.getRentingType());
            mNameType.setText(rentingType + dataHouse.getName());
            mYear.setText(rentingType + dataHouse.getArea() + "m²·" + StringUtil.getHouseOrientation(dataHouse.getOrientation()) + "|" + dataHouse.getHousingEstateName());
            if (dataHouse.getLables() != null) {
                for (int i = 0; i < dataHouse.getLables().size(); i++) {
                    if (i == 0) {
                        mTvLables1.setVisibility(View.VISIBLE);
                        mTvLables1.setText(dataHouse.getLables().get(0).getName());
                    }
                    if (i == 1) {
                        mTvLables2.setVisibility(View.VISIBLE);
                        mTvLables2.setText(dataHouse.getLables().get(1).getName());
                    }
                }
            }

            mTvJe.setText(dataHouse.getRent());
            mTvLlcs.setText("约看" + dataHouse.getViewNum() + "人");
        }
        hasVideo.setVisibility(View.GONE);
        tv_hasVideo.setVisibility(View.GONE);
        if(data.getHouse().getHasVideo().equals("1")){
            hasVideo.setVisibility(View.VISIBLE);
            tv_hasVideo.setVisibility(View.VISIBLE);
        }
        mTvTime.setText(data.getAppointmentTime());
        mTvName.setText("姓名：" + data.getRealName());
        mTvPhone.setText("电话：" + data.getMobile());
        mTvBz.setText(data.getRemark());
        if (data.getHasAdviser().equals("1")) {
            tv_yyr.setVisibility(View.VISIBLE);
            mTvDkrxm.setVisibility(View.VISIBLE);
            mTvDkrxm.setText("姓名：" + data.getAdvserName());
        }

        mBnt1.setVisibility(View.INVISIBLE);
        mBnt2.setVisibility(View.INVISIBLE);
        mBnt3.setVisibility(View.INVISIBLE);

        if(type==0){
            showYyxqUi();//预约详情
        }else{
            showJdxqUi();//接单详情
        }
    }
    //1 已预约 2 已接单 3已认证 4看房中 5未签约 6已签约 7待签约 8完成 9取消
    private void showJdxqUi(){
        switch (data.getStatus()) {
            case "1":
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("接单");
                mIvStatus1.setImageResource(R.drawable.group_927);
                break;
            case "2":
                mBnt1.setVisibility(View.VISIBLE);
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt1.setText("联系对方");
                mBnt2.setText("转单");
                mBnt3.setText("去认证");
                break;
            case "3":
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("去看房");
                break;
            case "4":
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                mBnt1.setVisibility(View.VISIBLE);
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt1.setText("线上签约");
                mBnt2.setText("线下签约");
                mBnt3.setText("不签约");
                break;
            case "5":
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                mIvStatus4.setImageResource(R.drawable.group_927);
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("待评价");
                tv_state.setText("未签约");
                break;
            case "6":
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("待评价");
                tv_state.setText("已签约");
                break;
            case "7":
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                mIvStatus4.setImageResource(R.drawable.group_927);
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("待签约");
                break;
            case "8":
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                mIvStatus4.setImageResource(R.drawable.group_927);
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("已完成");
                tv_state.setText("已完成");
                break;
        }
    }

    //1 已预约 2 已接单 3已认证 4看房中 5未签约 6已签约 7待签约 8完成 9取消
    private void showYyxqUi() {
        switch (data.getStatus()) {
            case "1":
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("更改预约");
                mBnt3.setText("取消预约");
                mIvStatus1.setImageResource(R.drawable.group_927);
                break;
            case "2":
                mBnt1.setVisibility(View.VISIBLE);
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt1.setText("更改预约");
                mBnt2.setText("联系对方");
                mBnt3.setText("取消预约");
                mIvStatus1.setImageResource(R.drawable.group_927);
                break;
            case "3":
                mBnt1.setVisibility(View.VISIBLE);
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt1.setText("更改预约");
                mBnt2.setText("联系对方");
                mBnt3.setText("取消预约");
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
            case "4":
                mBnt3.setVisibility(View.VISIBLE);
                mBnt3.setText("联系对方");
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                break;
            case "5":
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("评价");
                tv_state.setText("未签约");
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                mIvStatus4.setImageResource(R.drawable.group_927);
                break;
            case "6":
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("评价");
                tv_state.setText("已签约");
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                mIvStatus4.setImageResource(R.drawable.group_927);
                break;
            case "7":
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("签约");
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                break;
            case "8":
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                mIvStatus4.setImageResource(R.drawable.group_927);
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("已完成");
                tv_state.setText("已完成");
                break;
        }
    }


    @OnClick({R.id.bnt_1, R.id.bnt_2, R.id.bnt_3})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bnt_1:
            case R.id.bnt_2:
            case R.id.bnt_3:
                if(type==0){//预约详情
                    setOnClickListenerYyxq(((TextView) view).getText().toString());
                }else{//接单详情
                    setOnClickListenerJdxq(((TextView) view).getText().toString());
                }
                break;
        }
    }

    private void setOnClickListenerJdxq(String str) {
        switch (str){
            case "联系对方":
                IntentUtils.startActivityPhone(mContext, data.getMobile());
                break;
            case "去看房":
                DialogUtils.showDialogHint(mContext, "确认现在去看房吗？", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        adviserUpdate(data.getId(),"4");
                    }
                });
                break;
            case "去认证":
                DialogUtils.showDialogHint(mContext, "进行人脸识别", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        adviserUpdate(data.getId(),"3");
                    }
                });
                break;
            case "接单":
                DialogUtils.showDialogHint(mContext, "确认接单吗？", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        receivingOrder(data.getId());
                    }
                });
                break;
            case "不签约":
                DialogUtils.showDialogHint(mContext, "确定取消签约吗？", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        adviserUpdate(data.getId(),"5");
                    }
                });
                break;
            case "线上签约":
                DialogUtils.showDialogHint(mContext, "确定线上进行签约吗？", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        adviserUpdate(data.getId(),"7");
                    }
                });
                break;
            case "线下签约":
                DialogUtils.showDialogHint(mContext, "确定线下进行签约吗？", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        adviserUpdate(data.getId(),"6");
                    }
                });
                break;
            case "转单"://
                Intent mIntent = new Intent(mContext, UserInfoListActivity.class);
                mIntent.putExtra("appointTime",data.getAppointmentTime());
                mIntent.putExtra("houseId",data.getHouseId());
                mIntent.putExtra("appointmentId",data.getId());
                mIntent.putExtra("isZhuanDan","0");
                startActivity(mIntent);
                break;
            case "已完成":
                break;
        }
    }
    private void adviserUpdate(String appointmentId, String status){
        RetrofitUtil.getInstance().apiService()
                .updateStatus(appointmentId,status)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isResultOk(result)) {
                            initData();
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
    private void receivingOrder(String appointmentId){
        RetrofitUtil.getInstance().apiService()
                .receivingOrder(appointmentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isResultOk(result)) {
                            initData();
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
    private void setOnClickListenerYyxq(String str) {
        switch (str) {
            case "签约":
                Intent mIntent = new Intent(getActivity(),MineHtNew3Activity.class);
                mIntent.putExtra("appointmentId",id);
                startActivity(mIntent);
                break;
            case "评价":
                MineYypjActivity.startActivity_pj(mContext,data.getAdviserId(),data.getId());
                break;
            case "取消预约":
                DialogUtils.showDialogHint(mContext, "确定取消预约吗？", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        getAppointmentCancel();
                    }
                });
                break;
            case "更改预约":
                ZuFangYyActivity.startActivityYyUpdata(mContext,data);
                break;
            case "联系对方":
                IntentUtils.startActivityPhone(mContext, data.getAdviserMobile());
                break;
        }
    }

    private void getAppointmentCancel() {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .getAppointmentCancel(data.getId())
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
                            ToastUtils.showLong("取消成功");
                            initData();
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
