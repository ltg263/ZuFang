package com.jxxx.zf.bean;

import java.util.List;

public class AccountBillBean {

    /**
     * balance : 0
     * count : 0
     * freezeAmount : 0
     * logs : [{"accountType":0,"accountTypeStr":"","amount":0,"associationId":0,"associationType":0,"associationTypeStr":"","avatar":"","createTime":"","id":0,"inOrOut":0,"inOrOutStr":"","nickName":"","oldBalance":0,"orderStatus":0,"remark":"","userAccountId":0,"userId":0}]
     * totalAmount : 0
     */

    private String balance;
    private String count;
    private String freezeAmount;
    private String totalAmount;
    private List<LogsBean> logs;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(String freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<LogsBean> getLogs() {
        return logs;
    }

    public void setLogs(List<LogsBean> logs) {
        this.logs = logs;
    }

    public static class LogsBean {
        /**
         * accountType : 0
         * accountTypeStr : 
         * amount : 0
         * associationId : 0
         * associationType : 0
         * associationTypeStr : 
         * avatar : 
         * createTime : 
         * id : 0
         * inOrOut : 0
         * inOrOutStr : 
         * nickName : 
         * oldBalance : 0
         * orderStatus : 0
         * remark : 
         * userAccountId : 0
         * userId : 0
         */

        private String accountType;
        private String accountTypeStr;
        private String amount;
        private String associationId;
        private String associationType;
        private String associationTypeStr;
        private String avatar;
        private String createTime;
        private String id;
        private String inOrOut;
        private String inOrOutStr;
        private String nickName;
        private String oldBalance;
        private String orderStatus;
        private String remark;
        private String userAccountId;
        private String userId;

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getAccountTypeStr() {
            return accountTypeStr;
        }

        public void setAccountTypeStr(String accountTypeStr) {
            this.accountTypeStr = accountTypeStr;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAssociationId() {
            return associationId;
        }

        public void setAssociationId(String associationId) {
            this.associationId = associationId;
        }

        public String getAssociationType() {
            return associationType;
        }

        public void setAssociationType(String associationType) {
            this.associationType = associationType;
        }

        public String getAssociationTypeStr() {
            return associationTypeStr;
        }

        public void setAssociationTypeStr(String associationTypeStr) {
            this.associationTypeStr = associationTypeStr;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInOrOut() {
            return inOrOut;
        }

        public void setInOrOut(String inOrOut) {
            this.inOrOut = inOrOut;
        }

        public String getInOrOutStr() {
            return inOrOutStr;
        }

        public void setInOrOutStr(String inOrOutStr) {
            this.inOrOutStr = inOrOutStr;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getOldBalance() {
            return oldBalance;
        }

        public void setOldBalance(String oldBalance) {
            this.oldBalance = oldBalance;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getUserAccountId() {
            return userAccountId;
        }

        public void setUserAccountId(String userAccountId) {
            this.userAccountId = userAccountId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
