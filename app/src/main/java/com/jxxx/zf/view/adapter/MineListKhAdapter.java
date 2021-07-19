package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.MyCustomersBean;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.view.activity.MineSetSmrz2Activity;

import java.util.List;

public class MineListKhAdapter extends BaseQuickAdapter<MyCustomersBean.ListBean, BaseViewHolder> {

    public MineListKhAdapter(List<MyCustomersBean.ListBean> data) {
        super(R.layout.item_mine_kh, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCustomersBean.ListBean item) {
        GlideImageLoader.loadImageViewRadius(mContext,item.getPortraitUri(),15,helper.getView(R.id.head_icon));
        helper.setText(R.id.tv_fyName,item.getHouseName()).setText(R.id.tv_user_name,item.getNickname())
                .setText(R.id.tv_state,item.getMobile()).addOnClickListener(R.id.iv_dh);
    }

}
