package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;

import java.util.List;

public class ZuFangFaBuXxTextAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    int curPos = 0;
    public ZuFangFaBuXxTextAdapter(List<String> data) {
        super(R.layout.item_zu_fang_fa_bu_xx_text, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content,item)
                .setTextColor(R.id.tv_content,mContext.getResources().getColor(R.color.color_666666))
                .setBackgroundRes(R.id.tv_content,R.drawable.circle_line_666_20);
        if(helper.getLayoutPosition()==curPos){
            helper.setTextColor(R.id.tv_content,mContext.getResources().getColor(R.color.white))
                    .setBackgroundRes(R.id.tv_content,R.drawable.circle_solid_theme_25);
        }
    }

    public void setCurPos(int curPos) {
        this.curPos = curPos;
    }

    public int getCurPos() {
        return curPos;
    }
}
