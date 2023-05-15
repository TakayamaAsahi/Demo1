package com.bw.day3_21_demo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object GlideUtils {

    @JvmStatic
    @BindingAdapter("android:url")
    fun setImage(imagerView:ImageView,url:String){
        Glide.with(imagerView).load(url).into(imagerView)
    }
}