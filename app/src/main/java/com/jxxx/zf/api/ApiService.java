package com.jxxx.zf.api;


import com.jxxx.zf.bean.HomeZuFangListBase;
import com.jxxx.zf.bean.HouseListBase;
import com.jxxx.zf.bean.Result;
import com.jxxx.zf.bean.ZuFangDetailsBase;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("renting/api/v1/home/getHome")
    Observable<Result<HomeZuFangListBase>> homeGetHome();

    @GET("renting/api/v1/house/list")
    Observable<Result<HouseListBase>> HouseList(@Query("page") int page, @Query("pageSize") int pageSize);

    @GET("renting/api/v1/house/details")
    Observable<Result<ZuFangDetailsBase>> houseDetails(@Query("id") String id);
}
