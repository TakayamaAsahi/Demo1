package com.bw.demo1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bw.demo1.databinding.ItemVideoBinding
import com.bw.demo1.entity.VideoData

class VideoAdapter(val context:Context,var data:List<VideoData>,val clickListener: ClickListener): RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {

    lateinit var binding:ItemVideoBinding
    inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
        var title = binding.videoTitle
        var img = binding.videoImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemVideoBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var video = data.get(position)
        holder.title.text = video.title
        Glide.with(context).load(video.videomainimag).apply(RequestOptions().transform(CenterCrop(),RoundedCorners(20))).into(holder.img)

        holder.itemView.setOnClickListener {
            clickListener.onClick(position,video)
        }
//        holder.img.setOnClickListener {
//            clickListener.onClick(position,video)
//        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ClickListener{
        fun onClick(position: Int,entity:VideoData)
    }
}