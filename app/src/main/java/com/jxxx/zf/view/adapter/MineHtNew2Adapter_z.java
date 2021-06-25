package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;

import java.util.List;

public class MineHtNew2Adapter_z extends BaseQuickAdapter<String, BaseViewHolder> {

    public MineHtNew2Adapter_z(List<String> data) {
        super(R.layout.item_mine_ht_new_2_z, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
       helper.setText(R.id.tv_title,item);
    }

}
