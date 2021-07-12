package com.jxxx.zf.view.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.zf.MainActivity;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.ApplyInfoBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.SharedUtils;
import com.jxxx.zf.utils.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineApplyGwActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_number)
    EditText mEtNumber;
    @BindView(R.id.et_cxqy)
    TextView mEtCxqy;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_apply_gw;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "申请顾问");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.bnt,R.id.et_cxqy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_cxqy:

                break;
            case R.id.bnt:
                realNameAdviser();
                break;
        }
    }

    private void realNameAdviser() {
        String name = mEtName.getText().toString();
       String number = mEtNumber.getText().toString();
       if(StringUtil.isBlank(name) || StringUtil.isBlank(number)){
           ToastUtils.showLong("请输入完整信息");
           return;
       }
       if(StringUtil.checkNum(number)){
           ToastUtils.showLong("请输入有效的证件号码");
           return;
       }
        ApplyInfoBean.RealNameAdviser mRealNameAuthentication = new ApplyInfoBean.RealNameAdviser();
        mRealNameAuthentication.setRealName(name);
        mRealNameAuthentication.setMobile(SharedUtils.singleton().get(ConstValues.USER_NO,""));
        mRealNameAuthentication.setImgUrl(SharedUtils.singleton().get(ConstValues.PORTRAIT_URI,""));
//        mRealNameAuthentication.setBusinessAreaId(name);
        RetrofitUtil.getInstance().apiService()
                .realNameAdviser(mRealNameAuthentication)
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
                            finish();
                            baseStartActivity(MainActivity.class, null);
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
