package com.jxxx.zf.view.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.MineZfBean;

import java.util.List;

public class HomeMineZfAdapter extends BaseQuickAdapter<MineZfBean, BaseViewHolder> {

    public HomeMineZfAdapter(List<MineZfBean> data) {
        super(R.layout.item_home_zf_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineZfBean item) {
        TextView tvCont = helper.getView(R.id.tv_cont);
        tvCont.setText(item.getStr());
        if(item.isSelect()){
            tvCont.setTextColor(mContext.getResources().getColor(R.color.white));
            tvCont.setBackground(mContext.getResources().getDrawable(R.drawable.circle_solid_theme_5));
        }else{
            tvCont.setTextColor(mContext.getResources().getColor(R.color.color_666666));
            tvCont.setBackground(mContext.getResources().getDrawable(R.drawable.circle_solid_f4f4f4_5));
        }

        tvCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i= 0;i<mData.size();i++){
                    mData.get(i).setSelect(false);
                }
                item.setSelect(true);
                notifyDataSetChanged();
            }
        });
    }

}
