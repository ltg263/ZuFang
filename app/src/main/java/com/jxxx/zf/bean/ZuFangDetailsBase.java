package com.jxxx.zf.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.jxxx.zf.utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ZuFangDetailsBase implements Parcelable {

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

    private String address="";
    private String appointmentNum="";
    private String area="";
    private String businessAreaId="";
    private String cityId="";
    private String isCollection="";
    private String commission="";
    private String createTime="";
    private String delTf="";
    private String details="";
    private String districtId="";
    private String floor="";
    private String hasElevator="";
    private String hasParking="";
    private String houseEstateId="";
    private String houseLables="";
    private String houseParams="";
    private String houseType="";
    private String housingEstateName="";
    private String id="";
    private String imgUrl="";
    private String imgUrls="";
    private String lat="";
    private String lon="";
    private String name="";
    private String orientation="";
    private String provinceId="";
    private String renovationType="";
    private String rent="";
    private String rentType="";
    private String rentingType="";
    private String status="";
    private String viewNum="";
    private String hasVideo="";
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

    public static class LablesBean implements Parcelable {
        /**
         * id : 1
         * name : 靠近地铁
         */

        private String id="";
        private String name="";

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
        }

        public void readFromParcel(Parcel source) {
            this.id = source.readString();
            this.name = source.readString();
        }

        public LablesBean() {
        }

        protected LablesBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
        }

        public static final Creator<LablesBean> CREATOR = new Creator<LablesBean>() {
            @Override
            public LablesBean createFromParcel(Parcel source) {
                return new LablesBean(source);
            }

            @Override
            public LablesBean[] newArray(int size) {
                return new LablesBean[size];
            }
        };
    }

    public static class ParamsBean implements Parcelable {
        /**
         * id : 1
         * paramName : 空调
         */

        private String id="";
        private String paramName="";
        private String iconUrl="";

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.paramName);
            dest.writeString(this.iconUrl);
        }

        public void readFromParcel(Parcel source) {
            this.id = source.readString();
            this.paramName = source.readString();
            this.iconUrl = source.readString();
        }

        public ParamsBean() {
        }

        protected ParamsBean(Parcel in) {
            this.id = in.readString();
            this.paramName = in.readString();
            this.iconUrl = in.readString();
        }

        public static final Creator<ParamsBean> CREATOR = new Creator<ParamsBean>() {
            @Override
            public ParamsBean createFromParcel(Parcel source) {
                return new ParamsBean(source);
            }

            @Override
            public ParamsBean[] newArray(int size) {
                return new ParamsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.appointmentNum);
        dest.writeString(this.area);
        dest.writeString(this.businessAreaId);
        dest.writeString(this.cityId);
        dest.writeString(this.isCollection);
        dest.writeString(this.commission);
        dest.writeString(this.createTime);
        dest.writeString(this.delTf);
        dest.writeString(this.details);
        dest.writeString(this.districtId);
        dest.writeString(this.floor);
        dest.writeString(this.hasElevator);
        dest.writeString(this.hasParking);
        dest.writeString(this.houseEstateId);
        dest.writeString(this.houseLables);
        dest.writeString(this.houseParams);
        dest.writeString(this.houseType);
        dest.writeString(this.housingEstateName);
        dest.writeString(this.id);
        dest.writeString(this.imgUrl);
        dest.writeString(this.imgUrls);
        dest.writeString(this.lat);
        dest.writeString(this.lon);
        dest.writeString(this.name);
        dest.writeString(this.orientation);
        dest.writeString(this.provinceId);
        dest.writeString(this.renovationType);
        dest.writeString(this.rent);
        dest.writeString(this.rentType);
        dest.writeString(this.rentingType);
        dest.writeString(this.status);
        dest.writeString(this.viewNum);
        dest.writeString(this.hasVideo);
        dest.writeList(this.lables);
        dest.writeList(this.nearHouses);
        dest.writeList(this.params);
    }

    public void readFromParcel(Parcel source) {
        this.address = source.readString();
        this.appointmentNum = source.readString();
        this.area = source.readString();
        this.businessAreaId = source.readString();
        this.cityId = source.readString();
        this.isCollection = source.readString();
        this.commission = source.readString();
        this.createTime = source.readString();
        this.delTf = source.readString();
        this.details = source.readString();
        this.districtId = source.readString();
        this.floor = source.readString();
        this.hasElevator = source.readString();
        this.hasParking = source.readString();
        this.houseEstateId = source.readString();
        this.houseLables = source.readString();
        this.houseParams = source.readString();
        this.houseType = source.readString();
        this.housingEstateName = source.readString();
        this.id = source.readString();
        this.imgUrl = source.readString();
        this.imgUrls = source.readString();
        this.lat = source.readString();
        this.lon = source.readString();
        this.name = source.readString();
        this.orientation = source.readString();
        this.provinceId = source.readString();
        this.renovationType = source.readString();
        this.rent = source.readString();
        this.rentType = source.readString();
        this.rentingType = source.readString();
        this.status = source.readString();
        this.viewNum = source.readString();
        this.hasVideo = source.readString();
        this.lables = new ArrayList<LablesBean>();
        source.readList(this.lables, LablesBean.class.getClassLoader());
        this.nearHouses = new ArrayList<ZuFangDetailsBase>();
        source.readList(this.nearHouses, ZuFangDetailsBase.class.getClassLoader());
        this.params = new ArrayList<ParamsBean>();
        source.readList(this.params, ParamsBean.class.getClassLoader());
    }

    public ZuFangDetailsBase() {
    }

    protected ZuFangDetailsBase(Parcel in) {
        this.address = in.readString();
        this.appointmentNum = in.readString();
        this.area = in.readString();
        this.businessAreaId = in.readString();
        this.cityId = in.readString();
        this.isCollection = in.readString();
        this.commission = in.readString();
        this.createTime = in.readString();
        this.delTf = in.readString();
        this.details = in.readString();
        this.districtId = in.readString();
        this.floor = in.readString();
        this.hasElevator = in.readString();
        this.hasParking = in.readString();
        this.houseEstateId = in.readString();
        this.houseLables = in.readString();
        this.houseParams = in.readString();
        this.houseType = in.readString();
        this.housingEstateName = in.readString();
        this.id = in.readString();
        this.imgUrl = in.readString();
        this.imgUrls = in.readString();
        this.lat = in.readString();
        this.lon = in.readString();
        this.name = in.readString();
        this.orientation = in.readString();
        this.provinceId = in.readString();
        this.renovationType = in.readString();
        this.rent = in.readString();
        this.rentType = in.readString();
        this.rentingType = in.readString();
        this.status = in.readString();
        this.viewNum = in.readString();
        this.hasVideo = in.readString();
        this.lables = new ArrayList<LablesBean>();
        in.readList(this.lables, LablesBean.class.getClassLoader());
        this.nearHouses = new ArrayList<ZuFangDetailsBase>();
        in.readList(this.nearHouses, ZuFangDetailsBase.class.getClassLoader());
        this.params = new ArrayList<ParamsBean>();
        in.readList(this.params, ParamsBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ZuFangDetailsBase> CREATOR = new Parcelable.Creator<ZuFangDetailsBase>() {
        @Override
        public ZuFangDetailsBase createFromParcel(Parcel source) {
            return new ZuFangDetailsBase(source);
        }

        @Override
        public ZuFangDetailsBase[] newArray(int size) {
            return new ZuFangDetailsBase[size];
        }
    };
}
