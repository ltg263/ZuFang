package com.jxxx.zf.bean;

import java.util.List;

public class AppointmentDetailsBase {

    /**
     * adviserId : 1
     * advserName : AAA
     * appointmentTime : 1629450000000
     * createTime : 1625732993107
     * delTf : 0
     * gender : 0
     * hasAdviser : 1
     * house : {"address":"AAAAAA","appointmentNum":22,"area":45.5,"businessAreaId":1,"cityId":330100,"commission":300,"createTime":1625046487000,"delTf":0,"details":"文字描述","districtId":330102,"floor":3,"hasElevator":0,"hasParking":0,"hasVideo":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,3","houseType":1,"housingEstateName":"A小区","id":1,"imgUrl":"tu.jpg","imgUrls":"tu1,tu2","isCollection":0,"lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间1","nearHouses":[{"address":"AAAAAA","appointmentNum":22,"area":45.5,"businessAreaId":1,"cityId":330100,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":3,"hasElevator":0,"hasParking":0,"hasVideo":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,3","houseType":1,"id":1,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间1","openHomeTime":"00:00-23:59","orientation":1,"provinceId":330000,"renovationType":1,"rent":1600,"rentType":2,"rentingType":2,"status":1,"viewNum":11},{"address":"BBBBBB","appointmentNum":12,"area":50.5,"businessAreaId":1,"cityId":330100,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":8,"hasElevator":0,"hasParking":0,"hasVideo":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,4","houseType":1,"id":2,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间21","openHomeTime":"00:00-23:59","orientation":1,"provinceId":330000,"renovationType":1,"rent":1800,"rentType":1,"rentingType":1,"status":1,"viewNum":11},{"address":"AAAAAA","appointmentNum":12,"area":45.5,"businessAreaId":1,"cityId":330100,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":3,"hasElevator":0,"hasParking":0,"hasVideo":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,3","houseType":1,"id":3,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间11","openHomeTime":"00:00-23:59","orientation":1,"provinceId":330000,"renovationType":1,"rent":1600,"rentType":2,"rentingType":2,"status":1,"viewNum":11},{"address":"BBBBBB","appointmentNum":11,"area":50.5,"businessAreaId":1,"cityId":330100,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":8,"hasElevator":0,"hasParking":0,"hasVideo":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,4","houseType":1,"id":4,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间2","openHomeTime":"00:00-23:59","orientation":1,"provinceId":330000,"renovationType":1,"rent":1800,"rentType":1,"rentingType":1,"status":1,"viewNum":11},{"address":"BBBBBB","appointmentNum":11,"area":50.5,"businessAreaId":1,"cityId":330100,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":8,"hasElevator":0,"hasParking":0,"hasVideo":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,4","houseType":1,"id":5,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间24","openHomeTime":"00:00-23:59","orientation":1,"provinceId":330000,"renovationType":1,"rent":1800,"rentType":1,"rentingType":1,"status":1,"viewNum":11},{"address":"BBBBBB","appointmentNum":11,"area":50.5,"businessAreaId":1,"cityId":330100,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":8,"hasElevator":0,"hasParking":0,"hasVideo":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,4","houseType":1,"id":6,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间4","openHomeTime":"00:00-23:59","orientation":1,"provinceId":330000,"renovationType":1,"rent":1800,"rentType":1,"rentingType":1,"status":1,"viewNum":11},{"address":"BBBBBB","appointmentNum":11,"area":50.5,"businessAreaId":1,"cityId":330100,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":8,"hasElevator":0,"hasParking":0,"hasVideo":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,4","houseType":1,"id":7,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间45","openHomeTime":"00:00-23:59","orientation":1,"provinceId":330000,"renovationType":1,"rent":1800,"rentType":1,"rentingType":1,"status":1,"viewNum":11},{"address":"BBBBBB","appointmentNum":11,"area":50.5,"businessAreaId":1,"cityId":330100,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":8,"hasElevator":0,"hasParking":0,"hasVideo":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,4","houseType":1,"id":8,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间6","openHomeTime":"00:00-23:59","orientation":1,"provinceId":330000,"renovationType":1,"rent":1800,"rentType":1,"rentingType":1,"status":1,"viewNum":11},{"address":"BBBBBB","appointmentNum":11,"area":50.5,"businessAreaId":1,"cityId":330100,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":8,"hasElevator":0,"hasParking":0,"hasVideo":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,4","houseType":1,"id":9,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间46","openHomeTime":"00:00-23:59","orientation":1,"provinceId":330000,"renovationType":1,"rent":1800,"rentType":1,"rentingType":1,"status":1,"viewNum":11},{"address":"BBBBBB","appointmentNum":11,"area":50.5,"businessAreaId":1,"cityId":330100,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":8,"hasElevator":0,"hasParking":0,"hasVideo":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,4","houseType":1,"id":10,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间7","openHomeTime":"00:00-23:59","orientation":1,"provinceId":330000,"renovationType":1,"rent":1800,"rentType":1,"rentingType":1,"status":1,"viewNum":11}],"openHomeTime":"00:00-23:59","orientation":1,"params":[{"iconUrl":"tu.jpg","id":1,"paramName":"空调"},{"iconUrl":"tu.jpg","id":2,"paramName":"热水器"},{"iconUrl":"tu.jpg","id":3,"paramName":"床"}],"provinceId":330000,"renovationType":1,"rent":1600,"rentType":2,"rentingType":2,"status":1,"viewNum":11}
     * houseId : 1
     * id : 21
     * mobile : lili
     * realName : lili
     * remark : lili
     * status : 0
     * userId : 16
     */

    private String adviserId;
    private String advserName;
    private String appointmentTime;
    private String createTime;
    private String delTf;
    private String gender;
    private String hasAdviser;
    private ZuFangDetailsBase house;
    private String houseId;
    private String id;
    private String mobile;
    private String realName;
    private String remark;
    private String status;
    private String userId;

    public String getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(String adviserId) {
        this.adviserId = adviserId;
    }

    public String getAdvserName() {
        return advserName;
    }

    public void setAdvserName(String advserName) {
        this.advserName = advserName;
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

    public ZuFangDetailsBase getHouse() {
        return house;
    }

    public void setHouse(ZuFangDetailsBase house) {
        this.house = house;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

