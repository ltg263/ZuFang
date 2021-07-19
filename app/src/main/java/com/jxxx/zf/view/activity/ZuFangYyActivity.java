package com.jxxx.zf.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.R;
import com.jxxx.zf.api.HttpsUtils;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.AdviserListBean;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.AppointmentList;
import com.jxxx.zf.bean.ApponintmentApply;
import com.jxxx.zf.bean.HomeZuFangListBase;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.UserInfoBean;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.PickerViewUtils;
import com.jxxx.zf.utils.SharedUtils;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZuFangYyActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.iv_select)
    ImageView mIvSelect;
    @BindView(R.id.ll_dkgw)
    LinearLayout mLlDkgw;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_you)
    TextView mTvYou;
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
    @BindView(R.id.bnt_kf)
    TextView mBntKf;
    @BindView(R.id.tv_nickname)
    EditText tv_nickname;
    @BindView(R.id.tv_gender)
    TextView tv_gender;
    @BindView(R.id.tv_userNo)
    EditText tv_userNo;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_kfr)
    TextView tv_kfr;
    @BindView(R.id.tv_hasVideo)
    TextView tv_hasVideo;
    @BindView(R.id.hasVideo)
    TextView hasVideo;
    @BindView(R.id.openHomeTime)
    TextView openHomeTime;
    Intent intent;
    public static AdviserListBean.ListBean mAdviserListBean;
    @Override
    public int intiLayout() {
        return R.layout.activity_zu_fang_yy;
    }

    @Override
    public void initView() {
        mAdviserListBean = null;
        setToolbar(mMyToolbar, "房源预约");
        intent = getIntent();
        GlideImageLoader.loadImageAndDefault(this, getIntent().getStringExtra("imgUrl"), mHeadIcon);
        mNameType.setText(intent.getStringExtra("rentingName"));
        mYear.setText(intent.getStringExtra("rentingName_2"));
        if (StringUtil.isNotBlank(intent.getStringExtra("lables1"))) {
            mTvLables1.setText(intent.getStringExtra("lables1"));
        }
        if (StringUtil.isNotBlank(intent.getStringExtra("lables2"))) {
            mTvLables2.setText(intent.getStringExtra("lables2"));
        }
        mTvJe.setText(intent.getStringExtra("rent"));
        if(StringUtil.isNotBlank(intent.getStringExtra("openHomeTime"))){
            openHomeTime.setVisibility(View.VISIBLE);
            openHomeTime.setText("可预约时间:"+intent.getStringExtra("openHomeTime"));
        }
        mTvLlcs.setText(intent.getStringExtra("viewNum"));
        hasVideo.setVisibility(View.GONE);
        tv_hasVideo.setVisibility(View.GONE);
        if(intent.getStringExtra("hasVideo").equals("1")){
            hasVideo.setVisibility(View.VISIBLE);
            tv_hasVideo.setVisibility(View.VISIBLE);
        }
        if(StringUtil.isNotBlank(intent.getStringExtra("remark"))){
            tv_remark.setText(intent.getStringExtra("remark"));
        }
        if(StringUtil.isNotBlank(intent.getStringExtra("hasAdviser"))){
            String hasAdviser = intent.getStringExtra("hasAdviser");
            if(hasAdviser.equals("1")){
                if(StringUtil.isNotBlank(intent.getStringExtra("advserName"))){
                    tv_kfr.setText(intent.getStringExtra("advserName"));
                }
                mLlDkgw.setVisibility(View.VISIBLE);
                mIvSelect.setSelected(true);
                mAdviserListBean = new AdviserListBean.ListBean();
                mAdviserListBean.setId(intent.getStringExtra("adviserId"));
                mAdviserListBean.setRealName(intent.getStringExtra("advserName"));

                tv_nickname.setText(intent.getStringExtra("realName"));
                tv_gender.setText(intent.getStringExtra("gender").equals("1") ? "男" : "女");
                tv_userNo.setText(intent.getStringExtra("mobile"));
                if (StringUtil.isNotBlank(intent.getStringExtra("appointmentTime"))) {
                    tv_time.setText(intent.getStringExtra("appointmentTime"));
                }
            }
        }
    }

    @Override
    public void initData() {
        if(StringUtil.isNotBlank(intent.getStringExtra("adviserId"))){
            return;
        }
        showLoading();
        HttpsUtils.getUserDetails(new HttpsUtils.UserDetailsInterface() {
            @Override
            public void succeed(UserInfoBean result) {
                hideLoading();
                tv_nickname.setText(result.getNickname());
                tv_gender.setText(result.getGender().equals("1") ? "男" : "女");
                tv_userNo.setText(result.getUserNo());
            }

            @Override
            public void failure() {
                hideLoading();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mAdviserListBean!=null){
            tv_kfr.setText(mAdviserListBean.getRealName());
        }
    }

    @OnClick({R.id.tv_time,R.id.iv_select, R.id.ll_dkgw, R.id.bnt_kf,R.id.tv_gender})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_select:
                if (mIvSelect.isSelected()) {
                    mLlDkgw.setVisibility(View.GONE);
                    mIvSelect.setSelected(false);
                } else {
                    mLlDkgw.setVisibility(View.VISIBLE);
                    mIvSelect.setSelected(true);
                }

                break;
            case R.id.tv_gender:
                List<String> list = new ArrayList<>();
                list.add("男");
                list.add("女");
                PickerViewUtils.selectorCustom(this, list, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        tv_gender.setText(list.get(pos));
                    }
                });
                break;
            case R.id.ll_dkgw:
                if(StringUtil.isBlank(tv_time.getText().toString())){
                    ToastUtils.showLong("请先选择看房时间");
                    return;
                }
                Intent mIntent = new Intent(this,UserInfoListActivity.class);
                mIntent.putExtra("appointTime",tv_time.getText().toString());
                mIntent.putExtra("houseId",intent.getStringExtra("id"));
                if (mAdviserListBean!=null) {
                    mIntent.putExtra(ConstValues.APPNAME_ENGLISH,mAdviserListBean.getId());
                }
                startActivity(mIntent);
                break;
            case R.id.tv_time:
                PickerViewUtils.selectorDate(ZuFangYyActivity.this,
                        new boolean[]{true, true, true, true, true, true}, new PickerViewUtils.GetTimeInterface() {
                            @Override
                            public void getTime(Date time) {
                                mAdviserListBean = null;
                                tv_kfr.setText("");
                                mIvSelect.setSelected(false);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                tv_time.setText(simpleDateFormat.format(time));
                            }
                        });
                break;
            case R.id.bnt_kf:
                getAppointmentApply();
                break;
        }
    }

    private void getAppointmentApply() {
        ApponintmentApply mApponintmentApply = new ApponintmentApply();
        mApponintmentApply.setHasAdviser("0");
        mApponintmentApply.setId(intent.getStringExtra("appointmentId"));
        if(mAdviserListBean!=null && mIvSelect.isSelected()){
            mApponintmentApply.setHasAdviser("1");
            mApponintmentApply.setAdviserId(mAdviserListBean.getId());
            mApponintmentApply.setAdvserName(mAdviserListBean.getRealName());
        }
        mApponintmentApply.setAppointmentTime(tv_time.getText().toString());
        mApponintmentApply.setRemark(tv_remark.getText().toString());
        mApponintmentApply.setGender(tv_gender.getText().toString().equals("男")?"1":"2");
        mApponintmentApply.setHouseId(intent.getStringExtra("id"));
        mApponintmentApply.setMobile(tv_userNo.getText().toString());
        mApponintmentApply.setRealName(tv_nickname.getText().toString());
        mApponintmentApply.setUserId(SharedUtils.getUserId());
        Log.w("mApponintmentApply","mApponintmentApply:"+mApponintmentApply.toString());
        //更新
        if(StringUtil.isNotBlank(mApponintmentApply.getId())){
            RetrofitUtil.getInstance().apiService()
                    .getAppointmentApplyUpdate(mApponintmentApply)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Result<AppointmentDetailsBase>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Result<AppointmentDetailsBase> result) {
                            hideLoading();
                            if(isResultOk(result)){
                                baseStartActivity(ZuFangYyOkActivity.class, mApponintmentApply.getId());
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
            return;
        }
        RetrofitUtil.getInstance().apiService()
                .getAppointmentApply(mApponintmentApply)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AppointmentDetailsBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AppointmentDetailsBase> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null){
                            baseStartActivity(ZuFangYyOkActivity.class, result.getData().getId());
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
    public static void startActivityYyUi(Context mContext, ZuFangDetailsBase data){
        Intent mIntent = new Intent(mContext,ZuFangYyActivity.class);
        mIntent.putExtra("id",data.getId());
        mIntent.putExtra("imgUrl",data.getImgUrl());
        mIntent.putExtra("hasVideo",data.getHasVideo());
        mIntent.putExtra("openHomeTime",data.getOpenHomeTime());
        String rentingType = StringUtil.getRentingType(data.getRentingType());
        mIntent.putExtra("rentingName",rentingType + data.getName());
        mIntent.putExtra("rentingName_2",rentingType +data.getArea()+"m²·"+StringUtil.getHouseOrientation(data.getOrientation())+"|"+data.getHousingEstateName());
        if (data.getLables() != null) {
            for (int i = 0; i < data.getLables().size(); i++) {
                if (i == 0) {
                    mIntent.putExtra("lables1",data.getLables().get(i).getName());
                }
                if (i == 1) {
                    mIntent.putExtra("lables2",data.getLables().get(i).getName());
                }
            }
        }
        mIntent.putExtra("rent", data.getRent());
        mIntent.putExtra("viewNum","约看" + data.getViewNum() + "人");
        mContext.startActivity(mIntent);
    }


    public static void startActivityYyUpdata(Context mContext,AppointmentDetailsBase listBean) {
        Intent mIntent = new Intent(mContext, ZuFangYyActivity.class);
        ZuFangDetailsBase data = listBean.getHouse();
        mIntent.putExtra("appointmentId", listBean.getId());
        mIntent.putExtra("hasAdviser", listBean.getHasAdviser());
        mIntent.putExtra("id", data.getId());
        mIntent.putExtra("imgUrl", data.getImgUrl());
        mIntent.putExtra("remark", listBean.getRemark());
        mIntent.putExtra("realName", listBean.getRealName());
        mIntent.putExtra("gender", listBean.getGender());
        mIntent.putExtra("mobile", listBean.getMobile());
        mIntent.putExtra("hasVideo",data.getHasVideo());
        mIntent.putExtra("openHomeTime",data.getOpenHomeTime());
        mIntent.putExtra("appointmentTime", listBean.getAppointmentTime());
        mIntent.putExtra("advserName", listBean.getAdvserName());
        mIntent.putExtra("adviserId", listBean.getAdviserId());

        String rentingType = StringUtil.getRentingType(data.getRentingType());
        mIntent.putExtra("rentingName", rentingType + data.getName());
        mIntent.putExtra("rentingName_2", rentingType + data.getArea() + "m²·" + StringUtil.getHouseOrientation(data.getOrientation()) + "|" + data.getHousingEstateName());
        if (data.getLables() != null) {
            for (int i = 0; i < data.getLables().size(); i++) {
                if (i == 0) {
                    mIntent.putExtra("lables1", data.getLables().get(i).getName());
                }
                if (i == 1) {
                    mIntent.putExtra("lables2", data.getLables().get(i).getName());
                }
            }
        }
        mIntent.putExtra("rent", data.getRent());
        mIntent.putExtra("viewNum", "约看" + data.getViewNum() + "人");
        mContext.startActivity(mIntent);
    }
}
