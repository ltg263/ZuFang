package com.jxxx.zf.bean;

import java.util.List;

public class HouseEstateList {

    /**
     * count : 1
     * list : [{"estateName":"A小区","id":1,"streetId":11966}]
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
         * estateName : A小区
         * id : 1
         * streetId : 11966
         */

        private String estateName;
        private String id;
        private String streetId;

        public String getEstateName() {
            return estateName;
        }

        public void setEstateName(String estateName) {
            this.estateName = estateName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStreetId() {
            return streetId;
        }

        public void setStreetId(String streetId) {
            this.streetId = streetId;
        }
    }
}
