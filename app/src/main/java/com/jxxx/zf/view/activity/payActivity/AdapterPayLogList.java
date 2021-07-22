package com.jxxx.zf.view.activity.payActivity;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.AccountBillBean;

import java.util.List;

public class AdapterPayLogList extends BaseQuickAdapter<AccountBillBean.LogsBean, BaseViewHolder> {
    public AdapterPayLogList(@Nullable List<AccountBillBean.LogsBean> data) {
        super(R.layout.item_cash_history, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountBillBean.LogsBean item) {
        helper.setText(R.id.tv_name, item.getAccountTypeStr())
                .setText(R.id.tv_remark, item.getRemark())
                .setText(R.id.tv_inOrOutStr, item.getInOrOutStr()+item.getAmount())
                .setText(R.id.tv_createTime,item.getCreateTime());
    }
}
