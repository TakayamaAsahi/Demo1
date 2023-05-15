package com.bw.longfromvideo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.longfromvideo.adapter.LongFromVideoAdapter
import com.bw.longfromvideo.databinding.FragmentLongVideoBinding
import com.bw.longfromvideo.state.LongVideoUiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Route(path = "/longfromvideo/LongVideoFragment")
class LongVideoFragment : Fragment() {
    lateinit var binding:FragmentLongVideoBinding
    lateinit var vm:LongVideoViewModel
    lateinit var longVideoAdapter: LongFromVideoAdapter
    lateinit var list:List<LongVideoData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLongVideoBinding.inflate(layoutInflater)


        vm = ViewModelProvider(this).get(LongVideoViewModel::class.java)
        list = arrayListOf()
        longVideoAdapter = LongFromVideoAdapter(context,list,object :LongFromVideoAdapter.ClickListener{
            override fun onClick(position: Int, entity: LongVideoData) {
                ARouter.getInstance().build("/longfromvideo/VideoPlayActivity").withSerializable("lVideos",entity).navigation()
            }
        })
        binding.longRv.adapter = longVideoAdapter
        binding.longRv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        lifecycleScope.launch {
            vm.uiState.collect { uiState->
                when(uiState){
                    is LongVideoUiState.Loading->{
                        Log.i("====","加载中")
                    }
                    is LongVideoUiState.Success->{
                        updateUi(uiState)
                    }
                }
            }
        }

        lifecycleScope.launch {
            //发送请求列表意图
            vm.channel.send(LongVideoIntent.getVideos("94349546935",1,10))
        }
        return binding.root
    }

    private fun updateUi(uiState: LongVideoUiState.Success) {
        if (uiState.list==null){
            return
        }
        longVideoAdapter.data = uiState.list!!
        longVideoAdapter.notifyDataSetChanged()
    }
}