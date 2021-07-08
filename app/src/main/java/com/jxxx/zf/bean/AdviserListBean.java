package com.jxxx.zf.bean;

import java.util.List;

public class AdviserListBean {

    /**
     * count : 1
     * list : [{"businessAreaId":1,"createTime":1625625305000,"delTf":0,"id":1,"imgUrl":"tu.jpg","mobile":"15958243735","note":"说明","realName":"AAA","score":4.6,"serverNum":33,"status":1,"userId":7}]
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
         * businessAreaId : 1
         * createTime : 1625625305000
         * delTf : 0
         * id : 1
         * imgUrl : tu.jpg
         * mobile : 15958243735
         * note : 说明
         * realName : AAA
         * score : 4.6
         * serverNum : 33
         * status : 1
         * userId : 7
         */

        private String adviserAuthentication;
        private String businessAreaId;
        private String createTime;
        private String delTf;
        private String id;
        private String imgUrl;
        private String mobile;
        private String note;
        private String realName;
        private String score;
        private String serverNum;
        private String status;
        private String userId;

        public void setAdviserAuthentication(String adviserAuthentication) {
            this.adviserAuthentication = adviserAuthentication;
        }

        public String getAdviserAuthentication() {
            return adviserAuthentication;
        }

        public String getBusinessAreaId() {
            return businessAreaId;
        }

        public void setBusinessAreaId(String businessAreaId) {
            this.businessAreaId = businessAreaId;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getServerNum() {
            return serverNum;
        }

        public void setServerNum(String serverNum) {
            this.serverNum = serverNum;
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
