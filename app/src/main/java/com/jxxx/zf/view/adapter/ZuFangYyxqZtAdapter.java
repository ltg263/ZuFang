package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.HouseCompareBean;

import java.util.List;

public class ZuFangYyxqZtAdapter extends BaseQuickAdapter<AppointmentDetailsBase.StatusBeanList, BaseViewHolder> {

    public ZuFangYyxqZtAdapter(List<AppointmentDetailsBase.StatusBeanList> data) {
        super(R.layout.item_zu_fang_yyxq_zt, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppointmentDetailsBase.StatusBeanList item) {
        helper.setText(R.id.tv_time,item.getCreateTime()).setVisible(R.id.view1,true).setVisible(R.id.view2,true)
                .setText(R.id.tv_title,item.getStatusStr()).setText(R.id.tv_remark,"备注："+item.getRemark());
        if(helper.getLayoutPosition()==0){
            helper.setVisible(R.id.view1,false);
        }else if(item.getAppointmetStatus().equals("6")){
            helper.setVisible(R.id.view2,false);
        }

    }
}

