package com.jxxx.zf.view.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.AppointmentList;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.view.activity.ZuFangXqActivity;

import java.util.List;


public class MineListYyzxAdapter extends BaseQuickAdapter<AppointmentDetailsBase, BaseViewHolder> {

    public MineListYyzxAdapter(List<AppointmentDetailsBase> data) {
        super(R.layout.item_mine_yyzx, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppointmentDetailsBase item) {
        helper.addOnClickListener(R.id.bnt_1).addOnClickListener(R.id.bnt_2).addOnClickListener(R.id.bnt_3).setGone(R.id.ll_not,false)
                .setVisible(R.id.bnt_1, false).setVisible(R.id.bnt_2, false).setVisible(R.id.bnt_3, false)
                .setText(R.id.tv_qyzt, item.getStausStr()).setGone(R.id.hasVideo,false).setGone(R.id.tv_hasVideo,false)
                .setText(R.id.tv_htbh, "预约时间：" + item.getAppointmentTime());
        if(item.getHouse().getHasVideo().equals("1")){
            helper.setVisible(R.id.hasVideo,true).setVisible(R.id.tv_hasVideo,true);
        }
        //1 已预约 2 已接单 3已认证 4看房中 5已完成 6已签约 7已取消
        switch (item.getStatus()) {
            case "1":
            case "2":
            case "3":
                helper.setGone(R.id.ll_not,true)
                        .setVisible(R.id.bnt_1, true).setText(R.id.bnt_1, "更改预约")
                        .setVisible(R.id.bnt_2, true).setText(R.id.bnt_2, "联系对方")
                        .setVisible(R.id.bnt_3, true).setText(R.id.bnt_3, "取消预约");
                break;
            case "4":
            case "5":
                helper.setGone(R.id.ll_not,true)
                        .setVisible(R.id.bnt_2, true).setText(R.id.bnt_2, "联系对方")
                        .setVisible(R.id.bnt_3, true).setText(R.id.bnt_3, "取消预约");
                break;
            case "6":
                helper.setGone(R.id.ll_not,true)
                        .setVisible(R.id.bnt_2, true).setText(R.id.bnt_2, "联系对方")
                        .setVisible(R.id.bnt_3, true).setText(R.id.bnt_3, "评价");
                break;
//            case "7":
//                helper.setVisible(R.id.bnt_3, true).setText(R.id.bnt_3, "电话联系");
//                break;
        }
        ZuFangDetailsBase house = item.getHouse();
        if (house != null) {
            GlideImageLoader.loadImageViewRadius(mContext, house.getImgUrl(), helper.getView(R.id.head_icon));
            helper.setText(R.id.name_type, house.getRentingType().equals("1") ? "合租·" : "整租·" + house.getName())
                    .setText(R.id.year, house.getRentingType().equals("1") ? "合租·" : "整租·" + house.getArea() + "m²·" + StringUtil.getHouseOrientation(house.getOrientation()) + "|" + house.getHousingEstateName())
                    .setText(R.id.tv_llcs, "约看" + house.getViewNum() + "人").setText(R.id.tv_je, house.getRent());
            helper.setVisible(R.id.tv_lables_1, false).setVisible(R.id.tv_lables_2, false);
            if (house.getLables() != null) {
                for (int i = 0; i < house.getLables().size(); i++) {
                    if (i == 0) {
                        helper.setVisible(R.id.tv_lables_1, true).setText(R.id.tv_lables_1, house.getLables().get(0).getName());
                    }
                    if (i == 1) {
                        helper.setVisible(R.id.tv_lables_2, true).setText(R.id.tv_lables_2, house.getLables().get(1).getName());
                    }
                }
            }
        }

    }

}
