package com.jxxx.zf.view.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.ApplyInfoBean;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.utils.view.RatingBar;
import com.jxxx.zf.utils.view.ShoppingFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineYypjActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.flowlayout)
    ShoppingFlowLayout mFlowlayout;
    @BindView(R.id.select_num)
    RatingBar mSelectNum;
    @BindView(R.id.tv_t)
    TextView mTvT;
    @BindView(R.id.iv_select)
    ImageView mIvSelect;
    @BindView(R.id.et_contact)
    EditText mEtContact;
    @BindView(R.id.tv_num)
    TextView mTvNum;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_yypj;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "评价");
        setHistorySearchData();
        mSelectNum.setStar(5.0f);
        mSelectNum.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                mSelectNum.setStarNum(ratingCount);
                setTvEvaluatel((int) ratingCount, mTvT);
            }
        });
        mEtContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTvNum.setText(i+"/1000");
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void initData() {

    }

    private void setHistorySearchData() {
        List<String> mHistoryList = new ArrayList<>();
        mHistoryList.add("服务态度好");
        mHistoryList.add("不错");
        mHistoryList.add("很有耐心");
        mHistoryList.add("有责任的人");
        mHistoryList.add("态度好");
        mHistoryList.add("不错");
        mHistoryList.add("耐心");
        mHistoryList.add("是个有责任的人");
        mHistoryList.add("服务态度好");
        mHistoryList.add("不错");
        mHistoryList.add("很有耐心");
        mHistoryList.add("是个有责任的人");
        //往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        if (mFlowlayout != null) {
            mFlowlayout.removeAllViews();
        }
        for (int i = 0; i < mHistoryList.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(25, 10, 25, 10);
            tv.setText(mHistoryList.get(i));
            tv.setMaxEms(10);
            tv.setSingleLine();
            tv.setTextColor(getResources().getColor(R.color.color_blue_theme));
            tv.setBackgroundResource(R.drawable.circle_solid_eee3ff_25);
            tv.setLayoutParams(layoutParams);
            final String name = mHistoryList.get(i);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv.setTextColor(getResources().getColor(R.color.white));
                    tv.setBackgroundResource(R.drawable.circle_solid_theme_25);
                }
            });
            mFlowlayout.addView(tv, layoutParams);
        }
    }

    private void setTvEvaluatel(int ratingCount, TextView teviewEvaluate) {
        switch (ratingCount) {
            case 5:
                teviewEvaluate.setText("非常好");
                break;
            case 4:
                teviewEvaluate.setText("好");
                break;
            case 3:
                teviewEvaluate.setText("一般");
                break;
            case 2:
                teviewEvaluate.setText("差");
                break;
            case 1:
            case 0:
                teviewEvaluate.setText("非常差");
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.iv_select, R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_select:
                if (mIvSelect.isSelected()) {
                    mIvSelect.setSelected(false);
                } else {
                    mIvSelect.setSelected(true);
                }
                break;
            case R.id.bnt:
                getAdviserList();
                break;
        }
    }

    private void getAdviserList() {
        if(StringUtil.isBlank(mEtContact.getText().toString())){
            ToastUtils.showLong("评价内容不能为空");
            return;
        }
        ApplyInfoBean.AppointmentComment mAppointmentComment = new ApplyInfoBean.AppointmentComment();
        mAppointmentComment.setAdviserId(getIntent().getStringExtra("adviserId"));
        mAppointmentComment.setAppointmentId(getIntent().getStringExtra("appointmentId"));
        mAppointmentComment.setDetail(mEtContact.getText().toString());
        mAppointmentComment.setScore(mSelectNum.getStarNum()+"");
        mAppointmentComment.setIsAnonymous(mIvSelect.isSelected()?"1":"0");
        RetrofitUtil.getInstance().apiService()
                .getAdviserList(mAppointmentComment)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        hideLoading();
                        if(isResultOk(result)){
//                            baseStartActivity(MineHtJcOkActivity.class,null);
                            ToastUtils.showLong("评论成功");
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
        return;
    }

    public static void startActivity_pj(Context mContext,String adviserId,String appointmentId){
        Intent mIntent = new Intent(mContext,MineYypjActivity.class);
        mIntent.putExtra("adviserId",adviserId);
        mIntent.putExtra("appointmentId",appointmentId);
        mContext.startActivity(mIntent);
    }
}
