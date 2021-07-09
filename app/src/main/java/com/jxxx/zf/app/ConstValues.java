package com.jxxx.zf.app;

/**
 * Created by Administrator on 2017/9/1.
 */

public class ConstValues {
    /**
     * 应用名称
     */
    public static String APPNAME_ENGLISH = "zufang";
    /**sharedpreference 判断是否已登录字段*/
    public static final String ISLOGIN = "islogin";
    public static final String USERID = "user_id";
    public static final String TOKENID = "token";
    public static final String BASE_URL = "http://192.168.2.142:8088/renting/";

    //默认连接超时时间
    public static final int DEFAULT_TIMEOUT =60;
    public static final int PAGE_SIZE =10;
    public static String USER_TYPE = "user_type";//0 用户 1房东 2顾问 3房东顾问
}
