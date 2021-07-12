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

public class MineListScAdapter extends BaseQuickAdapter<ZuFangDetailsBase, BaseViewHolder> {

    public MineListScAdapter(List<ZuFangDetailsBase> data) {
        super(R.layout.item_mine_sc, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZuFangDetailsBase item) {
        helper.addOnClickListener(R.id.bnt_qxsc).addOnClickListener(R.id.bnt_yykf);
        GlideImageLoader.loadImageViewRadius(mContext, item.getImgUrl(), helper.getView(R.id.head_icon));
        helper.setText(R.id.name_type, item.getRentingType().equals("1") ? "合租·" : "整租·" + item.getName())
                .setText(R.id.year, item.getRentingType().equals("1") ? "合租·" : "整租·" + item.getArea() + "m²·" + StringUtil.getHouseOrientation(item.getOrientation()) + "|" + item.getHousingEstateName())
                .setText(R.id.tv_llcs, "约看" + item.getViewNum() + "人").setText(R.id.tv_je, item.getRent());
        helper.setVisible(R.id.tv_lables_1, false).setVisible(R.id.tv_lables_2, false);
        if (item.getLables() != null) {
            for (int i = 0; i < item.getLables().size(); i++) {
                if (i == 0) {
                    helper.setVisible(R.id.tv_lables_1, true).setText(R.id.tv_lables_1, item.getLables().get(0).getName());
                }
                if (i == 1) {
                    helper.setVisible(R.id.tv_lables_2, true).setText(R.id.tv_lables_2, item.getLables().get(1).getName());
                }
            }
        }
        helper.getView(R.id.include_yyxx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, ZuFangXqActivity.class);
                mIntent.putExtra("id", item.getId());
                mContext.startActivity(mIntent);
            }
        });
    }

}
