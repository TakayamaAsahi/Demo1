package com.bw.longfromvideo.repository

import com.bw.longfromvideo.LongVideoData
import com.bw.longfromvideo.entity.ResponseData
import com.bw.longfromvideo.model.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LongVideoRepo {
    val apiService = RetrofitManager.getApiService()

    suspend fun getSimpleVideoByChannelId(id:String,page:Int,pageSize:Int): ResponseData<List<LongVideoData>> {
        return withContext(Dispatchers.IO){
            var list = apiService.getSimpleVideoByChannelId(id, page, pageSize)
            list
        }
    }
}