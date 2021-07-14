package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.UserContractBean;

import java.util.List;

public class MineListHtAdapter extends BaseQuickAdapter<UserContractBean.ListBean, BaseViewHolder> {

    public MineListHtAdapter(List<UserContractBean.ListBean> data) {
        super(R.layout.item_mine_ht, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserContractBean.ListBean item) {
        helper.addOnClickListener(R.id.bnt_jy).addOnClickListener(R.id.bnt_xy).addOnClickListener(R.id.bnt_zd)
        .setText(R.id.tv_htbh,item.getContractNo()).setText(R.id.tv_qyzt,item.getStatus())
                .setText(R.id.tv_title,"名称").setText(R.id.tv_llcs,item.getStartTime()+"-"+item.getEndTime());
    }

}
