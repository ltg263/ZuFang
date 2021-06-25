package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;

import java.util.List;

public class MineListScAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MineListScAdapter(List<String> data) {
        super(R.layout.item_mine_sc, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.bnt_qxsc).addOnClickListener(R.id.bnt_yykf);
    }

}
