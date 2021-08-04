package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.UserContractBean;
import com.jxxx.zf.utils.GlideImageLoader;

import java.util.List;

public class MineListHtAdapter extends BaseQuickAdapter<UserContractBean.ListBean, BaseViewHolder> {

    public MineListHtAdapter(List<UserContractBean.ListBean> data) {
        super(R.layout.item_mine_ht, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserContractBean.ListBean item) {
        GlideImageLoader.loadImageViewRadius(mContext,item.getHouseImgUrl(),helper.getView(R.id.head_icon));
        helper.setGone(R.id.bnt_jy,false).setGone(R.id.bnt_xy,false).setGone(R.id.bnt_dzht,false)
                .addOnClickListener(R.id.bnt_jy).addOnClickListener(R.id.bnt_xy).addOnClickListener(R.id.bnt_zd)
                .addOnClickListener(R.id.bnt_dzht)
        .setText(R.id.tv_htbh,"合同编号："+item.getContractNo()).setText(R.id.tv_qyzt,item.getStatusStr())
                .setText(R.id.tv_title,item.getHouseName()).setText(R.id.year,item.getHouseAttribute())
                .setText(R.id.tv_llcs,item.getStartTime().replace(" 00:00:00","")+"-"+item.getEndTime().replace(" 00:00:00",""));

        switch (item.getStatus()){
            case "0":
                helper.setVisible(R.id.bnt_dzht,true).setVisible(R.id.bnt_xy,true).setText(R.id.bnt_xy,"签约");
                break;
            case "1":
                helper.setVisible(R.id.bnt_dzht,true).setVisible(R.id.bnt_jy,true).setVisible(R.id.bnt_xy,true).setText(R.id.bnt_jy,"解约").setText(R.id.bnt_xy,"续约");
                break;
            case "2":
            case "3":
                helper.setVisible(R.id.bnt_dzht,true);
                break;
        }
    }

}
