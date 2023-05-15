package com.bw.demo1.model

import com.bw.demo1.entity.ResponseData
import com.bw.demo1.entity.VideoData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/videosimple/getSimpleVideoByChannelId")
    suspend fun getSimpleVideoByChannelId(@Query("channelId")id:String,@Query("page")page:Int,@Query("pagesize")pageSize:Int):ResponseData<List<VideoData>>
}