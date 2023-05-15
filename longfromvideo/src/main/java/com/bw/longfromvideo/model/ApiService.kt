package com.bw.longfromvideo.model

import com.bw.longfromvideo.LongVideoData
import com.bw.longfromvideo.entity.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/videosimple/getSimpleVideoByChannelId")
    suspend fun getSimpleVideoByChannelId(@Query("channelId")id:String, @Query("page")page:Int, @Query("pagesize")pageSize:Int): ResponseData<List<LongVideoData>>
}