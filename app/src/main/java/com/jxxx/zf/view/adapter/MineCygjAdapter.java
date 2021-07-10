package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;

import java.util.List;

public class MineCygjAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MineCygjAdapter(List<String> data) {
        super(R.layout.item_zfxq_fwss, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title,item);
        switch (item){
            case "我的收藏":
                helper.setImageResource(R.id.iv_icon,R.drawable.group_1054);
                break;
            case "我的租客":
                helper.setImageResource(R.id.iv_icon,R.drawable.group_1045);
                break;
            case "实名认证":
                helper.setImageResource(R.id.iv_icon,R.drawable.group_1053);
                break;
            case "我的客户":
                helper.setImageResource(R.id.iv_icon,R.drawable.group_1044);
                break;
            case "我的接单":
                helper.setImageResource(R.id.iv_icon,R.drawable.group_1047);
                break;
            case "看房状态":
                helper.setImageResource(R.id.iv_icon,R.drawable.group_1043);
                break;
            case "房源上架":
                helper.setImageResource(R.id.iv_icon,R.drawable.group_1052);
                break;
            case "成为房东":
                helper.setImageResource(R.id.iv_icon,R.drawable.group_1048);
                break;
            case "成为顾问":
                helper.setImageResource(R.id.iv_icon,R.drawable.group_1051);
                break;
        }
    }
}
