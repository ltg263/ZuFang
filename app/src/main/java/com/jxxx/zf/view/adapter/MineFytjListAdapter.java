package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;

import java.util.List;

public class MineFytjListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MineFytjListAdapter(List<String> data) {
        super(R.layout.item_mine_fytj, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
    }

}
