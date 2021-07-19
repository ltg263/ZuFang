package com.jxxx.zf.view.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.StringUtil;
import com.jxxx.zf.view.activity.ZuFangXqActivity;

import java.util.List;

public class HomeFyAdapter extends BaseQuickAdapter<ZuFangDetailsBase, BaseViewHolder> {

    public HomeFyAdapter(List<ZuFangDetailsBase> data) {
        super(R.layout.item_home_fy_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZuFangDetailsBase item) {
        GlideImageLoader.loadImageViewRadius(mContext,item.getImgUrl(),helper.getView(R.id.head_icon));
        String rentingType = StringUtil.getRentingType(item.getRentingType());
        helper.setText(R.id.name_type,rentingType + item.getName())
                .setText(R.id.year,rentingType +item.getArea()+"m²·"+ StringUtil.getHouseOrientation(item.getOrientation())+"|"+item.getHousingEstateName())
        .setText(R.id.tv_llcs,"约看"+item.getViewNum()+"人").setText(R.id.tv_je,item.getRent());
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
        helper.getView(R.id.rl_zfxq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, ZuFangXqActivity.class);
                mIntent.putExtra("id",item.getId());
                mContext.startActivity(mIntent);
            }
        });
    }

}
