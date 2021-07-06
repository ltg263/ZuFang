package com.jxxx.zf.view.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.HomeZuFangListBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.view.activity.ZuFangXqActivity;

import java.util.List;

public class HomeFyAdapter extends BaseQuickAdapter<HomeZuFangListBase.HousesBean, BaseViewHolder> {

    public HomeFyAdapter(List<HomeZuFangListBase.HousesBean> data) {
        super(R.layout.item_home_fy_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeZuFangListBase.HousesBean item) {
        GlideImageLoader.loadImageViewRadius(mContext,item.getImgUrl(),helper.getView(R.id.head_icon));
        helper.setText(R.id.name_type,item.getName()).setText(R.id.year,item.getAddress());
        helper.getView(R.id.rl_zfxq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, ZuFangXqActivity.class);
                mContext.startActivity(mIntent);
            }
        });
    }

}
