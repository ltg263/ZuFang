package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.utils.view.RatingBar;

import java.util.List;

public class UserInfoListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public UserInfoListAdapter(List<String> data) {
        super(R.layout.item_user_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RatingBar mRatingbar = helper.getView(R.id.select_num);
        mRatingbar.setStar(4);
        mRatingbar.setClickable(false);
    }

}
