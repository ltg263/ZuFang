package com.jxxx.zf.api;


import com.jxxx.zf.app.ConstValues;
import com.jxxx.zf.bean.HomeZuFangListBase;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.LoginData;
import com.jxxx.zf.bean.LoginRequest;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.ZuFangDetailsBase;

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
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 全局-文件上传接口
     * @return
     */
    @Multipart
    @POST(ConstValues.BASE_URL + "api/scmp-application-mall/global/file/upload")
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


    @GET("api/v1/home/getHome")
    Observable<Result<HomeZuFangListBase>> homeGetHome();

    @GET("api/v1/house/list")
    Observable<Result<HouseListBase>> HouseList(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("api/v1/house/details")
    Observable<Result<ZuFangDetailsBase>> houseDetails(@Query("id") String id);
}
