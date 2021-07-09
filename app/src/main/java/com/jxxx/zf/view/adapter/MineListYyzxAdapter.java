package com.jxxx.zf.view.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.AppointmentList;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.view.activity.ZuFangXqActivity;

import java.util.List;


public class MineListYyzxAdapter extends BaseQuickAdapter<AppointmentList.ListBean, BaseViewHolder> {

    public MineListYyzxAdapter(List<AppointmentList.ListBean> data) {
        super(R.layout.item_mine_yyzx, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppointmentList.ListBean item) {
        helper.addOnClickListener(R.id.bnt_1).addOnClickListener(R.id.bnt_2).addOnClickListener(R.id.bnt_3).setGone(R.id.ll_not,false)
                .setVisible(R.id.bnt_1, false).setVisible(R.id.bnt_2, false).setVisible(R.id.bnt_3, false)
                .setText(R.id.tv_qyzt, item.getStausStr())
                .setText(R.id.tv_htbh, "预约时间：" + item.getAppointmentTime());
        //1 已预约 2 已接单 3已认证 4看房中 5已完成 6已签约 7已取消
        switch (item.getStatus()) {
            case "1":
            case "2":
            case "3":
                helper.setGone(R.id.ll_not,true).setVisible(R.id.bnt_1, true).setText(R.id.bnt_1, "取消预约")
                        .setVisible(R.id.bnt_2, true).setText(R.id.bnt_2, "更改预约")
                        .setVisible(R.id.bnt_3, true).setText(R.id.bnt_3, "电话联系");
                break;
            case "5":
            case "6":
                helper.setGone(R.id.ll_not,true).setVisible(R.id.bnt_1, true).setText(R.id.bnt_1, "评价")
                        .setVisible(R.id.bnt_3, true).setText(R.id.bnt_3, "电话联系");
                break;
//            case "4":
//            case "7":
//                helper.setVisible(R.id.bnt_3, true).setText(R.id.bnt_3, "电话联系");
//                break;
        }
        ZuFangDetailsBase house = item.getHouse();
        if (house != null) {
            String orientationStr = "";
            switch (house.getOrientation()) {
                case "1":
                    orientationStr = "朝东";
                    break;
                case "2":
                    orientationStr = "朝南";
                    break;
                case "3":
                    orientationStr = "朝西";
                    break;
                case "4":
                    orientationStr = "朝北";
                    break;
                case "5":
                    orientationStr = "朝东南";
                    break;
                case "6":
                    orientationStr = "朝西南";
                    break;
                case "7":
                    orientationStr = "朝东北";
                    break;
                case "8":
                    orientationStr = "朝西北";
                    break;
            }
            GlideImageLoader.loadImageViewRadius(mContext, house.getImgUrl(), helper.getView(R.id.head_icon));
            helper.setText(R.id.name_type, house.getRentingType().equals("1") ? "合租·" : "合租·" + house.getName())
                    .setText(R.id.year, house.getRentingType().equals("1") ? "合租·" : "合租·" + house.getArea() + "m²·" + orientationStr + "|" + house.getHousingEstateName())
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
