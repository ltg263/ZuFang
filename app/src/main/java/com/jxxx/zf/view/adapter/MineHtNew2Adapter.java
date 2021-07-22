package com.jxxx.zf.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.UserContractBean;

import java.util.List;

public class MineHtNew2Adapter extends BaseQuickAdapter<UserContractBean.ListBean.ItemsBean, BaseViewHolder> {

    public MineHtNew2Adapter(List<UserContractBean.ListBean.ItemsBean> data) {
        super(R.layout.item_mine_ht_new_2, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserContractBean.ListBean.ItemsBean item) {
       helper.setText(R.id.tv_title,item.getParamName()).setText(R.id.tv_num,"×"+item.getNum());
//       RecyclerView mRvList = helper.getView(R.id.rv_list);
//        List<String> list = new ArrayList<>();
//        list.add("家电1");
//        list.add("家具2");
//        list.add("家电3");
//        list.add("家具4");
//        list.add("家电5");
//        list.add("家具6");
//        list.add("家电7");
//        list.add("家具8");
//        list.add("家电9");
//        mRvList.setAdapter(new MineHtNew2Adapter_z(list));
    }

}
