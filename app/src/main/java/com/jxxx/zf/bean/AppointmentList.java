package com.jxxx.zf.bean;

import java.util.List;

public class AppointmentList {

    /**
     * count : 5
     * list : [{"adviserId":1,"appointmentTime":1625814600000,"createTime":1625728251000,"delTf":0,"gender":2,"hasAdviser":1,"houseId":2,"id":10,"mobile":"17633332222","realName":"tttt","status":1,"userId":10},{"appointmentTime":1625731260000,"createTime":1625727674000,"delTf":0,"gender":1,"hasAdviser":0,"houseId":1,"id":9,"mobile":"17222214444","realName":"he","status":1,"userId":10},{"appointmentTime":1625719260000,"createTime":1625711403000,"delTf":0,"gender":1,"hasAdviser":0,"houseId":1,"id":8,"mobile":"17622221111","realName":"yer","status":1,"userId":7},{"appointmentTime":1625730960000,"createTime":1625644565000,"delTf":0,"gender":1,"hasAdviser":0,"houseId":1,"id":7,"mobile":"17622123357","realName":"tw","status":1,"userId":10},{"adviserId":1,"appointmentTime":1625719260000,"createTime":1625637848000,"delTf":0,"gender":1,"hasAdviser":0,"houseId":1,"id":6,"mobile":"17622221111","realName":"yer","status":1,"userId":1}]
     */

    private String count;
    private List<ListBean> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * adviserId : 1
         * appointmentTime : 1625814600000
         * createTime : 1625728251000
         * delTf : 0
         * gender : 2
         * hasAdviser : 1
         * houseId : 2
         * id : 10
         * mobile : 17633332222
         * realName : tttt
         * status : 1
         * userId : 10
         */

        private String adviserId;
        private String appointmentTime;
        private String createTime;
        private String delTf;
        private String gender;
        private String hasAdviser;
        private String houseId;
        private String id;
        private String mobile;
        private String realName;
        private String status;
        private String userId;

        public String getAdviserId() {
            return adviserId;
        }

        public void setAdviserId(String adviserId) {
            this.adviserId = adviserId;
        }

        public String getAppointmentTime() {
            return appointmentTime;
        }

        public void setAppointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDelTf() {
            return delTf;
        }

        public void setDelTf(String delTf) {
            this.delTf = delTf;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHasAdviser() {
            return hasAdviser;
        }

        public void setHasAdviser(String hasAdviser) {
            this.hasAdviser = hasAdviser;
        }

        public String getHouseId() {
            return houseId;
        }

        public void setHouseId(String houseId) {
            this.houseId = houseId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
