package com.jxxx.zf.bean;

import com.jxxx.zf.utils.StringUtil;

import java.io.Serializable;
import java.util.List;

public class ZuFangDetailsBase{

    /**
     * address : BBBBBB
     * appointmentNum : 11
     * area : 50.5
     * businessAreaId : 1
     * cityId : 330100
     * collection : false
     * commission : 300
     * createTime : 1625046487000
     * delTf : 0
     * details : 文字描述
     * districtId : 330102
     * floor : 8
     * hasElevator : 0
     * hasParking : 0
     * houseEstateId : 1
     * houseLables : 1,2
     * houseParams : 1,2,4
     * houseType : 1
     * housingEstateName : A小区
     * id : 2
     * imgUrl : tu.jpg
     * imgUrls : tu1,tu2
     * lables : [{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}]
     * lat : 29.8076665582
     * lon : 121.5572157118
     * name : 房间2
     * nearHouses : [{"address":"AAAAAA","appointmentNum":11,"area":45.5,"businessAreaId":1,"cityId":330100,"collection":false,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":3,"hasElevator":0,"hasParking":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,3","houseType":1,"id":1,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间1","orientation":1,"provinceId":330000,"renovationType":1,"rent":1600,"rentType":2,"rentingType":2,"status":1,"viewNum":11},{"address":"BBBBBB","appointmentNum":11,"area":50.5,"businessAreaId":1,"cityId":330100,"collection":false,"commission":300,"createTime":1625046487000,"delTf":0,"districtId":330102,"floor":8,"hasElevator":0,"hasParking":0,"houseEstateId":1,"houseLables":"1,2","houseParams":"1,2,4","houseType":1,"id":2,"imgUrl":"tu.jpg","lables":[{"id":1,"name":"靠近地铁"},{"id":2,"name":"房东直租"}],"lat":29.8076665582,"lon":121.5572157118,"name":"房间2","orientation":1,"provinceId":330000,"renovationType":1,"rent":1800,"rentType":1,"rentingType":1,"status":1,"viewNum":11}]
     * orientation : 1
     * params : [{"id":1,"paramName":"空调"},{"id":2,"paramName":"热水器"},{"id":4,"paramName":"衣柜"}]
     * provinceId : 330000
     * renovationType : 1
     * rent : 1800
     * rentType : 1
     * rentingType : 1
     * status : 1
     * viewNum : 11
     */

    private String address;
    private String appointmentNum;
    private String area;
    private String businessAreaId;
    private String cityId;
    private String isCollection;
    private String commission;
    private String createTime;
    private String delTf;
    private String details;
    private String districtId;
    private String floor;
    private String hasElevator;
    private String hasParking;
    private String houseEstateId;
    private String houseLables;
    private String houseParams;
    private String houseType;
    private String housingEstateName;
    private String id;
    private String imgUrl;
    private String imgUrls;
    private String lat;
    private String lon;
    private String name;
    private String orientation;
    private String provinceId;
    private String renovationType;
    private String rent;
    private String rentType;
    private String rentingType;
    private String status;
    private String viewNum;
    private String hasVideo;
    private List<LablesBean> lables;
    private List<ZuFangDetailsBase> nearHouses;
    private List<ParamsBean> params;

    public void setHasVideo(String hasVideo) {
        this.hasVideo = hasVideo;
    }

    public String getHasVideo() {
        return hasVideo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppointmentNum() {
        return appointmentNum;
    }

    public void setAppointmentNum(String appointmentNum) {
        this.appointmentNum = appointmentNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessAreaId() {
        return businessAreaId;
    }

    public void setBusinessAreaId(String businessAreaId) {
        this.businessAreaId = businessAreaId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(String isCollection) {
        this.isCollection = isCollection;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(String hasElevator) {
        this.hasElevator = hasElevator;
    }

    public String getHasParking() {
        return hasParking;
    }

    public void setHasParking(String hasParking) {
        this.hasParking = hasParking;
    }

    public String getHouseEstateId() {
        return houseEstateId;
    }

    public void setHouseEstateId(String houseEstateId) {
        this.houseEstateId = houseEstateId;
    }

    public String getHouseLables() {
        return houseLables;
    }

    public void setHouseLables(String houseLables) {
        this.houseLables = houseLables;
    }

    public String getHouseParams() {
        return houseParams;
    }

    public void setHouseParams(String houseParams) {
        this.houseParams = houseParams;
    }

    public String getHouseType() {
        if(StringUtil.isBlank(houseType)){
            return "";
        }
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHousingEstateName() {
        return housingEstateName;
    }

    public void setHousingEstateName(String housingEstateName) {
        this.housingEstateName = housingEstateName;
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

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrientation() {
        if(StringUtil.isBlank(orientation)){
            return "无";
        }
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getRenovationType() {
        return renovationType;
    }

    public void setRenovationType(String renovationType) {
        this.renovationType = renovationType;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getRentingType() {
        if(StringUtil.isBlank(rentingType)){
            return "无";
        }
        return rentingType;
    }

    public void setRentingType(String rentingType) {
        this.rentingType = rentingType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public List<LablesBean> getLables() {
        return lables;
    }

    public void setLables(List<LablesBean> lables) {
        this.lables = lables;
    }

    public List<ZuFangDetailsBase> getNearHouses() {
        return nearHouses;
    }

    public void setNearHouses(List<ZuFangDetailsBase> nearHouses) {
        this.nearHouses = nearHouses;
    }

    public List<ParamsBean> getParams() {
        return params;
    }

    public void setParams(List<ParamsBean> params) {
        this.params = params;
    }

    public static class LablesBean {
        /**
         * id : 1
         * name : 靠近地铁
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ParamsBean {
        /**
         * id : 1
         * paramName : 空调
         */

        private String id;
        private String paramName;
        private String iconUrl;

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }
    }
}
