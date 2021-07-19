package com.jxxx.zf.bean;

import java.util.List;

public class MyCustomersBean {

    /**
     * count : 5
     * list : [{"houseName":"房间2","mobile":"17774004352","nickname":"","portraitUri":"https://c-ssl.duitang.com/uploads/item/201704/27/20170427155254_Kctx8.jpeg","userId":16},{"houseName":"房间1","mobile":"17774004352","nickname":"","portraitUri":"https://c-ssl.duitang.com/uploads/item/201704/27/20170427155254_Kctx8.jpeg","userId":16},{"houseName":"房间1","mobile":"17774004352","nickname":"","portraitUri":"https://c-ssl.duitang.com/uploads/item/201704/27/20170427155254_Kctx8.jpeg","userId":16},{"houseName":"房间11","mobile":"17774004352","nickname":"","portraitUri":"https://c-ssl.duitang.com/uploads/item/201704/27/20170427155254_Kctx8.jpeg","userId":16},{"houseName":"房间1","mobile":"17774004352","nickname":"","portraitUri":"https://c-ssl.duitang.com/uploads/item/201704/27/20170427155254_Kctx8.jpeg","userId":16}]
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
         * houseName : 房间2
         * mobile : 17774004352
         * nickname :
         * portraitUri : https://c-ssl.duitang.com/uploads/item/201704/27/20170427155254_Kctx8.jpeg
         * userId : 16
         */

        private String houseName;
        private String mobile;
        private String nickname;
        private String portraitUri;
        private String userId;

        public String getHouseName() {
            return houseName;
        }

        public void setHouseName(String houseName) {
            this.houseName = houseName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
