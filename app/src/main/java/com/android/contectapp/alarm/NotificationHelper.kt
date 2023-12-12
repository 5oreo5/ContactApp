package com.android.contectapp.alarm

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.android.contectapp.R
import java.util.Calendar

class NotificationHelper(base: Context?) : ContextWrapper(base) {

    private val channelID = "channelID"
    private val channelNM = "channelNM"
    init {
        // 안드로이드 버전이 오레오 이상이면 실행
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannel()
    }

    // 채널 생성
    private fun createChannel() {
        var channel = NotificationChannel(
            channelID,
            channelNM,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor = Color.BLUE
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        getManager().createNotificationChannel(channel)
    }

    // 알람 매니저 생성
    fun getManager(): NotificationManager {
        return getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    // 알람 및 알림 예약
    fun scheduleAlarmAndNotification(
        title: String,
        content: String,
        delayMinutes: Int,
        notificationId: Int
    ) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // 예약 시간 계산
        val triggerTime = Calendar.getInstance()
        triggerTime.add(Calendar.MINUTE, delayMinutes)

        // 알림을 트리거할 Intent 생성
        val intent = Intent(this, AlertReceiver::class.java)
        intent.action = "SCHEDULED_NOTIFICATION" // "SCHEDULED_NOTIFICATION" 액션 추가
        intent.putExtra("title", title)
        intent.putExtra("content", content)

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            notificationId,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // 알람 예약
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            triggerTime.timeInMillis,
            pendingIntent
        )

        // 예약된 알림을 만들고 표시
        val notificationBuilder = createNotification(title, content)
        showNotification(notificationId, notificationBuilder)
    }

    // notification 설정
    fun createNotification(title: String, content: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_launcher_background)
    }
    // 알림 표시
    fun showNotification(notificationId: Int, notificationBuilder: NotificationCompat.Builder) {
        val notificationManager = getManager()
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
    // 알림 취소
    fun cancelNotification(notificationId: Int) {
        val notificationManager = getManager()
        notificationManager.cancel(notificationId)
    }
}