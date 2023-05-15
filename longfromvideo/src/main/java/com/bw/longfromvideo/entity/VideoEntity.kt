package com.bw.longfromvideo

import java.io.Serializable

data class LongVideoData(
    var avatar_url: String,
    var channelid: String,
    var commentnum: Int,
    var ctime: String,
    var description: String,
    var group_id: String,
    var id: Int,
    var image_url: String,
    var item_id: String,
    var name: String,
    var playnum: Int,
    var preview_url: String,
    var publish_time: Any,
    var title: String,
    var userid: String,
    var verifycode: String,
    var videomainimag: String,
    var videopath: String
) : Serializable