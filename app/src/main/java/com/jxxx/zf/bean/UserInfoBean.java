package com.jxxx.zf.bean;

public class UserInfoBean {


    /**
     * adviser : false
     * balance : 0
     * cityId : 0
     * createTime : 1625652287000
     * envelopesSum : 0
     * gender : 0
     * hasDelete : 0
     * id : 16
     * integral : 0
     * landlord : false
     * nickname : 
     * password : 5793EE3DEB1A256ABF80BEDD6EDD5EDB
     * portraitUri : https://c-ssl.duitang.com/uploads/item/201704/27/20170427155254_Kctx8.jpeg
     * provinceId : 0
     * refererId : 0
     * saltValue : rXQbQOsI5zAwgaZtu5VN
     * signFlag : false
     * status : 1
     * updateTime : 1625652287000
     * userNo : 17774004352
     * userType : 1
     */

    private boolean adviser;
    private String balance;
    private String cityId;
    private String createTime;
    private String envelopesSum;
    private String gender;
    private String hasDelete;
    private String id;
    private String integral;
    private boolean landlord;
    private String nickname;
    private String password;
    private String portraitUri;
    private String provinceId;
    private String refererId;
    private String saltValue;
    private boolean signFlag;
    private String status;
    private String updateTime;
    private String userNo;
    private String userType;
    private String identityFlag;//	实名认证状态 0未提交 1审核中 2已通过 3审核失败

    public void setIdentityFlag(String identityFlag) {
        this.identityFlag = identityFlag;
    }

    public String getIdentityFlag() {
        return identityFlag;
    }

    public boolean isAdviser() {
        return adviser;
    }

    public void setAdviser(boolean adviser) {
        this.adviser = adviser;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEnvelopesSum() {
        return envelopesSum;
    }

    public void setEnvelopesSum(String envelopesSum) {
        this.envelopesSum = envelopesSum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(String hasDelete) {
        this.hasDelete = hasDelete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public boolean isLandlord() {
        return landlord;
    }

    public void setLandlord(boolean landlord) {
        this.landlord = landlord;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getRefererId() {
        return refererId;
    }

    public void setRefererId(String refererId) {
        this.refererId = refererId;
    }

    public String getSaltValue() {
        return saltValue;
    }

    public void setSaltValue(String saltValue) {
        this.saltValue = saltValue;
    }

    public boolean isSignFlag() {
        return signFlag;
    }

    public void setSignFlag(boolean signFlag) {
        this.signFlag = signFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
