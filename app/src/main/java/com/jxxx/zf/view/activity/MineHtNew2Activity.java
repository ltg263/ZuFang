package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.UserContractBean;
import com.jxxx.zf.utils.PickerViewUtils;
import com.jxxx.zf.view.adapter.MineHtNew2Adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineHtNew2Activity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    UserContractBean.ListBean mUserContractBean;
    private MineHtNew2Adapter mMineHtNew2Adapter;

    List<String> lists = new ArrayList<>();

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_ht_new_2;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "物品清单确认");
        lists.add("1");
        lists.add("2");
        lists.add("3");
        lists.add("4");
        lists.add("5");
        mUserContractBean = getIntent().getParcelableExtra("mUserContractBean");
        mMineHtNew2Adapter = new MineHtNew2Adapter(null);
        mRvList.setAdapter(mMineHtNew2Adapter);
        mMineHtNew2Adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PickerViewUtils.selectorCustom(MineHtNew2Activity.this, lists, "", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mMineHtNew2Adapter.getData().get(position).setNum((pos+1)+"");
                        mMineHtNew2Adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void initData() {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .showContractItems(mUserContractBean.getAppointmentId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<UserContractBean.ListBean.ItemsBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<UserContractBean.ListBean.ItemsBean>> result) {
                        hideLoading();
                        if (isResultOk(result) && result.getData() != null) {
                            mMineHtNew2Adapter.setNewData(result.getData());
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

    @OnClick({R.id.bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt:
                mUserContractBean.setItems(mMineHtNew2Adapter.getData());
                Intent mIntent = new Intent(this,MineHtNew1Activity.class);
                mIntent.putExtra("mUserContractBean",mUserContractBean);
                startActivity(mIntent);
                break;
        }
    }
}
