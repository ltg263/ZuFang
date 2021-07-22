package com.jxxx.zf.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
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

    public static class ListBean implements Parcelable {
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
        private String houseImgUrl;
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
        private String houseName;
        private String rentType;
        private String rentalDuration;
        private String id;
        private String landlordId;
        private String mobile;
        private String paymentCycle;
        private String realName;
        private String rentAmount;
        private String startTime;
        private String status;
        private String statusStr;
        private String userId;
        private String houseAttribute;
        private List<BillsBean> bills;
        private List<ItemsBean> items;

        public String getHouseImgUrl() {
            return houseImgUrl;
        }

        public void setHouseImgUrl(String houseImgUrl) {
            this.houseImgUrl = houseImgUrl;
        }

        public void setHouseAttribute(String houseAttribute) {
            this.houseAttribute = houseAttribute;
        }

        public String getHouseAttribute() {
            return houseAttribute;
        }

        public String getHouseName() {
            return houseName;
        }

        public void setHouseName(String houseName) {
            this.houseName = houseName;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public String getRentalDuration() {
            return rentalDuration;
        }

        public void setRentalDuration(String rentalDuration) {
            this.rentalDuration = rentalDuration;
        }

        public void setRentType(String rentType) {
            this.rentType = rentType;
        }

        public String getRentType() {
            return rentType;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

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
        public static class ItemsBean implements Parcelable {

            /**
             * contractId : 0
             * houseParamId : 0
             * id : 0
             * num : 0
             * paramName :
             */

            private String contractId;
            private String houseParamId;
            private String id;
            private String num;
            private String paramName;

            public String getContractId() {
                return contractId;
            }

            public void setContractId(String contractId) {
                this.contractId = contractId;
            }

            public String getHouseParamId() {
                return houseParamId;
            }

            public void setHouseParamId(String houseParamId) {
                this.houseParamId = houseParamId;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getParamName() {
                return paramName;
            }

            public void setParamName(String paramName) {
                this.paramName = paramName;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.contractId);
                dest.writeString(this.houseParamId);
                dest.writeString(this.id);
                dest.writeString(this.num);
                dest.writeString(this.paramName);
            }

            public void readFromParcel(Parcel source) {
                this.contractId = source.readString();
                this.houseParamId = source.readString();
                this.id = source.readString();
                this.num = source.readString();
                this.paramName = source.readString();
            }

            public ItemsBean() {
            }

            protected ItemsBean(Parcel in) {
                this.contractId = in.readString();
                this.houseParamId = in.readString();
                this.id = in.readString();
                this.num = in.readString();
                this.paramName = in.readString();
            }

            public static final Creator<ItemsBean> CREATOR = new Creator<ItemsBean>() {
                @Override
                public ItemsBean createFromParcel(Parcel source) {
                    return new ItemsBean(source);
                }

                @Override
                public ItemsBean[] newArray(int size) {
                    return new ItemsBean[size];
                }
            };
        }

        public static class BillsBean implements Parcelable {
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.amount);
                dest.writeString(this.contractId);
                dest.writeString(this.expireTime);
                dest.writeString(this.id);
                dest.writeByte(this.overdue ? (byte) 1 : (byte) 0);
                dest.writeString(this.overdueDays);
                dest.writeString(this.payTime);
                dest.writeString(this.remark);
                dest.writeString(this.status);
            }

            public void readFromParcel(Parcel source) {
                this.amount = source.readString();
                this.contractId = source.readString();
                this.expireTime = source.readString();
                this.id = source.readString();
                this.overdue = source.readByte() != 0;
                this.overdueDays = source.readString();
                this.payTime = source.readString();
                this.remark = source.readString();
                this.status = source.readString();
            }

            public BillsBean() {
            }

            protected BillsBean(Parcel in) {
                this.amount = in.readString();
                this.contractId = in.readString();
                this.expireTime = in.readString();
                this.id = in.readString();
                this.overdue = in.readByte() != 0;
                this.overdueDays = in.readString();
                this.payTime = in.readString();
                this.remark = in.readString();
                this.status = in.readString();
            }

            public static final Creator<BillsBean> CREATOR = new Creator<BillsBean>() {
                @Override
                public BillsBean createFromParcel(Parcel source) {
                    return new BillsBean(source);
                }

                @Override
                public BillsBean[] newArray(int size) {
                    return new BillsBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.adviserId);
            dest.writeString(this.appointmentId);
            dest.writeString(this.certificateNumber);
            dest.writeString(this.certificatePhoto);
            dest.writeString(this.certificateType);
            dest.writeString(this.contractNature);
            dest.writeString(this.contractNo);
            dest.writeString(this.contractType);
            dest.writeString(this.createTime);
            dest.writeString(this.delTf);
            dest.writeString(this.deposit);
            dest.writeString(this.emergencyPhone);
            dest.writeString(this.emergencyRelationship);
            dest.writeString(this.endTime);
            dest.writeString(this.frontMoney);
            dest.writeString(this.gender);
            dest.writeString(this.houseId);
            dest.writeString(this.rentType);
            dest.writeString(this.rentalDuration);
            dest.writeString(this.id);
            dest.writeString(this.landlordId);
            dest.writeString(this.mobile);
            dest.writeString(this.paymentCycle);
            dest.writeString(this.realName);
            dest.writeString(this.rentAmount);
            dest.writeString(this.startTime);
            dest.writeString(this.houseImgUrl);
            dest.writeString(this.houseAttribute);
            dest.writeString(this.houseName);
            dest.writeString(this.statusStr);
            dest.writeString(this.status);
            dest.writeString(this.userId);
            dest.writeList(this.bills);
            dest.writeList(this.items);
        }

        public void readFromParcel(Parcel source) {
            this.adviserId = source.readString();
            this.appointmentId = source.readString();
            this.certificateNumber = source.readString();
            this.certificatePhoto = source.readString();
            this.certificateType = source.readString();
            this.contractNature = source.readString();
            this.contractNo = source.readString();
            this.contractType = source.readString();
            this.createTime = source.readString();
            this.delTf = source.readString();
            this.deposit = source.readString();
            this.emergencyPhone = source.readString();
            this.emergencyRelationship = source.readString();
            this.endTime = source.readString();
            this.frontMoney = source.readString();
            this.gender = source.readString();
            this.houseId = source.readString();
            this.rentType = source.readString();
            this.rentalDuration = source.readString();
            this.id = source.readString();
            this.landlordId = source.readString();
            this.mobile = source.readString();
            this.paymentCycle = source.readString();
            this.realName = source.readString();
            this.rentAmount = source.readString();
            this.startTime = source.readString();
            this.houseImgUrl = source.readString();
            this.houseAttribute = source.readString();
            this.houseName = source.readString();
            this.statusStr = source.readString();
            this.status = source.readString();
            this.userId = source.readString();
            this.bills = new ArrayList<BillsBean>();
            this.items = new ArrayList<ItemsBean>();
            source.readList(this.bills, BillsBean.class.getClassLoader());
            source.readList(this.items, ItemsBean.class.getClassLoader());
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.adviserId = in.readString();
            this.appointmentId = in.readString();
            this.certificateNumber = in.readString();
            this.certificatePhoto = in.readString();
            this.certificateType = in.readString();
            this.contractNature = in.readString();
            this.contractNo = in.readString();
            this.contractType = in.readString();
            this.createTime = in.readString();
            this.delTf = in.readString();
            this.deposit = in.readString();
            this.emergencyPhone = in.readString();
            this.emergencyRelationship = in.readString();
            this.endTime = in.readString();
            this.frontMoney = in.readString();
            this.gender = in.readString();
            this.houseId = in.readString();
            this.rentType = in.readString();
            this.rentalDuration = in.readString();
            this.id = in.readString();
            this.landlordId = in.readString();
            this.mobile = in.readString();
            this.paymentCycle = in.readString();
            this.realName = in.readString();
            this.rentAmount = in.readString();
            this.startTime = in.readString();
            this.houseImgUrl = in.readString();
            this.houseAttribute = in.readString();
            this.houseName = in.readString();
            this.statusStr = in.readString();
            this.status = in.readString();
            this.userId = in.readString();
            this.bills = new ArrayList<BillsBean>();
            this.items = new ArrayList<ItemsBean>();
            in.readList(this.bills, BillsBean.class.getClassLoader());
            in.readList(this.items, ItemsBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }
}
