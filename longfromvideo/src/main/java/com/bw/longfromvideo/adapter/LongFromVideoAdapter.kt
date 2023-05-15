package com.bw.longfromvideo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bw.longfromvideo.LongVideoData
import com.bw.longfromvideo.databinding.ItemLongfromvideoBinding

class LongFromVideoAdapter(val context: Context?, var data:List<LongVideoData>, val clickListener: ClickListener):RecyclerView.Adapter<LongFromVideoAdapter.MyViewHolder>() {
    lateinit var binding:ItemLongfromvideoBinding
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var title = binding.itemLFVTitle
        var img = binding.itemLFVImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemLongfromvideoBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var video = data.get(position)
        holder.title.text = video.title
        if (context != null) {
            Glide.with(context).load(video.videomainimag).apply(
                RequestOptions().transform(
                    CenterCrop(),
                    RoundedCorners(20)
                )).into(holder.img)
        }

        holder.itemView.setOnClickListener {
            clickListener.onClick(position,video)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ClickListener{
        fun onClick(position: Int,entity: LongVideoData)
    }
}