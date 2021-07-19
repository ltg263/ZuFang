package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.ZuFangDetailsBase;

import java.util.List;

public class ZuFangFaBuXxTextLablesAdapter extends BaseQuickAdapter<ZuFangDetailsBase.LablesBean, BaseViewHolder> {

    List<ZuFangDetailsBase.LablesBean> houseParams;
    public ZuFangFaBuXxTextLablesAdapter(List<ZuFangDetailsBase.LablesBean> data, List<ZuFangDetailsBase.LablesBean> houseParams) {
        super(R.layout.item_zu_fang_fa_bu_xx_text, data);
        this.houseParams = houseParams;
    }

    @Override
    protected void convert(BaseViewHolder helper, ZuFangDetailsBase.LablesBean item) {
        helper.setText(R.id.tv_content,item.getName())
                .setTextColor(R.id.tv_content,mContext.getResources().getColor(R.color.color_666666))
                .setBackgroundRes(R.id.tv_content,R.drawable.circle_line_666_20);
        if(houseParams.contains(item)){
            helper.setTextColor(R.id.tv_content,mContext.getResources().getColor(R.color.white))
                    .setBackgroundRes(R.id.tv_content,R.drawable.circle_solid_theme_25);
        }
    }
}
