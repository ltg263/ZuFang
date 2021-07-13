package com.jxxx.zf.api;


import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.bean.AdviserListBean;
import com.jxxx.zf.bean.AppointmentDetailsBase;
import com.jxxx.zf.bean.AppointmentList;
import com.jxxx.zf.bean.ApponintmentApply;
import com.jxxx.zf.bean.HomeZuFangListBase;
import com.jxxx.zf.bean.HouseCompareBean;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.HouseParamListBean;
import com.jxxx.zf.bean.LoginData;
import com.jxxx.zf.bean.ApplyInfoBean;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.UserInfoBean;
import com.jxxx.zf.bean.ZuFangDetailsBase;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 全局-文件上传接口
     * @return
     */
    @Multipart
    @POST(ConstValues.BASE_URL + "api/v1/image")
    Observable<Result> uploadFile(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);


    /**
     * 全局-发送短信验证码
     * @return
     */
    @POST(ConstValues.BASE_URL + "api/v1/user/verify/getVerifyCode")
    Observable<Result> sendSms(@Query("mobile") String mobile);

    /**
     * 登录页-手机密码登录接口
     * @return
     */
    @POST(ConstValues.BASE_URL + "api/v1/user/verify/login")
    Observable<Result<LoginData>> pwdLogin(@Query("clientType") String clientType,@Query("phone") String phone,@Query("password") String password,@Query("verify") String verify);

    /**
     * 注册页-手机短验注册接口
     * @return
     */
    @POST(ConstValues.BASE_URL + "api/v1/user/verify/register")
    Observable<Result<LoginData>> smsRegister(@Query("clientType") String clientType,@Query("phone") String phone,@Query("password") String password,@Query("verify") String verify);


    @GET("api/v1/user/getDetails")
    Observable<Result<UserInfoBean>> getDetails();


    @GET("api/v1/user/appointment/appointmentList")
    Observable<Result<AppointmentList>> getAppointmentList();

    @POST("api/v1/user/appointment/cancel")
    Observable<Result> getAppointmentCancel(@Query("id") String id);

    @GET("api/v1/user/appointment/list")
    Observable<Result<AppointmentList>> getUserAppointmentList(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("api/v1/user/appointment/appointmentList")
    Observable<Result<AppointmentList>> getUserAppointmentJdList(@Query("page") int page, @Query("pageSize") int pageSize,@Query("status") String status);


    @POST("api/v1/user/realNameAuthentication")
    Observable<Result> realNameAuthentication(@Body ApplyInfoBean.RealNameAuthentication realNameAuthentication);


    @POST("api/v1/user/adviser/add")
    Observable<Result> realNameAdviser(@Body ApplyInfoBean.RealNameAdviser realNameAdviser);


    @POST("api/v1/user/appointment/apply")
    Observable<Result<AppointmentDetailsBase>> getAppointmentApply(@Body ApponintmentApply app);

    @POST("api/v1/user/appointment/update")
    Observable<Result<AppointmentDetailsBase>> getAppointmentApplyUpdate(@Body ApponintmentApply app);


    @GET("api/v1/user/appointment/details")
    Observable<Result<AppointmentDetailsBase>> getAppointmentDetails(@Query("id") String  id);

    @GET("api/v1/adviser/list")
    Observable<Result<AdviserListBean>> getAdviserList();


    @GET("api/v1/home/getHome")
    Observable<Result<HomeZuFangListBase>> homeGetHome();

    @GET("api/v1/house/list")
    Observable<Result<HouseListBase>> HouseList(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("api/v1/house/details")
    Observable<Result<ZuFangDetailsBase>> houseDetails(@Query("id") String id);

    @POST("api/v1/house/compare")
    Observable<Result<List<HouseCompareBean>>> houseCompare(@Query("houseIds") String houseIds,@Query("hideSame") String hideSame);

    @POST("api/v1/user/collection/doCollection")
    Observable<Result<String>> houseDoCollection(@Query("houseId") String houseId);

    @POST("api/v1/user/adviser/receivingOrder")
    Observable<Result> receivingOrder(@Query("appointmentId") String appointmentId);

    @GET("api/v1/houseParam/listAll")
    Observable<Result<List<HouseParamListBean>>> getHouseParamAll();

    @POST("api/v1/user/house/add")
    Observable<Result> addUserHouse(@Body ZuFangDetailsBase mZuFangDetailsBase);
}
