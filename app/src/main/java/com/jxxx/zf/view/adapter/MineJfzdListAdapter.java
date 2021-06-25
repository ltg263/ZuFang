package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;

import java.util.List;

public class MineJfzdListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MineJfzdListAdapter(List<String> data) {
        super(R.layout.item_mine_jfdd, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_num,helper.getLayoutPosition()+"");
        if(helper.getLayoutPosition()==1){
            helper.setBackgroundRes(R.id.tv_num,R.drawable.circle_solid_theme_25)
                    .setTextColor(R.id.tv_num,mContext.getResources().getColor(R.color.white))
                    .setTextColor(R.id.tv_je,mContext.getResources().getColor(R.color.color_333333))
                    .setTextColor(R.id.tv_zt,mContext.getResources().getColor(R.color.red40))
                    .setTextColor(R.id.tv_time,mContext.getResources().getColor(R.color.color_333333));
        }
    }

}
