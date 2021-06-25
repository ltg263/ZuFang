package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;

import java.util.List;

public class ZuFangDbAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ZuFangDbAdapter(List<String> data) {
        super(R.layout.item_zu_fang_db, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title,item);
    }
}
