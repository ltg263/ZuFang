package com.jxxx.zf.view.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.AdviserListBean;
import com.jxxx.zf.utils.GlideImageLoader;
import com.jxxx.zf.utils.view.RatingBar;

import java.util.List;

public class UserInfoListAdapter extends BaseQuickAdapter<AdviserListBean.ListBean, BaseViewHolder> {
    String id = "";
    public UserInfoListAdapter(List<AdviserListBean.ListBean> data, String id) {
        super(R.layout.item_user_info, data);
        this.id =id;
    }

    @Override
    protected void convert(BaseViewHolder helper, AdviserListBean.ListBean item) {
        GlideImageLoader.loadImageAndDefault(mContext,item.getImgUrl(),helper.getView(R.id.iv));
        helper.setText(R.id.tv_user_name,item.getRealName()).setText(R.id.tv_fs,item.getScore()+"分 | "+item.getServerNum()+"带看")
        .setText(R.id.tv_state,item.getNote());
        ImageView iv_select = helper.getView(R.id.iv_select);
        iv_select.setSelected(false);
        if(item.getId().equals(id)){
            iv_select.setSelected(true);
        }
        RatingBar mRatingbar = helper.getView(R.id.select_num);
        mRatingbar.setStar(Float.parseFloat(item.getScore()));
        mRatingbar.setClickable(false);
    }

}
