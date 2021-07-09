package com.jxxx.zf.view.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jxxx.zf.R;
import com.jxxx.zf.api.HttpsUtils;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.bean.UserInfoBean;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.SharedUtils;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.view.activity.LoginActivity;
import com.jxxx.zf.view.activity.MineFylrActivity;
import com.jxxx.zf.view.activity.MineHtListActivity;
import com.jxxx.zf.view.activity.MineJfzdActivity;
import com.jxxx.zf.view.activity.MineJieDanActivity;
import com.jxxx.zf.view.activity.MineKhListActivity;
import com.jxxx.zf.view.activity.MineListScActivity;
import com.jxxx.zf.view.activity.MineSetGyActivity;
import com.jxxx.zf.view.activity.MineSetSmrzActivity;
import com.jxxx.zf.view.activity.MineSettingActivity;
import com.jxxx.zf.view.activity.MineYyzxListActivity;
import com.jxxx.zf.view.activity.payActivity.ActivityPayHomeQb;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class HomeFourFragment extends BaseFragment {

    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_info)
    TextView mTvUserInfo;
    @BindView(R.id.tv_user_img)
    ImageView mTvUserImg;
    @BindView(R.id.rl_user_info)
    RelativeLayout mRlUserInfo;
    @BindView(R.id.ll_top_1)
    LinearLayout mLlTop1;
    @BindView(R.id.ll_top_2)
    LinearLayout mLlTop2;
    @BindView(R.id.ll_top_3)
    LinearLayout mLlTop3;
    @BindView(R.id.ll_top_4)
    LinearLayout mLlTop4;
    @BindView(R.id.ll_center_1)
    LinearLayout mLlCenter1;
    @BindView(R.id.ll_center_2)
    LinearLayout mLlCenter2;
    @BindView(R.id.ll_center_3)
    LinearLayout mLlCenter3;
    @BindView(R.id.ll_center_4)
    LinearLayout mLlCenter4;
    @BindView(R.id.ll_center_5)
    LinearLayout mLlCenter5;
    @BindView(R.id.ll_center_6)
    LinearLayout mLlCenter6;
    @BindView(R.id.ll_center_7)
    LinearLayout mLlCenter7;
    @BindView(R.id.ll_center_8)
    LinearLayout mLlCenter8;
    @BindView(R.id.ll_center_9)
    LinearLayout mLlCenter9;
    @BindView(R.id.ll_below_1)
    LinearLayout mLlBelow1;
    @BindView(R.id.ll_below_2)
    LinearLayout mLlBelow2;
    @BindView(R.id.ll_below_3)
    LinearLayout mLlBelow3;
    @BindView(R.id.ll_below_4)
    LinearLayout mLlBelow4;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_four;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            initData();
        }
    }

    @Override
    protected void initData() {
        if(StringUtil.isNotBlank(SharedUtils.getToken()) && SharedUtils.singleton().get(ConstValues.ISLOGIN,false)){
            getUserDetails();
        }else{
            mTvUserName.setText("请先登录");
            Glide.with(mContext).load(R.mipmap.icon_logo).thumbnail(0.1f)
                    .apply(bitmapTransform(new CropCircleTransformation())).into(mTvUserImg);
        }
    }

    private void getUserDetails() {
        HttpsUtils.getUserDetails(new HttpsUtils.UserDetailsInterface() {
            @Override
            public void succeed(UserInfoBean result) {
                mTvUserName.setText(result.getNickname());
                GlideImageLoader.loadImageViewWithCirclr(getContext(),result.getPortraitUri(),mTvUserImg);
                int userType = SharedUtils.singleton().get(ConstValues.USER_TYPE,0);
                switch (userType){
                    case 0:
                        mTvUserInfo.setText("普通用户");
                        break;
                    case 1:
                        mTvUserInfo.setText("房东");
                        break;
                    case 2:
                        mTvUserInfo.setText("顾问");
                        break;
                    case 3:
                        mTvUserInfo.setText("房东|顾问");
                        break;
                }
            }

            @Override
            public void failure() {
            }
        });
    }

    @OnClick({R.id.rl_user_info, R.id.ll_top_1, R.id.ll_top_2, R.id.ll_top_3, R.id.ll_top_4, R.id.ll_center_1,
            R.id.ll_center_2, R.id.ll_center_3, R.id.ll_center_4, R.id.ll_center_5, R.id.ll_center_6, R.id.ll_center_7,
            R.id.ll_center_8, R.id.ll_center_9, R.id.ll_below_1, R.id.ll_below_2, R.id.ll_below_3, R.id.ll_below_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_user_info:
                baseStartActivity(LoginActivity.class,null);
                break;
            case R.id.ll_top_1:
                baseStartActivity(MineHtListActivity.class,null);
                break;
            case R.id.ll_top_2:
                baseStartActivity(MineJfzdActivity.class,null);
                break;
            case R.id.ll_top_3:
                baseStartActivity(MineYyzxListActivity.class,null);
                break;
            case R.id.ll_top_4:
                baseStartActivity(ActivityPayHomeQb.class,null);
                break;
            case R.id.ll_center_1:
                baseStartActivity(MineListScActivity.class,null);
                break;
            case R.id.ll_center_2:
                baseStartActivity(MineKhListActivity.class,null);
                break;
            case R.id.ll_center_3:
                baseStartActivity(MineSetSmrzActivity.class,null);
                break;
            case R.id.ll_center_4:
                baseStartActivity(MineKhListActivity.class,null);
                break;
            case R.id.ll_center_5:
                baseStartActivity(MineJieDanActivity.class,null);
                break;
            case R.id.ll_center_6:
                break;
            case R.id.ll_center_7:
                baseStartActivity(MineFylrActivity.class,null);
                break;
            case R.id.ll_center_8:
                break;
            case R.id.ll_center_9:
                break;
            case R.id.ll_below_1:
                baseStartActivity(MineSettingActivity.class,null);
                break;
            case R.id.ll_below_2:
                break;
            case R.id.ll_below_3:
                baseStartActivity(MineSetGyActivity.class,null);
                break;
            case R.id.ll_below_4:
                break;
        }
    }
}



