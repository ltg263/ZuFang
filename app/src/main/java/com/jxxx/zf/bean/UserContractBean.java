package com.jxxx.zf.bean;

import java.util.List;

public class UserContractBean {

    /**
     * count : 0
     * list : [{"adviserId":0,"appointmentId":0,"bills":[{"amount":0,"contractId":0,"expireTime":"","id":0,"overdue":true,"overdueDays":0,"payTime":"","remark":"","status":0}],"certificateNumber":"","certificatePhoto":"","certificateType":0,"contractNature":0,"contractNo":"","contractType":0,"createTime":"","delTf":0,"deposit":0,"emergencyPhone":"","emergencyRelationship":"","endTime":"","frontMoney":0,"gender":0,"houseId":0,"id":0,"landlordId":0,"mobile":"","paymentCycle":0,"realName":"","rentAmount":0,"startTime":"","status":0,"userId":0}]
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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
         * adviserId : 0
         * appointmentId : 0
         * bills : [{"amount":0,"contractId":0,"expireTime":"","id":0,"overdue":true,"overdueDays":0,"payTime":"","remark":"","status":0}]
         * certificateNumber : 
         * certificatePhoto : 
         * certificateType : 0
         * contractNature : 0
         * contractNo : 
         * contractType : 0
         * createTime : 
         * delTf : 0
         * deposit : 0
         * emergencyPhone : 
         * emergencyRelationship : 
         * endTime : 
         * frontMoney : 0
         * gender : 0
         * houseId : 0
         * id : 0
         * landlordId : 0
         * mobile : 
         * paymentCycle : 0
         * realName : 
         * rentAmount : 0
         * startTime : 
         * status : 0
         * userId : 0
         */

        private String adviserId;
        private String appointmentId;
        private String certificateNumber;
        private String certificatePhoto;
        private String certificateType;
        private String contractNature;
        private String contractNo;
        private String contractType;
        private String createTime;
        private String delTf;
        private String deposit;
        private String emergencyPhone;
        private String emergencyRelationship;
        private String endTime;
        private String frontMoney;
        private String gender;
        private String houseId;
        private String id;
        private String landlordId;
        private String mobile;
        private String paymentCycle;
        private String realName;
        private String rentAmount;
        private String startTime;
        private String status;
        private String userId;
        private List<BillsBean> bills;

        public String getAdviserId() {
            return adviserId;
        }

        public void setAdviserId(String adviserId) {
            this.adviserId = adviserId;
        }

        public String getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(String appointmentId) {
            this.appointmentId = appointmentId;
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

        public String getContractNature() {
            return contractNature;
        }

        public void setContractNature(String contractNature) {
            this.contractNature = contractNature;
        }

        public String getContractNo() {
            return contractNo;
        }

        public void setContractNo(String contractNo) {
            this.contractNo = contractNo;
        }

        public String getContractType() {
            return contractType;
        }

        public void setContractType(String contractType) {
            this.contractType = contractType;
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

        public String getFrontMoney() {
            return frontMoney;
        }

        public void setFrontMoney(String frontMoney) {
            this.frontMoney = frontMoney;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
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

        public String getLandlordId() {
            return landlordId;
        }

        public void setLandlordId(String landlordId) {
            this.landlordId = landlordId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPaymentCycle() {
            return paymentCycle;
        }

        public void setPaymentCycle(String paymentCycle) {
            this.paymentCycle = paymentCycle;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRentAmount() {
            return rentAmount;
        }

        public void setRentAmount(String rentAmount) {
            this.rentAmount = rentAmount;
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
             * amount : 0
             * contractId : 0
             * expireTime : 
             * id : 0
             * overdue : true
             * overdueDays : 0
             * payTime : 
             * remark : 
             * status : 0
             */

            private String amount;
            private String contractId;
            private String expireTime;
            private String id;
            private boolean overdue;
            private String overdueDays;
            private String payTime;
            private String remark;
            private String status;

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
}
