package com.jxxx.zf.bean;

import java.util.List;

public class ContractBillBean {

    /**
     * bills : [{"amount":0,"contractId":0,"expireTime":"","id":0,"leftDays":0,"overdue":true,"overdueDays":0,"payTime":"","remark":"","status":0}]
     * deposit : 0
     * frontMoney : 0
     * letfAmount : 0
     * rentAmount : 0
     * rentType : 0
     * totalAmount : 0
     * withAmount : 0
     */

    private String deposit;
    private String frontMoney;
    private String letfAmount;
    private String rentAmount;
    private String rentType;
    private String totalAmount;
    private String withAmount;
    private List<BillsBean> bills;

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getFrontMoney() {
        return frontMoney;
    }

    public void setFrontMoney(String frontMoney) {
        this.frontMoney = frontMoney;
    }

    public String getLetfAmount() {
        return letfAmount;
    }

    public void setLetfAmount(String letfAmount) {
        this.letfAmount = letfAmount;
    }

    public String getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(String rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getWithAmount() {
        return withAmount;
    }

    public void setWithAmount(String withAmount) {
        this.withAmount = withAmount;
    }

    public List<BillsBean> getBills() {
        return bills;
    }

    public void setBills(List<BillsBean> bills) {
        this.bills = bills;
    }

    public static class BillsBean {
        /**
         * amount : 0
         * contractId : 0
         * expireTime : 
         * id : 0
         * leftDays : 0
         * overdue : true
         * overdueDays : 0
         * payTime : 
         * remark : 
         * status : 0
         */

        private String amount;
        private String contractId;
        private String expireTime;
        private String beginDate="";
        private String id;
        private String leftDays;
        private boolean overdue;
        private String overdueDays;
        private String payTime;
        private String remark;
        private String status;

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getContractId() {
            return contractId;
        }

        public void setContractId(String contractId) {
            this.contractId = contractId;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLeftDays() {
            return leftDays;
        }

        public void setLeftDays(String leftDays) {
            this.leftDays = leftDays;
        }

        public boolean isOverdue() {
            return overdue;
        }

        public void setOverdue(boolean overdue) {
            this.overdue = overdue;
        }

        public String getOverdueDays() {
            return overdueDays;
        }

        public void setOverdueDays(String overdueDays) {
            this.overdueDays = overdueDays;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
