package com.jxxx.zf.view.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.HouseCompareBean;

import java.util.List;

public class ZuFangDbAdapter extends BaseQuickAdapter<HouseCompareBean, BaseViewHolder> {

    public ZuFangDbAdapter(List<HouseCompareBean> data) {
        super(R.layout.item_zu_fang_db, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseCompareBean item) {
        helper.setText(R.id.tv_title,item.getName());
        RecyclerView mRvList = helper.getView(R.id.rv_list);
        mRvList.setAdapter(new ZuFangDbAdapterCon(item.getValues()));
    }

    class ZuFangDbAdapterCon extends BaseQuickAdapter<String, BaseViewHolder> {

        public ZuFangDbAdapterCon(List<String> data) {
            super(R.layout.item_zu_fang_db, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            if(item.equals("true") || item.equals("1")){
                helper.setText(R.id.tv_title,"●").setGone(R.id.rv_list,false);
            }else if(item.equals("false") || item.equals("0")){
                helper.setText(R.id.tv_title,"-").setGone(R.id.rv_list,false);
            }else {
                helper.setText(R.id.tv_title, item).setGone(R.id.rv_list, false);
            }

        }
    }
}

