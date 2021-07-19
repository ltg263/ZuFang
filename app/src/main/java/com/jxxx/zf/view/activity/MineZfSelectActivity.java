package com.jxxx.zf.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.api.RetrofitUtil;
import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.bean.MineZfBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.utils.view.RangeSeekBar;
import com.jxxx.zf.view.adapter.HomeMineZfAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineZfSelectActivity extends BaseActivity {
    @BindView(R.id.mRangeSeekBar)
    RangeSeekBar mRangeSeekBar;
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.mRecyclerView1)
    RecyclerView mMRecyclerView1;
    @BindView(R.id.mRecyclerView2)
    RecyclerView mMRecyclerView2;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.mRecyclerView3)
    RecyclerView mMRecyclerView3;
    String rentBegin = "100";
    String rentEnd = "5000";
    String rentingType,houseType,lables;
    @Override
    public int intiLayout() {
        return R.layout.activity_mine_zf_select;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "我的租房");
        mRangeSeekBar.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(float lowerRange, float upperRange) {
                rentBegin = lowerRange+"";
                rentEnd = upperRange+"";
                Log.w("onRangeChanged", "" + (int) lowerRange + "~" + (int) upperRange);
            }
        });
    }

    @Override
    public void initData() {
        ArrayList<MineZfBean> lists_hx = new ArrayList<>();
        lists_hx.add(new MineZfBean("不限",false));
        lists_hx.add(new MineZfBean("合租",false));
        lists_hx.add(new MineZfBean("整租",false));
        HomeMineZfAdapter mHomeMineZfAdapter_hx = new HomeMineZfAdapter(lists_hx);
        mMRecyclerView1.setAdapter(mHomeMineZfAdapter_hx);
        mHomeMineZfAdapter_hx.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for(int i = 0;i< mHomeMineZfAdapter_hx.getData().size(); i++){
                    mHomeMineZfAdapter_hx.getData().get(i).setSelect(false);
                }

                if(position ==0){
                    rentingType = null;
                }
                if(position ==1){
                    rentingType = "1";
                }
                if(position ==2){
                    rentingType = "2";
                }

                Log.w("-->>","mIntent"+rentingType);
                mHomeMineZfAdapter_hx.getData().get(position).setSelect(true);
                mHomeMineZfAdapter_hx.notifyDataSetChanged();
            }
        });

        ArrayList<MineZfBean> lists_js = new ArrayList<>();
        for(int i=0;i<ConstValues.HOUSE_TYPE.length;i++){
            lists_js.add(new MineZfBean(ConstValues.HOUSE_TYPE[i],false));
        }
        HomeMineZfAdapter mHomeMineZfAdapter_js = new HomeMineZfAdapter(lists_js);
        mMRecyclerView2.setAdapter(mHomeMineZfAdapter_js);
        mHomeMineZfAdapter_js.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for(int i = 0;i< mHomeMineZfAdapter_js.getData().size(); i++){
                    mHomeMineZfAdapter_js.getData().get(i).setSelect(false);
                }
                houseType = (position+1)+"";
                mHomeMineZfAdapter_js.getData().get(position).setSelect(true);
                mHomeMineZfAdapter_js.notifyDataSetChanged();
            }
        });
        getHouseLable();
    }

    private void getHouseLable() {
        RetrofitUtil.getInstance().apiService()
                .getHouseLable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<ZuFangDetailsBase.LablesBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<ZuFangDetailsBase.LablesBean>> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null) {
                            ArrayList<MineZfBean> lists_qt = new ArrayList<>();
                            for(int i=0;i<result.getData().size();i++){
                                lists_qt.add(new MineZfBean(result.getData().get(i).getName(),false));
                            }
                            HomeMineZfAdapter mHomeMineZfAdapter_qt = new HomeMineZfAdapter(lists_qt);
                            mMRecyclerView3.setAdapter(mHomeMineZfAdapter_qt);
                            mHomeMineZfAdapter_qt.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    for(int i = 0;i< mHomeMineZfAdapter_qt.getData().size(); i++){
                                        mHomeMineZfAdapter_qt.getData().get(i).setSelect(false);
                                    }
                                    lables = mHomeMineZfAdapter_qt.getData().get(position).getStr();
                                    mHomeMineZfAdapter_qt.getData().get(position).setSelect(true);
                                    mHomeMineZfAdapter_qt.notifyDataSetChanged();
                                }
                            });
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

    @OnClick({R.id.tv_address, R.id.iv_delete, R.id.tv_bnt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_address:
                break;
            case R.id.iv_delete:
                mTvAddress.setText("");
                break;
            case R.id.tv_bnt:

                Intent mIntent = new Intent();
                mIntent.putExtra("type","1");
                mIntent.putExtra("rentBegin",rentBegin);
                mIntent.putExtra("rentEnd",rentEnd);
                mIntent.putExtra("rentingType",rentingType);
                mIntent.putExtra("houseType",houseType);
//                mIntent.putExtra("address",address);
                mIntent.putExtra("lables",lables);
                if(StringUtil.isNotBlank(getIntent().getStringExtra("type"))
                        && getIntent().getStringExtra("type").equals("0")){
                    setResult(0,mIntent);
                    finish();
                    return;
                }
                mIntent.setClass(this,ZuFangListActivity.class);
                startActivity(mIntent);
                break;
        }
    }

}
