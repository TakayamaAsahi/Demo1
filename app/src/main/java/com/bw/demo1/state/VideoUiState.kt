package com.bw.demo1.state

import com.bw.demo1.entity.VideoData

sealed class VideoUiState {
    data class Success(var list: List<VideoData>?):VideoUiState()
    data class Error(var ex:Throwable?):VideoUiState()
    data class Fail(var msg:String):VideoUiState()
    object Loading:VideoUiState()
}