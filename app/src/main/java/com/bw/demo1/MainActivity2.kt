package com.bw.demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.demo1.databinding.ActivityMain2Binding
import com.bw.longfromvideo.LongVideoFragment

class MainActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var fragment:LongVideoFragment = ARouter.getInstance().build("/longfromvideo/LongVideoFragment").navigation() as LongVideoFragment

        supportFragmentManager.beginTransaction().replace(R.id.cs_activity,fragment).commit()
    }
}