package com.bw.demo1.entity

data class ResponseData<T> (
    val code:Int,
    val msg:String,
    val data:T?
)