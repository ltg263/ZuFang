package com.jxxx.zf.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.DialogUtils;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.view.activity.ChatActivity;
import com.jxxx.zf.view.activity.MineYypjActivity;
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
    @BindView(R.id.tv_hasVideo)
    TextView tv_hasVideo;
    @BindView(R.id.hasVideo)
    TextView hasVideo;
    @BindView(R.id.iv_status_1)
    ImageView mIvStatus1;
    @BindView(R.id.iv_status_2)
    ImageView mIvStatus2;
    @BindView(R.id.iv_status_3)
    ImageView mIvStatus3;
    @BindView(R.id.iv_status_4)
    ImageView mIvStatus4;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_zu_fang_yyxq_1;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String id = "";
        if (bundle != null) {
            id = bundle.getString("id");
        }
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
            mNameType.setText(dataHouse.getRentingType().equals("1") ? "合租·" : "整租·" + dataHouse.getName());
            mYear.setText(dataHouse.getRentingType().equals("1") ? "合租·" : "整租·" + dataHouse.getArea() + "m²·" + StringUtil.getHouseOrientation(dataHouse.getOrientation()) + "|" + dataHouse.getHousingEstateName());
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

//        var actions = [String].init()
//        if loginUserManager.adviser {
//            switch appointmentModel.status {
//                case 3:
//                    actions = ["联系对方", "去看房"]
//                case 4:
//                    actions = ["不签约", "线下签约", "线上签约"]
//                default:
//                    actions = ["联系对方", "去认证"]
//            }
//        } else {
//            switch appointmentModel.status {
//                case 1:
//                    if appointmentModel.hasAdviser == 0 {
//                    actions = ["取消预约"]
//                }
//                case 6:
//                    actions = ["联系对方", "去评价"]
//                default:
//                    actions = ["联系对方", "取消预约"]
//            }
//        }
        //1 已预约 2 已接单 3已认证 4看房中 5已完成 6已签约 7已取消
        switch (data.getStatus()) {
            case "1":
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
            case "5":
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("取消预约");
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                break;
            case "6":
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_927);
                mIvStatus3.setImageResource(R.drawable.group_927);
                mIvStatus4.setImageResource(R.drawable.group_927);
                mBnt2.setVisibility(View.VISIBLE);
                mBnt3.setVisibility(View.VISIBLE);
                mBnt2.setText("联系对方");
                mBnt3.setText("评价");
                mIvStatus1.setImageResource(R.drawable.group_927);
                mIvStatus2.setImageResource(R.drawable.group_930);
                break;
//            case "7":
//                helper.setVisible(R.id.bnt_3, true).setText(R.id.bnt_3, "电话联系");
//                break;
        }
    }


    @OnClick({R.id.bnt_1, R.id.bnt_2, R.id.bnt_3})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bnt_1:
            case R.id.bnt_2:
            case R.id.bnt_3:
                setOnClickListener(((TextView) view).getText().toString());
                break;
        }
    }


    private void setOnClickListener(String str) {
        switch (str) {
            case "评价":
                baseStartActivity(MineYypjActivity.class, null);
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
