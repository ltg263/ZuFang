package com.jxxx.zf.view.activity;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.HouseCompareBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.view.GridDivider;
import com.jxxx.zf.view.adapter.ZfxqFwssAdapter;
import com.jxxx.zf.view.adapter.ZuFangDbAdapter;
import com.jxxx.zf.view.adapter.ZuFangDbAdapterFzDz;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZuFangFybdActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_top)
    RecyclerView mRvListTop;
    ZuFangDbAdapter mZuFangDbAdapter;
    private ZuFangDbAdapterFzDz mZuFangDbAdapterFzDz;
    String str;
    boolean hideSame = false;
    @Override
    public int intiLayout() {
        return R.layout.activity_zu_fang_fybd;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "房源对比");
        str = "1,2,3,4,5,6";
        List<String> houseName = new ArrayList<>();
        houseName.add("隐藏相同");
        houseName.add("小区名称1");
        houseName.add("小区名称2");
        houseName.add("小区名称3");
        houseName.add("小区名称4");
        houseName.add("小区名称5");
        houseName.add("小区名称6");
        mZuFangDbAdapterFzDz = new ZuFangDbAdapterFzDz(houseName);
        mRvListTop.setAdapter(mZuFangDbAdapterFzDz);
        mZuFangDbAdapterFzDz.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_select:
                        ImageView iv = (ImageView) view;
                        iv.setSelected(!iv.isSelected());
                        hideSame = iv.isSelected();
                        initData();
                        break;
                    case R.id.iv_select1:
                        houseName.clear();
                        houseName.add("隐藏相同");
                        houseName.add("小区名称1");
                        houseName.add("小区名称2");
                        houseName.add("小区名称3");
                        houseName.add("小区名称4");
                        houseName.add("小区名称5");
                        mZuFangDbAdapterFzDz.setNewData(houseName);
                        str = "1,2,3,4,5";
                        initData();
                        break;
                }
            }
        });

        mZuFangDbAdapter = new ZuFangDbAdapter(null);
        mRvList.setAdapter(mZuFangDbAdapter);
//        mRvList.addItemDecoration(new GridDivider(this, 1, 5,this.getResources().getColor(R.color.colorAccent)));
    }

    @Override
    public void initData() {
        RetrofitUtil.getInstance().apiService()
                .houseCompare(str,hideSame?"1":null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<HouseCompareBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<HouseCompareBean>> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null){
                            mZuFangDbAdapter.setNewData(result.getData());
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
