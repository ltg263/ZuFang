package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;

import java.util.List;

public class ZfxqFwssAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ZfxqFwssAdapter(List<String> data) {
        super(R.layout.item_zfxq_fwss, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title,item);
    }
}
