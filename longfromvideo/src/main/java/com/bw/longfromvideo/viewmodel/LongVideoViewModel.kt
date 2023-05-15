package com.bw.longfromvideo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bw.longfromvideo.repository.LongVideoRepo
import com.bw.longfromvideo.state.LongVideoUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

sealed class LongVideoIntent{
    data class getVideos(val id:String,val page:Int,val pageSize:Int):LongVideoIntent()
    object getDetail:LongVideoIntent()
}

class LongVideoViewModel:ViewModel() {
    var longVideoRepo = LongVideoRepo()
    //获取一个channel
    val channel = Channel<LongVideoIntent>(Channel.UNLIMITED)
    private val _uiState = MutableStateFlow<LongVideoUiState>(LongVideoUiState.Loading)
    val uiState: StateFlow<LongVideoUiState>
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
                    is LongVideoIntent.getVideos->getVideos(it.id,it.page,it.pageSize)
                }
            }
        }
    }

    private fun getVideos(id: String, page: Int, pageSize: Int) {
        viewModelScope.launch {
            val data = longVideoRepo.getSimpleVideoByChannelId(id, page, pageSize)
            if (data.code == 0){
                _uiState.value = LongVideoUiState.Success(data?.data)
            }else{
                _uiState.value = LongVideoUiState.Fail(data.msg)
            }
        }
    }

}