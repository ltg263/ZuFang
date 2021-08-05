package com.jxxx.zf.bean;

import java.util.List;

public class BankListUserBeans {

    /**
     * count : 1
     * list : [{"bankId":4,"bankName":"中国银行","bankNo":"123466","createTime":"2021-08-05 10:45:01","delTf":0,"id":6,"mobile":"13112345645","status":1,"userId":16}]
     */

    private int count;
    private List<BankListUserBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<BankListUserBean> getList() {
        return list;
    }

    public void setList(List<BankListUserBean> list) {
        this.list = list;
    }

}

