package com.jxxx.zf.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.AdviserListBean;
import com.jxxx.zf.bean.AppointmentList;
import com.jxxx.zf.bean.HomeZuFangListBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.view.adapter.UserInfoListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserInfoListActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private UserInfoListAdapter mUserInfoListAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_user_info_list;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "选择带看");
        String id = getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH);
        mUserInfoListAdapter = new UserInfoListAdapter(null,id);
        mRvList.setAdapter(mUserInfoListAdapter);

        mUserInfoListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZuFangYyActivity.mAdviserListBean = mUserInfoListAdapter.getData().get(position);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        showLoading();
        RetrofitUtil.getInstance().apiService()//
                .getAdviserList(getIntent().getStringExtra("appointTime"),getIntent().getStringExtra("houseId"))
//                .getAdviserList(null,getIntent().getStringExtra("houseId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AdviserListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AdviserListBean> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null) {
                            mUserInfoListAdapter.setNewData(result.getData().getList());
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
