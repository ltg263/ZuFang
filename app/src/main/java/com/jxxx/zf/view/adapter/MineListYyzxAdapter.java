package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;

import java.util.List;

public class MineListYyzxAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MineListYyzxAdapter(List<String> data) {
        super(R.layout.item_mine_yyzx, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.bnt_1).addOnClickListener(R.id.bnt_2).addOnClickListener(R.id.bnt_3);
        helper.setText(R.id.bnt_1,"取消预约");
        if(helper.getLayoutPosition()==1){
            helper.setText(R.id.bnt_1,"评价");
        }
    }

}
