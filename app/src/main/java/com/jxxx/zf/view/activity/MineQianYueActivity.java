package com.jxxx.zf.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineQianYueActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.head_icon)
    ImageView mHeadIcon;
    @BindView(R.id.tv_hasVideo)
    TextView mTvHasVideo;
    @BindView(R.id.name_type)
    TextView mNameType;
    @BindView(R.id.year)
    TextView mYear;
    @BindView(R.id.hasVideo)
    TextView mHasVideo;
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
    @BindView(R.id.iv_select)
    ImageView mIvSelect;
    @BindView(R.id.bnt)
    TextView mBnt;
    @BindView(R.id.et_realName)
    EditText mEtRealName;
    @BindView(R.id.et_realNum)
    EditText mEtRealNum;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.bnt_rq_rz)
    TextView mBntRqRz;
    @BindView(R.id.bnt_rq_dq)
    TextView mBntRqDq;
    @BindView(R.id.bnt_zffs)
    TextView mBntZffs;
    @BindView(R.id.bnt_fkfs)
    TextView mBntFkfs;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_qian_yue;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "签约");

    }

    @Override
    public void initData() {
        RetrofitUtil.getInstance().apiService()
                .getAppointmentDetails(getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH))
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
                            initUi(result.getData().getHouse());
                            mEtRealName.setText(result.getData().getRealName());
                            mEtMobile.setText(result.getData().getMobile());
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

    private void initUi(ZuFangDetailsBase house_info) {
        GlideImageLoader.loadImageAndDefault(this, house_info.getImgUrl(), mHeadIcon);
        mNameType.setText(house_info.getRentingType().equals("1") ? "合租·" : "整租·" + house_info.getName());
        mYear.setText(house_info.getRentingType().equals("1") ? "合租·" : "整租·" + house_info.getArea() + "m²·" +
                StringUtil.getHouseOrientation(house_info.getOrientation()) + "|" + house_info.getHousingEstateName());
        if (house_info.getLables() != null) {
            for (int i = 0; i < house_info.getLables().size(); i++) {
                if (i == 0) {
                    mTvLables1.setVisibility(View.VISIBLE);
                    mTvLables1.setText(house_info.getLables().get(0).getName());
                }
                if (i == 1) {
                    mTvLables2.setVisibility(View.VISIBLE);
                    mTvLables2.setText(house_info.getLables().get(1).getName());
                }
            }
        }
        mTvJe.setText(house_info.getRent());
        mTvLlcs.setText("约看" + house_info.getViewNum() + "人");
    }

    @OnClick({R.id.bnt_rq_rz, R.id.bnt_rq_dq, R.id.bnt_zffs, R.id.bnt_fkfs, R.id.iv_select, R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_rq_rz:
                break;
            case R.id.bnt_rq_dq:
                break;
            case R.id.bnt_zffs:
                break;
            case R.id.bnt_fkfs:
                break;
            case R.id.iv_select:
                break;
            case R.id.bnt:
                baseStartActivity(MineHtNew1Activity.class, null);
                break;
        }
    }
}
