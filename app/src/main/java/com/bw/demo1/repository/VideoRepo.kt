package com.bw.demo1.repository

import com.bw.demo1.entity.ResponseData
import com.bw.demo1.entity.VideoData
import com.bw.demo1.model.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepo {
    val apiService = RetrofitManager.getApiService()

    suspend fun getSimpleVideoByChannelId(id:String,page:Int,pageSize:Int):ResponseData<List<VideoData>>{
        return withContext(Dispatchers.IO){
            var list = apiService.getSimpleVideoByChannelId(id, page, pageSize)
            list
        }
    }
}