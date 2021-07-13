package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.HouseParamListBean;

import java.util.List;

public class ZuFangFaBuXxTextSsAdapter extends BaseQuickAdapter<HouseParamListBean, BaseViewHolder> {

    int curPos = 0;
    public ZuFangFaBuXxTextSsAdapter(List<HouseParamListBean> data) {
        super(R.layout.item_zu_fang_fa_bu_xx_text, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseParamListBean item) {
        helper.setText(R.id.tv_content,item.getParamName())
                .setTextColor(R.id.tv_content,mContext.getResources().getColor(R.color.color_666666))
                .setBackgroundRes(R.id.tv_content,R.drawable.circle_line_666_20);
        if(helper.getLayoutPosition()==curPos){
            helper.setTextColor(R.id.tv_content,mContext.getResources().getColor(R.color.white))
                    .setBackgroundRes(R.id.tv_content,R.drawable.circle_solid_theme_25);
        }
    }
}
