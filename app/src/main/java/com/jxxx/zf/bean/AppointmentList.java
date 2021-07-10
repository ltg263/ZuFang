package com.jxxx.zf.bean;

import java.util.List;

public class AppointmentList {

    /**
     * count : 5
     * list : [{"adviserId":1,"appointmentTime":1625814600000,"createTime":1625728251000,"delTf":0,"gender":2,"hasAdviser":1,"houseId":2,"id":10,"mobile":"17633332222","realName":"tttt","status":1,"userId":10},{"appointmentTime":1625731260000,"createTime":1625727674000,"delTf":0,"gender":1,"hasAdviser":0,"houseId":1,"id":9,"mobile":"17222214444","realName":"he","status":1,"userId":10},{"appointmentTime":1625719260000,"createTime":1625711403000,"delTf":0,"gender":1,"hasAdviser":0,"houseId":1,"id":8,"mobile":"17622221111","realName":"yer","status":1,"userId":7},{"appointmentTime":1625730960000,"createTime":1625644565000,"delTf":0,"gender":1,"hasAdviser":0,"houseId":1,"id":7,"mobile":"17622123357","realName":"tw","status":1,"userId":10},{"adviserId":1,"appointmentTime":1625719260000,"createTime":1625637848000,"delTf":0,"gender":1,"hasAdviser":0,"houseId":1,"id":6,"mobile":"17622221111","realName":"yer","status":1,"userId":1}]
     */

    private int count;
    private List<AppointmentDetailsBase> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AppointmentDetailsBase> getList() {
        return list;
    }

    public void setList(List<AppointmentDetailsBase> list) {
        this.list = list;
    }
}
