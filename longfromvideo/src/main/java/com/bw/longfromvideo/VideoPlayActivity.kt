package com.bw.longfromvideo

import android.R.attr.text
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.longfromvideo.adapter.MyPagerAdapter
import com.bw.longfromvideo.databinding.ActivityVideoPlayBinding
import com.bw.longfromvideo.view.BlankFragment1
import com.bw.longfromvideo.view.BlankFragment2
import master.flame.danmaku.controller.DrawHandler
import master.flame.danmaku.danmaku.model.BaseDanmaku
import master.flame.danmaku.danmaku.model.DanmakuTimer
import master.flame.danmaku.danmaku.model.IDanmakus
import master.flame.danmaku.danmaku.model.IDisplayer
import master.flame.danmaku.danmaku.model.android.DanmakuContext
import master.flame.danmaku.danmaku.model.android.Danmakus
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser


@Route(path = "/longfromvideo/VideoPlayActivity")
class VideoPlayActivity : AppCompatActivity() {
    lateinit var binding:ActivityVideoPlayBinding
    lateinit var myPagerAdapter: MyPagerAdapter
    var dContext = DanmakuContext.create()
    @Autowired
    lateinit var lVideos:LongVideoData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ARouter.getInstance().inject(this)
        binding.lVideoPlayer.setUp(lVideos.videopath,false," ")

        val fragment1:BlankFragment1 = BlankFragment1()
        val fragment2:BlankFragment2 = BlankFragment2()
        val list = listOf(fragment1,fragment2)
        val text_title:List<String> = listOf("简介","评论")

        myPagerAdapter = MyPagerAdapter(supportFragmentManager,list,text_title)
        binding.lVp.adapter = myPagerAdapter
        binding.lTab.setupWithViewPager(binding.lVp)


        var bd:Bundle = Bundle()
        bd.putString("title",lVideos.title.toString())
        bd.putString("jj",lVideos.description.toString())
        fragment1.arguments = bd

        initDanMu()

        binding.lBtDanmu.setOnClickListener {
            var string = binding.lEdDanmu.text.toString()
            addDanmu(string,true);
        }
    }

    private fun addDanmu(text:String,isSelf:Boolean) {
        val danmaku: BaseDanmaku =
            dContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL)
        if (danmaku == null || binding.lVideoDanmu == null) {
            return
        }

        danmaku.text = text
        danmaku.padding = 5
        danmaku.priority = 1
        danmaku.isLive = false
        danmaku.time = binding.lVideoDanmu.getCurrentTime() + 1200
        danmaku.textSize = 40f
        danmaku.textColor = Color.WHITE
        danmaku.textShadowColor = Color.BLACK
        // danmaku.underlineColor = Color.GREEN;
        // danmaku.underlineColor = Color.GREEN;
        if (isSelf) {
            danmaku.borderColor = Color.GREEN
        }
        binding.lVideoDanmu.addDanmaku(danmaku)
    }

    private fun initDanMu() {
        // 设置最大显示行数
        // 设置最大显示行数
        val maxLinesPair = HashMap<Int, Int>()
        maxLinesPair[BaseDanmaku.TYPE_SCROLL_RL] = 5 // 滚动弹幕最大显示5行

        // 设置是否禁止重叠
        // 设置是否禁止重叠
        val overlappingEnablePair = HashMap<Int, Boolean>()
        overlappingEnablePair[BaseDanmaku.TYPE_SCROLL_RL] = true
        overlappingEnablePair[BaseDanmaku.TYPE_FIX_TOP] = true

        dContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3F)
            .setDuplicateMergingEnabled(false)
            .setScrollSpeedFactor(1.2f)
            .setScaleTextSize(1.2f)
            .setMaximumLines(maxLinesPair)
            .preventOverlapping(overlappingEnablePair)
            .setDanmakuMargin(40)

        val parser: BaseDanmakuParser = object : BaseDanmakuParser() {
            override fun parse(): IDanmakus {
                return Danmakus()
            }
        }

        binding.lVideoDanmu.setCallback(object : DrawHandler.Callback {
            override fun updateTimer(timer: DanmakuTimer) {}
            override fun drawingFinished() {}
            override fun danmakuShown(danmaku: BaseDanmaku) {}
            override fun prepared() {
                binding.lVideoDanmu.start()
            }
        })

        binding.lVideoDanmu.prepare(parser, dContext)
//            dv_live.showFPS(true);
        //            dv_live.showFPS(true);
        binding.lVideoDanmu.enableDanmakuDrawingCache(true)
    }
}