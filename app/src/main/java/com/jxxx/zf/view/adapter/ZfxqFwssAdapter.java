package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.ZuFangDetailsBase;
import com.jxxx.zf.utils.GlideImageLoader;

import java.util.List;

public class ZfxqFwssAdapter extends BaseQuickAdapter<ZuFangDetailsBase.ParamsBean, BaseViewHolder> {

    public ZfxqFwssAdapter(List<ZuFangDetailsBase.ParamsBean> data) {
        super(R.layout.item_zfxq_fwss, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZuFangDetailsBase.ParamsBean item) {
        GlideImageLoader.loadImageAndDefault(mContext,item.getIconUrl(),helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_title,item.getParamName());
    }
}
