package com.jxxx.zf.bean;

public class LoginData {

    /**
     * adviser : false
     * landlord : false
     * tokenId : 9ecafd65-bb2a-4377-a147-9ac93b743888_1
     * userBase : {"clientType":3,"createTime":1625652287000,"gender":0,"id":16,"nickname":"","portraitUri":"https://c-ssl.duitang.com/uploads/item/201704/27/20170427155254_Kctx8.jpeg","refererId":0,"status":1,"userNo":"17774004352","userType":1}
     */

    private boolean adviser;
    private boolean landlord;
    private String tokenId;
    private UserBaseBean userBase;

    public boolean isAdviser() {
        return adviser;
    }

    public void setAdviser(boolean adviser) {
        this.adviser = adviser;
    }

    public boolean isLandlord() {
        return landlord;
    }

    public void setLandlord(boolean landlord) {
        this.landlord = landlord;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public UserBaseBean getUserBase() {
        return userBase;
    }

    public void setUserBase(UserBaseBean userBase) {
        this.userBase = userBase;
    }

    public static class UserBaseBean {
        /**
         * clientType : 3
         * createTime : 1625652287000
         * gender : 0
         * id : 16
         * nickname : 
         * portraitUri : https://c-ssl.duitang.com/uploads/item/201704/27/20170427155254_Kctx8.jpeg
         * refererId : 0
         * status : 1
         * userNo : 17774004352
         * userType : 1
         */

        private String clientType;
        private String createTime;
        private String gender;
        private String id;
        private String nickname;
        private String portraitUri;
        private String refererId;
        private String status;
        private String userNo;
        private String userType;

        public String getClientType() {
            return clientType;
        }

        public void setClientType(String clientType) {
            this.clientType = clientType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPortraitUri() {
            return portraitUri;
        }

        public void setPortraitUri(String portraitUri) {
            this.portraitUri = portraitUri;
        }

        public String getRefererId() {
            return refererId;
        }

        public void setRefererId(String refererId) {
            this.refererId = refererId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
}
