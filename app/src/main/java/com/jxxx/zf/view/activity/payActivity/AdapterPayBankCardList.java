package com.jxxx.zf.view.activity.payActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.zf.R;
import com.jxxx.zf.bean.BankListBean;
import com.jxxx.zf.bean.BankListUserBean;

import java.util.List;

public class AdapterPayBankCardList extends BaseQuickAdapter<BankListUserBean, BaseViewHolder> {

    public AdapterPayBankCardList(List<BankListUserBean> data) {
        super(R.layout.item_bank_card, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankListUserBean item) {
        helper.setText(R.id.bankName, item.getBankName())
                .setText(R.id.cardNo, item.getBankNo());
        helper.addOnClickListener(R.id.iv_delete).addOnClickListener(R.id.iv_mr);
        helper.setImageResource(R.id.iv_mr, R.mipmap.ic_circle_no);
        if (item.getStatus().equals("1") ) {
            helper.setImageResource(R.id.iv_mr, R.mipmap.ic_circle_yes_l);
        }

    }

}
