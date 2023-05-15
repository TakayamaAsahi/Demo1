package com.bw.longfromvideo.entity

data class ResponseData<T> (
    val code:Int,
    val msg:String,
    val data:T?
)