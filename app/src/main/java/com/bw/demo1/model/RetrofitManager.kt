package com.bw.demo1.model

import android.text.TextUtils
import com.bw.demo1.App
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {
    var mApi:ApiService?=null

    fun getApiService():ApiService{
        if (mApi == null){
            synchronized(this){
                if (mApi == null){
                    val okHttpClient = buildOkHttpClient();
                    mApi = buildRetrofit("http://124.70.98.255:8083/",okHttpClient).create(ApiService::class.java)
                }
            }
        }
        return mApi!!
    }



    private fun buildOkHttpClient(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
//            .addInterceptor(object :Interceptor{
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val builder = chain.request().newBuilder()
//                    val token = ACache.get(App.context).getAsString("token")
//                    if (!TextUtils.isEmpty(token)){
//                        builder.addHeader("sn-token",token)
//                    }
//                    val request = builder.build()
//                    return chain.proceed(request)
//                }
//            })
            .connectTimeout(60,TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
    }

    private fun buildRetrofit(baseUrl:String,builder:OkHttpClient.Builder): Retrofit {
        val client = builder.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}