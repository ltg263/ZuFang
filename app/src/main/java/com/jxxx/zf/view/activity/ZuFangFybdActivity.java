package com.jxxx.zf.view.activity;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.HouseCompareBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.utils.view.GridDivider;
import com.jxxx.zf.view.adapter.ZfxqFwssAdapter;
import com.jxxx.zf.view.adapter.ZuFangDbAdapter;
import com.jxxx.zf.view.adapter.ZuFangDbAdapterFzDz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    String houseIds;
    String houseNames;
    List<String> houseNameLists =new ArrayList<>();
    List<String> houseIdLists =new ArrayList<>();
    boolean hideSame = false;
    @Override
    public int intiLayout() {
        return R.layout.activity_zu_fang_fybd;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "房源对比");
        houseIds = getIntent().getStringExtra("houseIds");
        houseNames = "隐藏相同,"+getIntent().getStringExtra("houseNames");
        List<String> arrFyName = Arrays.asList(houseNames.split(","));
        for(int i=0;i<arrFyName.size();i++){
            houseNameLists.add(arrFyName.get(i));
        }
        List<String> arrFyId = Arrays.asList(houseIds.split(","));
        for(int i=0;i<arrFyId.size();i++){
            houseIdLists.add(arrFyId.get(i));
        }
        mZuFangDbAdapterFzDz = new ZuFangDbAdapterFzDz(houseNameLists);
        mRvListTop.setAdapter(mZuFangDbAdapterFzDz);
        mZuFangDbAdapterFzDz.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
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
                        if(mZuFangDbAdapterFzDz.getData().size()==3){
                            ToastUtils.showLong("不可删除");
                            return;
                        }
                        mZuFangDbAdapterFzDz.remove(position);
                        houseIdLists.remove(position-1);
                        houseIds = "";
                        for(int i= 0;i<houseIdLists.size();i++){
                            if (i==0) {
                                houseIds = houseIdLists.get(i);
                            }else{
                                houseIds = houseIds+","+houseIdLists.get(i);
                            }
                        }
                        Log.w("houseIds","houseIds"+houseIds);
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
                .houseCompare(houseIds,hideSame?"1":null)
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
