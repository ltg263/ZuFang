package com.jxxx.zf.view.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.view.activity.ZuFangXqActivity;

import java.util.List;

public class MineListFylrAdapter extends BaseQuickAdapter<ZuFangDetailsBase, BaseViewHolder> {
    public boolean isBianji = false;
    public MineListFylrAdapter(List<ZuFangDetailsBase> data) {
        super(R.layout.item_mine_fylr, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZuFangDetailsBase item) {
        helper.addOnClickListener(R.id.bnt_1).addOnClickListener(R.id.bnt_2).addOnClickListener(R.id.bnt_3)
                .setGone(R.id.bnt_1,true).setGone(R.id.bnt_2,true).setGone(R.id.bnt_3,true);
        helper.setGone(R.id.iv_select,false).setText(R.id.tv_htbh,"发布时间:"+item.getCreateTime());
        if(isBianji){
            helper.setGone(R.id.iv_select,true);
        }

        GlideImageLoader.loadImageViewRadius(mContext,item.getImgUrl(),helper.getView(R.id.head_icon));
        String statusStr = "";
        switch (item.getStatus()){//0审核中 1上架 2下架 3审核失败
            case "0":
                statusStr = "审核中";
                helper.setGone(R.id.bnt_3,false);
                break;
            case "1":
                statusStr = "上架";
                helper.setText(R.id.bnt_3,"下架");
                break;
            case "2":
                statusStr = "下架";
                helper.setText(R.id.bnt_3,"上架");
                break;
            case "3":
                statusStr = "审核失败";
                helper.setGone(R.id.bnt_3,false);
                break;
        }

        String rentingType = StringUtil.getRentingType(item.getRentingType());
        helper.setText(R.id.name_type,rentingType + item.getName())
                .setText(R.id.year,rentingType+item.getArea()+"m²·"+ StringUtil.getHouseOrientation(item.getOrientation())+"|"+item.getHousingEstateName())
                .setText(R.id.tv_llcs,"约看"+item.getViewNum()+"人").setText(R.id.tv_je,item.getRent()).setText(R.id.tv_qyzt,statusStr);
        helper.setVisible(R.id.tv_lables_1,false).setVisible(R.id.tv_lables_2,false).setGone(R.id.tv_hasVideo,false).setGone(R.id.hasVideo,false);
        if(item.getHasVideo().equals("1")){
            helper.setVisible(R.id.hasVideo,true).setVisible(R.id.tv_hasVideo,true);
        }
        if (item.getLables() != null) {
            for (int i = 0; i < item.getLables().size(); i++) {
                if (i == 0) {
                    helper.setVisible(R.id.tv_lables_1,true).setText(R.id.tv_lables_1,item.getLables().get(0).getName());
                }
                if (i == 1) {
                    helper.setVisible(R.id.tv_lables_2,true).setText(R.id.tv_lables_2,item.getLables().get(1).getName());
                }
            }
        }
        helper.getView(R.id.include_yyxx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, ZuFangXqActivity.class);
                mIntent.putExtra("id",item.getId());
                mContext.startActivity(mIntent);
            }
        });
    }

    public void setBianji(boolean bianji) {
        isBianji = bianji;
    }
}
