package com.bw.longfromvideo.state

import com.bw.longfromvideo.LongVideoData

sealed class LongVideoUiState {
    data class Success(var list: List<LongVideoData>?): LongVideoUiState()
    data class Error(var ex:Throwable?): LongVideoUiState()
    data class Fail(var msg:String): LongVideoUiState()
    object Loading: LongVideoUiState()

}