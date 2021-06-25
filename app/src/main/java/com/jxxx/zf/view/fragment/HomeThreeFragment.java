package com.jxxx.zf.view.fragment;


import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.zf.R;
import com.jxxx.zf.base.BaseFragment;
import com.jxxx.zf.view.activity.ChatActivity;
import com.jxxx.zf.view.activity.ZuFangListActivity;
import com.jxxx.zf.view.activity.mapAddress.ActivitySearchLocation;
import com.jxxx.zf.view.adapter.HomeMsgAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class HomeThreeFragment extends BaseFragment {


    @BindView(R.id.rv_list_msg)
    RecyclerView mRvListMsg;
    private HomeMsgAdapter mHomeMsgAdapter;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_three;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        ArrayList<String> bannerImg = new ArrayList<>();
        bannerImg.add("https://static.howbuy.com/upload/smhyupload/HY-30218028.jpg");
        bannerImg.add("https://static.howbuy.com/upload/smhyupload/HY-30218028.jpg");
        bannerImg.add("https://static.howbuy.com/upload/smhyupload/HY-30218028.jpg");
        bannerImg.add("https://static.howbuy.com/upload/smhyupload/HY-30218028.jpg");
        mHomeMsgAdapter = new HomeMsgAdapter(bannerImg);
        mRvListMsg.setAdapter(mHomeMsgAdapter);

        mHomeMsgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                baseStartActivity(ChatActivity.class,null);
            }
        });
    }


    @OnClick({R.id.ll_top_1, R.id.ll_top_2, R.id.ll_top_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_top_1:
                baseStartActivity(ZuFangListActivity.class,null);
                break;
            case R.id.ll_top_2:
                ActivityUtils.startActivityForResult(getActivity(), ActivitySearchLocation.class, 3);
                break;
            case R.id.ll_top_3:
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
        } else if (resultCode == 1) {
//            address.setText(data.getStringExtra("address"));
//            latLng = data.getParcelableExtra("lat");
        }

    }
}



