package com.jxxx.zf.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.UserInfoBean;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.PickerViewUtils;
import com.jxxx.zf.utils.SharedUtils;
import com.jxxx.zf.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineInfoActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.head_icon)
    ImageView mHeadIcon;
    @BindView(R.id.nick_name)
    EditText mNickName;
    @BindView(R.id.gender)
    TextView mGender;
    @BindView(R.id.phone)
    EditText mPhone;

    List<String> lists = new ArrayList<>();
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_info;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "个人信息");
        GlideImageLoader.loadImageViewWithCirclr(this, SharedUtils.singleton().get(ConstValues.USER_AVATAR,""), mHeadIcon);
        mNickName.setText(SharedUtils.singleton().get(ConstValues.NICK_NAME,""));
        String gender = SharedUtils.singleton().get(ConstValues.USER_GENDER,"");
        lists.add("男");
        lists.add("女");

        if(gender.equals("1")){
            mGender.setText("男");
        }else if(gender.equals("2")){
            mGender.setText("女");
        }else{
            mGender.setText("未知");
        }
        mPhone.setText(SharedUtils.singleton().get(ConstValues.USER_NO,""));
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.head_icon, R.id.rl_gender,R.id.tv_bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_icon:

                break;
            case R.id.rl_gender:
                PickerViewUtils.selectorCustom(this, lists, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mGender.setText(lists.get(pos));
                    }
                });
                break;
            case R.id.tv_bnt:
                userUpdate();
                break;
        }
    }

    private void userUpdate() {
        String nickName = mNickName.getText().toString().trim();
        String mobile = mPhone.getText().toString().trim();
        String gender = mGender.getText().toString().trim();

        if(gender.equals("男")){
            gender = "1";
        }else if(gender.equals("女")){
            gender = "2";
        }else{
            gender = "0";
        }
        if(StringUtil.isBlank(nickName) || StringUtil.isBlank(mobile) || gender.equals("0")){
            ToastUtils.showShort("信息输入不完整");
            return;
        }
        RetrofitUtil.getInstance().apiService()
                .updateUserInfo("http://g1.dfcfw.com/g4/202103/20210310145220.png",nickName,gender,mobile)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getStatus()==0 && result.getData()!=null) {
                            ToastUtils.showShort("修改成功");
                            finish();
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
}
