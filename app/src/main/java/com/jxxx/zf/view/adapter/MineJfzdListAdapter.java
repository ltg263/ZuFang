package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.ContractBillBean;

import java.util.List;

public class MineJfzdListAdapter extends BaseQuickAdapter<ContractBillBean.BillsBean, BaseViewHolder> {

    public MineJfzdListAdapter(List<ContractBillBean.BillsBean> data) {
        super(R.layout.item_mine_jfdd, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContractBillBean.BillsBean item) {
        String tv_zt = "已支付";
        if(item.getStatus().equals("0")){
            tv_zt = "请即使支付";
            if(item.isOverdue()){
            }
        }
        helper.setText(R.id.tv_num,helper.getLayoutPosition()+"")
                .setText(R.id.tv_je,item.getAmount()).setText(R.id.tv_zt,tv_zt);
        if(helper.getLayoutPosition()==1){
            helper.setBackgroundRes(R.id.tv_num,R.drawable.circle_solid_theme_25)
                    .setTextColor(R.id.tv_num,mContext.getResources().getColor(R.color.white))
                    .setTextColor(R.id.tv_je,mContext.getResources().getColor(R.color.color_333333))
                    .setTextColor(R.id.tv_zt,mContext.getResources().getColor(R.color.red40))
                    .setTextColor(R.id.tv_time,mContext.getResources().getColor(R.color.color_333333));
        }
    }

}
