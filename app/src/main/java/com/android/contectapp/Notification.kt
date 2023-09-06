package com.android.contectapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(base: Context?):ContextWrapper(base) {

    private val channelID="channelID"
    private val channelNM="channelNM"
    init{
        //안드버전이 오레오 이상이면 실행
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            createChannel()
    }
    // 채널 생성
    private fun createChannel(){
        var channel=NotificationChannel(channelID,channelNM,NotificationManager.IMPORTANCE_DEFAULT)
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor= Color.BLUE
        channel.lockscreenVisibility=Notification.VISIBILITY_PRIVATE
        getManager().createNotificationChannel(channel)


    }
    //알람매니저생성
    fun getManager():NotificationManager{
        return getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }
    //notification 설정
    fun createNotification(title: String, content: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_launcher_background)
    }

    fun showNotification(notificationId: Int, notificationBuilder: NotificationCompat.Builder) {
        val notificationManager = getManager()
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    fun cancelNotification(notificationId: Int) {
        val notificationManager = getManager()
        notificationManager.cancel(notificationId)
    }

}