package com.jxxx.zf.view.fragment;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.view.activity.ChatActivity;

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
    @BindView(R.id.bnt_lxyx)
    TextView mBntLxyx;
    @BindView(R.id.bnt_qrgw)
    TextView mBntQrgw;

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

    private void initUi(AppointmentDetailsBase data) {
        ZuFangDetailsBase dataHouse = data.getHouse();
        if(dataHouse!=null){
            GlideImageLoader.loadImageAndDefault(getActivity(), dataHouse.getImgUrl(), mHeadIcon);
            mNameType.setText(dataHouse.getRentingType().equals("1") ? "合租·" : "合租·" + dataHouse.getName());
            String orientation = "";
            switch (dataHouse.getOrientation()) {
                case "1":
                    orientation = "朝东";
                    break;
                case "2":
                    orientation = "朝南";
                    break;
                case "3":
                    orientation = "朝西";
                    break;
                case "4":
                    orientation = "朝北";
                    break;
                case "5":
                    orientation = "朝东南";
                    break;
                case "6":
                    orientation = "朝西南";
                    break;
                case "7":
                    orientation = "朝东北";
                    break;
                case "8":
                    orientation = "朝西北";
                    break;
            }
            mYear.setText(dataHouse.getRentingType().equals("1") ? "合租·" : "合租·" +dataHouse.getArea()+"m²·"+orientation+"|"+dataHouse.getHousingEstateName());
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
            mTvLlcs.setText("约看"+dataHouse.getViewNum()+"人");
        }
        mTvTime.setText(StringUtil.getTimeToYMD(data.getAppointmentTime(),"yyyy-MM-dd HH:mm"));
        mTvName.setText("姓名："+data.getRealName());
        mTvPhone.setText("电话："+data.getMobile());
        mTvBz.setText(data.getRemark());
        mTvDkrxm.setText("姓名："+data.getAdvserName());
    }

    @OnClick({R.id.bnt_lxyx, R.id.bnt_qrgw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_lxyx:
                baseStartActivity(ChatActivity.class, null);
                break;
            case R.id.bnt_qrgw:

                break;
        }
    }
}
