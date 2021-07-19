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
    public static final String NICK_NAME = "nickname";
    public static final String USER_NO = "user_no";
    public static final String IDENTITY_FLAG = "identity_flag";
    public static final String PORTRAIT_URI = "portrait_uri";
    public static final String BASE_URL = "http://192.168.2.142:8088/renting/";
//-朝东,2-朝南,3-朝西,4-朝北,5-朝东南,6-朝西南,7-朝东北,8-朝西北
    public static final String[] HOUSE_ORIENTATION = new String[]{"朝东" ,"朝南","朝西","朝北","朝东南","朝西南","朝东北","朝西北"};

    public static final String[] HOUSE_RENTING_TYPE = new String[]{"整租" ,"合租"};
    //	租金方式:1,押一付一;2,押一付二;3,押一付三;4,半年;5,一年
    public static  final String[] HOUSE_RENT_TYPE= new String[]{"押一付一" ,"押一付二","押一付三","半年","一年"};
    //	租金方式:1,押一付一;2,押一付二;3,押一付三;4,半年;5,一年
    public static  final String[] HOUSE_TYPE= new String[]{"一室" ,"两室","三室","三室以上"};
    //默认连接超时时间
    public static final int DEFAULT_TIMEOUT =60;
    public static final int PAGE_SIZE =10;
    public static String USER_TYPE = "user_type";//0 用户 1房东 2顾问 3房东顾问
    public static String LOCATION_LONGITUDE = "location_longitude";//经度
    public static String LOCATION_LATITUDE = "location_latitude";//维度
}
