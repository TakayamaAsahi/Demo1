package com.bw.demo1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bw.demo1.repository.VideoRepo
import com.bw.demo1.state.VideoUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

sealed class VideoIntent{
    data class getVideos(val id:String,val page:Int,val pageSize:Int):VideoIntent()
    object getDetail:VideoIntent()
}

class VideoViewModel:ViewModel() {
    var videoRepo = VideoRepo()
    //获取一个channel
    val channel = Channel<VideoIntent>(Channel.UNLIMITED)
    private val _uiState = MutableStateFlow<VideoUiState>(VideoUiState.Loading)
    val uiState: StateFlow<VideoUiState>
        get() = _uiState

    init {
        handlerIntent()
    }
    //处理意图
    private fun handlerIntent() {
        viewModelScope.launch {
            //接收到意图,根据意图做操作
            channel.consumeAsFlow().collect {
                when(it){
                    //根据获取列表意图,去获取列表
                    is VideoIntent.getVideos->getVideos(it.id,it.page,it.pageSize)
                }
            }
        }
    }

    private fun getVideos(id: String, page: Int, pageSize: Int) {
        viewModelScope.launch {
            val data = videoRepo.getSimpleVideoByChannelId(id, page, pageSize)
            if (data.code == 0){
                _uiState.value = VideoUiState.Success(data?.data)
            }else{
                _uiState.value = VideoUiState.Fail(data.msg)
            }
        }
    }
}