package com.jxxx.zf.bean;

public class LoginData {

    /**
     * adviser : true
     * landlord : true
     * tokenId : 
     * userBase : {"clientType":0,"createTime":"","gender":0,"id":0,"nickname":"","openId":"","portraitUri":"","refererId":0,"status":0,"userNo":"","userType":0}
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
         * clientType : 0
         * createTime : 
         * gender : 0
         * id : 0
         * nickname : 
         * openId : 
         * portraitUri : 
         * refererId : 0
         * status : 0
         * userNo : 
         * userType : 0
         */

        private String clientType;
        private String createTime;
        private String gender;
        private String id;
        private String nickname;
        private String openId;
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

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
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
