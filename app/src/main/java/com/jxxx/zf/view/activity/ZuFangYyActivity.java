package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.HomeZuFangListBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.UserInfoBean;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.PickerViewUtils;
import com.jxxx.zf.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    TextView tv_nickname;
    @BindView(R.id.tv_gender)
    TextView tv_gender;
    @BindView(R.id.tv_userNo)
    TextView tv_userNo;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_kfr)
    TextView tv_kfr;

    @Override
    public int intiLayout() {
        return R.layout.activity_zu_fang_yy;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "房源预约");
        Intent intent = getIntent();
        GlideImageLoader.loadImageAndDefault(this,getIntent().getStringExtra("imgUrl"),mHeadIcon);
        mNameType.setText(intent.getStringExtra("rentingName"));
        mYear.setText(intent.getStringExtra("rentingName_2"));
        if(StringUtil.isNotBlank(intent.getStringExtra("lables1"))){
            mTvLables1.setText(intent.getStringExtra("lables1"));
        }
        if(StringUtil.isNotBlank(intent.getStringExtra("lables2"))){
            mTvLables2.setText(intent.getStringExtra("lables2"));
        }
        mTvJe.setText(intent.getStringExtra("rent"));
        mTvLlcs.setText(intent.getStringExtra("viewNum"));
        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickerViewUtils.selectorDate(ZuFangYyActivity.this,
                        new boolean[]{true, true, true, true, true, false}, new PickerViewUtils.GetTimeInterface() {
                    @Override
                    public void getTime(Date time) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        tv_time.setText(simpleDateFormat.format(time));
                    }
                });
            }
        });
    }

    @Override
    public void initData() {
        RetrofitUtil.getInstance().apiService()
                .getDetails()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserInfoBean> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null) {
                            tv_nickname.setText(result.getData().getNickname());
                            tv_gender.setText(result.getData().getGender().equals("1")?"男":"女");
                            tv_userNo.setText(result.getData().getUserNo());
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

    @OnClick({R.id.iv_select, R.id.ll_dkgw, R.id.bnt_kf})
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
            case R.id.ll_dkgw:
                baseStartActivity(UserInfoListActivity.class, null);
                break;
            case R.id.bnt_kf:
                baseStartActivity(ZuFangYyOkActivity.class, null);
                break;
        }
    }
}
