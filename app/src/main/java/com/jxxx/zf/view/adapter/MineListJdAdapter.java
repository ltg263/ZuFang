package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.StringUtil;

import java.util.List;

public class MineListJdAdapter extends BaseQuickAdapter<AppointmentDetailsBase, BaseViewHolder> {

    public MineListJdAdapter(List<AppointmentDetailsBase> data) {
        super(R.layout.item_mine_jd, data);
    }
//    var actions = [String].init()
//        if loginUserManager.adviser {
//        switch appointmentModel.status {
//            case 3:
//                actions = ["联系对方", "去看房"]
//            case 4:
//                actions = ["不签约", "线下签约", "线上签约"]
//            default:
//                actions = ["联系对方", "去认证"]
//        }
//    } else {
//        switch appointmentModel.status {
//            case 1:
//                if appointmentModel.hasAdviser == 0 {
//                actions = ["取消预约"]
//            }
//            case 6:
//                actions = ["联系对方", "去评价"]
//            default:
//                actions = ["联系对方", "取消预约"]
//        }
//    }
    @Override
    protected void convert(BaseViewHolder helper, AppointmentDetailsBase item) {
        helper.addOnClickListener(R.id.bnt_lx).addOnClickListener(R.id.bnt_jd)
                .setVisible(R.id.bnt_lx, false).setVisible(R.id.bnt_jd, false)
                .setText(R.id.tv_qyzt, item.getStausStr())
                .setText(R.id.tv_htbh, "预约时间：" + item.getAppointmentTime());
        //1 已预约 2 已接单 3已认证 4看房中 5未签约 6已签约 7待签约 8完成 9取消
        switch (item.getStatus()) {
            case "1":
                helper.setVisible(R.id.bnt_lx, true).setText(R.id.bnt_lx, "联系对方")
                        .setVisible(R.id.bnt_jd, true).setText(R.id.bnt_jd, "接单");
                break;
            case "2":
                helper.setVisible(R.id.bnt_lx, true).setText(R.id.bnt_lx, "联系对方")
                        .setVisible(R.id.bnt_jd, true).setText(R.id.bnt_jd, "去认证");
                break;
            case "3":
                helper.setVisible(R.id.bnt_lx, true).setText(R.id.bnt_lx, "联系对方")
                        .setVisible(R.id.bnt_jd, true).setText(R.id.bnt_jd, "去看房");
                break;
            case "4":
                helper.setVisible(R.id.bnt_lx, true).setText(R.id.bnt_lx, "联系对方")
                        .setVisible(R.id.bnt_jd, true).setText(R.id.bnt_jd, "线上签约");
//                        .setVisible(R.id.bnt_jd, true).setText(R.id.bnt_jd, "线下签约");
                break;
            case "5":
            case "6":
                helper.setVisible(R.id.bnt_lx, true).setText(R.id.bnt_lx, "联系对方")
                        .setVisible(R.id.bnt_jd, true).setText(R.id.bnt_jd, "待评价");
                break;
            case "7":
                helper.setVisible(R.id.bnt_lx, true).setText(R.id.bnt_lx, "联系对方")
                        .setVisible(R.id.bnt_jd, true).setText(R.id.bnt_jd, "待签约");
                break;
//            case "7":
//                helper.setVisible(R.id.bnt_3, true).setText(R.id.bnt_3, "电话联系");
//                break;
        }
        ZuFangDetailsBase house = item.getHouse();
        if (house != null) {
            GlideImageLoader.loadImageViewRadius(mContext, house.getImgUrl(), helper.getView(R.id.head_icon));
            String rentingType = StringUtil.getRentingType(house.getRentingType());
            helper.setText(R.id.name_type, rentingType + house.getName())
                    .setText(R.id.year, rentingType + house.getArea() + "m²·" + StringUtil.getHouseOrientation(house.getOrientation()) + "|" + house.getHousingEstateName())
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
