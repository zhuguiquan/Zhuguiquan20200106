package com.bw.zhuguiquan20200106;

import com.bw.zhuguiquan20200106.model.bean.LeftBean;
import com.bw.zhuguiquan20200106.model.bean.RightBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * DateTime:2020/1/6 0006
 * author:朱贵全(Administrator)
 * function:
 */
public interface Api {
    @GET("category")
    Observable<LeftBean>leftJson();
    @GET("shopByCategory")
    Observable<RightBean>rightJson(@Query("category") String category);
}
