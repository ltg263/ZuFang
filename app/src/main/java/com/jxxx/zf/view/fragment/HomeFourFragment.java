package com.jxxx.zf.view.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.HttpsUtils;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.bean.UserInfoBean;
import com.jxxx.zf.utils.DialogUtils;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.SharedUtils;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.view.activity.MineApplyFdActivity;
import com.jxxx.zf.view.activity.MineApplyGwActivity;
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
import com.jxxx.zf.view.adapter.MineCygjAdapter;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.ll_below_1)
    LinearLayout mLlBelow1;
    @BindView(R.id.ll_below_2)
    LinearLayout mLlBelow2;
    @BindView(R.id.ll_below_3)
    LinearLayout mLlBelow3;
    @BindView(R.id.ll_below_4)
    LinearLayout mLlBelow4;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MineCygjAdapter mMineCygjAdapter;
    List<String> list = new ArrayList<>();

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_four;
    }

    @Override
    protected void initView() {
        mMineCygjAdapter = new MineCygjAdapter(null);
        mRvList.setAdapter(mMineCygjAdapter);
        mMineCygjAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (mMineCygjAdapter.getData().get(position)){

                    case "我的收藏":
                        baseStartActivity(MineListScActivity.class, null);
                        break;
                    case "我的租客":
                        baseStartActivity(MineKhListActivity.class, null);
                        break;
                    case "实名认证":
                        switch (SharedUtils.getIdentityFlag()){
                            case "0":// 0未提交 1审核中 2已通过 3审核失败
                            case "3":// 0未提交 1审核中 2已通过 3审核失败
                                baseStartActivity(MineSetSmrzActivity.class);
                                break;
                            case "1":
                                DialogUtils.showDialogHint(mContext, "实名审核中", true, null);
                                break;
                            case "2":
                                DialogUtils.showDialogHint(mContext, "已实名", true, null);
                                break;
                        }
                        break;
                    case "我的客户":
                        baseStartActivity(MineKhListActivity.class, null);
                        break;
                    case "我的接单":
                        baseStartActivity(MineJieDanActivity.class, null);
                        break;
                    case "看房状态":

                        break;
                    case "房源上架":
                        baseStartActivity(MineFylrActivity.class, null);
                        break;
                    case "成为房东":
                        if(!SharedUtils.getIdentityFlag().equals("2")){
                            DialogUtils.showDialogHint(mContext, "请先进行实名认证", false,
                                    new DialogUtils.ErrorDialogInterface() {
                                @Override
                                public void btnConfirm() {
                                    baseStartActivity(MineSetSmrzActivity.class);
                                }
                            });
                            return;
                        }
                        baseStartActivity(MineApplyFdActivity.class);
                        break;
                    case "成为顾问":
                        if(!SharedUtils.getIdentityFlag().equals("2")){
                            DialogUtils.showDialogHint(mContext, "请先进行实名认证", false,
                                    new DialogUtils.ErrorDialogInterface() {
                                        @Override
                                        public void btnConfirm() {
                                            baseStartActivity(MineSetSmrzActivity.class);
                                        }
                                    });
                            return;
                        }
                        baseStartActivity(MineApplyGwActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }
    }

    @Override
    protected void initData() {
        if (StringUtil.isNotBlank(SharedUtils.getToken()) && SharedUtils.singleton().get(ConstValues.ISLOGIN, false)) {
            getUserDetails();

        } else {
            mTvUserName.setText("请先登录");
            Glide.with(mContext).load(R.mipmap.icon_logo).thumbnail(0.1f)
                    .apply(bitmapTransform(new CropCircleTransformation())).into(mTvUserImg);
            list.clear();
            list.add("我的收藏");
            list.add("我的租客");
            list.add("实名认证");
            list.add("我的客户");
            list.add("我的接单");
            list.add("看房状态");
            list.add("房源上架");
            list.add("成为房东");
            list.add("成为顾问");
            mMineCygjAdapter.setNewData(list);
        }
    }

    private void getUserDetails() {
        switch (SharedUtils.singleton().get(ConstValues.USER_TYPE, 0)) {
            case 0:
                mTvUserInfo.setText("普通用户");
                list.clear();
                list.add("我的收藏");
                list.add("实名认证");
                list.add("看房状态");
                list.add("成为房东");
                list.add("成为顾问");


                list.add("我的接单");
                list.add("房源上架");
                mMineCygjAdapter.setNewData(list);
                break;
            case 1:
                mTvUserInfo.setText("房东");
                list.clear();
                list.add("我的收藏");
                list.add("我的租客");
                list.add("实名认证");
                list.add("我的客户");
                list.add("看房状态");
                list.add("房源上架");
                mMineCygjAdapter.setNewData(list);
                break;
            case 2:
                mTvUserInfo.setText("顾问");
                list.clear();
                list.add("我的收藏");
                list.add("我的租客");
                list.add("实名认证");
                list.add("我的接单");
                list.add("看房状态");
                list.add("房源上架");
                list.add("成为房东");
                mMineCygjAdapter.setNewData(list);
                break;
            case 3:
                mTvUserInfo.setText("房东|顾问");
                list.clear();
                list.add("我的收藏");
                list.add("我的租客");
                list.add("实名认证");
                list.add("我的客户");
                list.add("我的接单");
                list.add("看房状态");
                list.add("房源上架");
                break;
        }
        HttpsUtils.getUserDetails(new HttpsUtils.UserDetailsInterface() {
            @Override
            public void succeed(UserInfoBean result) {
                mTvUserName.setText(result.getNickname());
                GlideImageLoader.loadImageViewWithCirclr(getContext(), result.getPortraitUri(), mTvUserImg);
                if(result.isAdviser() && result.isLandlord()){//房东顾问双身份
                    SharedUtils.singleton().put(ConstValues.USER_TYPE,3);
                }else if(result.isAdviser()){//房东
                    SharedUtils.singleton().put(ConstValues.USER_TYPE,2);
                }else if(result.isLandlord()){//顾问
                    SharedUtils.singleton().put(ConstValues.USER_TYPE,1);
                }else{//用户
                    SharedUtils.singleton().put(ConstValues.USER_TYPE,0);
                }
                SharedUtils.singleton().put(ConstValues.USERID,result.getId());
                SharedUtils.singleton().put(ConstValues.NICK_NAME,result.getNickname());
                SharedUtils.singleton().put(ConstValues.USER_NO,result.getUserNo());
                SharedUtils.singleton().put(ConstValues.IDENTITY_FLAG,result.getIdentityFlag());
                SharedUtils.singleton().put(ConstValues.PORTRAIT_URI,result.getPortraitUri());
                if(SharedUtils.getIdentityFlag().equals("3")){
                    DialogUtils.showDialogHint(mContext, "实名失败\n请重新提交审核", false, new DialogUtils.ErrorDialogInterface() {
                        @Override
                        public void btnConfirm() {
                            baseStartActivity(MineSetSmrzActivity.class);
                        }
                    });
                }
            }

            @Override
            public void failure() {
            }
        });
    }

    @OnClick({R.id.rl_user_info, R.id.ll_top_1, R.id.ll_top_2, R.id.ll_top_3, R.id.ll_top_4,
            R.id.ll_below_1, R.id.ll_below_2, R.id.ll_below_3, R.id.ll_below_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_user_info:
                baseStartActivity(LoginActivity.class, null);
                break;
            case R.id.ll_top_1:
                baseStartActivity(MineHtListActivity.class, null);
                break;
            case R.id.ll_top_2:
                baseStartActivity(MineJfzdActivity.class, null);
                break;
            case R.id.ll_top_3:
                baseStartActivity(MineYyzxListActivity.class, null);
                break;
            case R.id.ll_top_4:
                baseStartActivity(ActivityPayHomeQb.class, null);
                break;
            case R.id.ll_below_1:
                baseStartActivity(MineSettingActivity.class, null);
                break;
            case R.id.ll_below_2:
                break;
            case R.id.ll_below_3:
                baseStartActivity(MineSetGyActivity.class, null);
                break;
            case R.id.ll_below_4:
                break;
        }
    }
}



