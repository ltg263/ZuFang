package com.jxxx.zf.view.activity.search;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.view.adapter.HomeFyAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchResultTopicActivity extends BaseActivity {


    @BindView(R.id.include)
    Toolbar mMyToolbar;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.rv_shopping_cart)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String search;
    private HomeFyAdapter mHomeFyAdapter;


    @Override
    public int intiLayout() {
        return R.layout.activity_search_result_topic;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "搜索");
        search = getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH);
        tvTopTitle.setText(search);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);

        mHomeFyAdapter = new HomeFyAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setAdapter(mHomeFyAdapter);

//        mHomeFyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ShopDetailsActivity.startActivityIntent(
//                        SearchResultTopicActivity.this,mHomeFyAdapter.getData().get(position).getId());
//            }
//        });
        getAllTopic();
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.activity_search_goods_search_tv, R.id.tv_top_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_search_goods_search_tv:
            case R.id.tv_top_title:
                finish();
                break;
        }
    }

    private void getAllTopic(){
        RetrofitUtil.getInstance().apiService()
                .HouseList(1,10,null,null,null,null,null,search)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<HouseListBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<HouseListBase> result) {
                        if (isResultOk(result)) {
                            if(result.getData()!=null &&result.getData().getList()!=null
                                    && result.getData().getList().size()>0){
                                llNoData.setVisibility(View.GONE);
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                mHomeFyAdapter.setNewData(result.getData().getList());
                            }
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
