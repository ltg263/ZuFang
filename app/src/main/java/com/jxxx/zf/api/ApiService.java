package com.jxxx.zf.api;


import com.jxxx.zf.bean.HomeZuFangListBase;
import com.jxxx.zf.bean.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("renting/api/v1/home/getHome")
    Observable<Result<HomeZuFangListBase>> homeGetHome();
}
