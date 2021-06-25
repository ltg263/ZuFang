
package com.jxxx.zf.view.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseActivity;
import com.jxxx.zf.utils.IntentUtils;
import com.jxxx.zf.view.adapter.MineListKhAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineKhListActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private MineListKhAdapter mMineListKhAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_refresh_list;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "我的客户");
    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mMineListKhAdapter = new MineListKhAdapter(list);
        mRvList.setAdapter(mMineListKhAdapter);

        mMineListKhAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                baseStartActivity(ZuFangYyxqActivity.class,null);
            }
        });
        mMineListKhAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_dh:
                        IntentUtils.startActivityPhone(MineKhListActivity.this,"17774004352");
                        break;

                }
            }
        });
    }
}
