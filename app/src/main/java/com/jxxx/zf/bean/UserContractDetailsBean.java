package com.jxxx.zf.bean;

import java.util.List;

public class UserContractDetailsBean {

    /**
     * adviserId : 5
     * bills : [{"amount":1800,"contractId":11,"expireTime":"2021-07-22 17:14:33","id":63,"overdue":false,"overdueDays":0,"status":0},{"amount":1800,"contractId":11,"expireTime":"2021-08-22 17:14:33","id":64,"leftDays":30,"overdue":false,"overdueDays":-30,"status":0},{"amount":1800,"contractId":11,"expireTime":"2021-09-22 17:14:33","id":65,"leftDays":61,"overdue":false,"overdueDays":-61,"status":0},{"amount":1800,"contractId":11,"expireTime":"2021-10-22 17:14:33","id":66,"leftDays":91,"overdue":false,"overdueDays":-91,"status":0},{"amount":1800,"contractId":11,"expireTime":"2021-11-22 17:14:33","id":67,"leftDays":122,"overdue":false,"overdueDays":-122,"status":0},{"amount":1800,"contractId":11,"expireTime":"2021-12-22 17:14:33","id":68,"leftDays":152,"overdue":false,"overdueDays":-152,"status":0},{"amount":1800,"contractId":11,"expireTime":"2022-01-22 17:14:33","id":69,"leftDays":183,"overdue":false,"overdueDays":-183,"status":0},{"amount":1800,"contractId":11,"expireTime":"2022-02-22 17:14:33","id":70,"leftDays":214,"overdue":false,"overdueDays":-214,"status":0},{"amount":1800,"contractId":11,"expireTime":"2022-03-22 17:14:33","id":71,"leftDays":242,"overdue":false,"overdueDays":-242,"status":0}]
     * certificateNumber : 130525199909092929
     * certificatePhoto : jztp.jpn
     * certificateType : 1
     * contractNo : 210722171433001051
     * createTime : 2021-07-22 17:14:33
     * delTf : 0
     * deposit : 1800
     * emergencyPhone : 13013331222
     * emergencyRelationship : 李小龙
     * endTime : 2022-04-22 00:00:00
     * houseAddress : BBBBBB
     * houseAttribute : 合租・50.50m²・朝东|A小区
     * houseId : 4
     * houseImgUrl : tu.jpg
     * houseName : 房间2
     * id : 11
     * paymentCycle : 9
     * rentAmount : 1800
     * rentType : 1
     * startTime : 2021-07-22 00:00:00
     * status : 0
     * statusStr : 未签约
     * userId : 16
     */

    private String adviserId;
    private String certificateNumber;
    private String certificatePhoto;
    private String certificateType;
    private String contractNo;
    private String createTime;
    private String delTf;
    private String deposit;
    private String emergencyPhone;
    private String emergencyRelationship;
    private String endTime;
    private String houseAddress;
    private String houseAttribute;
    private String houseId;
    private String houseImgUrl;
    private String houseName;
    private String id;
    private String paymentCycle;
    private String rentAmount;
    private String rentType;
    private String startTime;
    private String status;
    private String statusStr;
    private String userId;
    private List<BillsBean> bills;

    public String getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(String adviserId) {
        this.adviserId = adviserId;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificatePhoto() {
        return certificatePhoto;
    }

    public void setCertificatePhoto(String certificatePhoto) {
        this.certificatePhoto = certificatePhoto;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getEmergencyRelationship() {
        return emergencyRelationship;
    }

    public void setEmergencyRelationship(String emergencyRelationship) {
        this.emergencyRelationship = emergencyRelationship;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseAttribute() {
        return houseAttribute;
    }

    public void setHouseAttribute(String houseAttribute) {
        this.houseAttribute = houseAttribute;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseImgUrl() {
        return houseImgUrl;
    }

    public void setHouseImgUrl(String houseImgUrl) {
        this.houseImgUrl = houseImgUrl;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentCycle() {
        return paymentCycle;
    }

    public void setPaymentCycle(String paymentCycle) {
        this.paymentCycle = paymentCycle;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<BillsBean> getBills() {
        return bills;
    }

    public void setBills(List<BillsBean> bills) {
        this.bills = bills;
    }

    public static class BillsBean {
        /**
         * amount : 1800
         * contractId : 11
         * expireTime : 2021-07-22 17:14:33
         * id : 63
         * overdue : false
         * overdueDays : 0
         * status : 0
         * leftDays : 30
         */

        private String amount;
        private String contractId;
        private String expireTime;
        private String id;
        private boolean overdue;
        private String overdueDays;
        private String status;
        private String leftDays;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLeftDays() {
            return leftDays;
        }

        public void setLeftDays(String leftDays) {
            this.leftDays = leftDays;
        }
    }
}
