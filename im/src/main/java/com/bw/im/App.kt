package com.bw.im

import android.app.Application
import android.content.Context
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.hyphenate.easeui.EaseIM

class App:Application() {
    companion object{
        @JvmField
        var context: Context?=null
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        val options:EMOptions = EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
//初始化
        EMClient.getInstance().init(context, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

        //EaseIM 初始化
        if(EaseIM.getInstance().init(context, options)){
            //在做打包混淆时，关闭 debug 模式，避免消耗不必要的资源
            EMClient.getInstance().setDebugMode(true);
            //EaseIM 初始化成功之后再调用注册消息监听的代码 ...
        }

    }
}