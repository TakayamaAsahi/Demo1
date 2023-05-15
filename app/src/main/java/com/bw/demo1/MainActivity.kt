package com.bw.demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.demo1.adapter.VideoAdapter
import com.bw.demo1.databinding.ActivityMainBinding
import com.bw.demo1.entity.VideoData
import com.bw.demo1.state.VideoUiState
import com.bw.demo1.viewmodel.VideoIntent
import com.bw.demo1.viewmodel.VideoViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var vm:VideoViewModel
    lateinit var videoAdapter: VideoAdapter
    lateinit var list:List<VideoData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(VideoViewModel::class.java)

        list = arrayListOf()
        videoAdapter = VideoAdapter(this,list,object :VideoAdapter.ClickListener{
            override fun onClick(position: Int, entity: VideoData) {
//                ARouter.getInstance().build("/longfromvideo/MainActivity").navigation()
            }

        })
        binding.rv1.adapter = videoAdapter
        binding.rv1.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            vm.uiState.collect { uiState->
                when(uiState){
                    is VideoUiState.Loading->{
                        Log.i("====","加载中")
                    }
                    is VideoUiState.Success->{
                        updateUi(uiState)
                    }
                }
            }
        }

        lifecycleScope.launch {
            //发送请求列表意图
            vm.channel.send(VideoIntent.getVideos("94349546935",1,10))
        }
    }

    private fun updateUi(uiState: VideoUiState.Success) {
        if (uiState.list==null){
            return
        }
        videoAdapter.data = uiState.list!!
        videoAdapter.notifyDataSetChanged()
    }
}