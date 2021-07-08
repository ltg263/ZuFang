package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;

import java.util.List;

public class ZuFangDbAdapterFzDz extends BaseQuickAdapter<String, BaseViewHolder> {

    public ZuFangDbAdapterFzDz(List<String> data) {
        super(R.layout.item_zu_fang_db_dz, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title, item)
                .setGone(R.id.iv_select,false)
                .setGone(R.id.iv_select1,true)
                .addOnClickListener(R.id.iv_select1);
        if(item.equals("隐藏相同")){
            helper.setGone(R.id.iv_select,true)
                    .setGone(R.id.iv_select1,false)
                    .addOnClickListener(R.id.iv_select);
        }
    }
}
