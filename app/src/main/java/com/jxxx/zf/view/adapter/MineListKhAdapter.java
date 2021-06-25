package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;

import java.util.List;

public class MineListKhAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MineListKhAdapter(List<String> data) {
        super(R.layout.item_mine_kh, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.iv_dh);
    }

}
