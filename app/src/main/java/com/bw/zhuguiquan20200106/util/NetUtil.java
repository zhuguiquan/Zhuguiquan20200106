package com.bw.zhuguiquan20200106.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.zhuguiquan20200106.Api;
import com.bw.zhuguiquan20200106.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * DateTime:2020/1/6 0006
 * author:朱贵全(Administrator)
 * function:
 */
public class NetUtil {

    private final Api api;
//单例模式
    private static final class MyHolder{
        private static final NetUtil NET_UTIL=new NetUtil();
    }

    public static NetUtil getInstance() {
        return MyHolder.NET_UTIL;
    }

    private NetUtil() {
        //创建拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //设置拦截器
                .addInterceptor(httpLoggingInterceptor)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://blog.zhaoliang5156.cn/baweiapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }
    //判断有网无网
public boolean hasNet(Context context){
    ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
    if (activeNetworkInfo != null&&activeNetworkInfo.isAvailable()) {
        return true;
    }else{
        return false;
    }
}
public void getPhoto(String http, ImageView imageView){
        //加载图片
    Glide.with(imageView)
            .load(http)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher_round)
           .into(imageView);
}

    public Api getApi() {
        return api;
    }
}
