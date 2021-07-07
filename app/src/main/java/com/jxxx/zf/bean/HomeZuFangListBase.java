package com.jxxx.zf.bean;

import java.util.List;

public class HomeZuFangListBase {

    private List<HousesListBean> houses;
    private List<BannersBean> banners;

    public List<HousesListBean> getHouses() {
        return houses;
    }

    public void setHouses(List<HousesListBean> houses) {
        this.houses = houses;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }


    public static class BannersBean {
        /**
         * createTime : 1625563240000
         * delTf : 0
         * id : 2
         * imgUrl : https://c-ssl.duitang.com/uploads/item/201704/27/20170427155254_Kctx8.jpeg
         * queueNo : 1
         * status : 1
         * type : 1
         */

        private String createTime;
        private String delTf;
        private String id;
        private String imgUrl;
        private String queueNo;
        private String status;
        private String type;

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

        public String getQueueNo() {
            return queueNo;
        }

        public void setQueueNo(String queueNo) {
            this.queueNo = queueNo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
