package com.android.contectapp

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class AlertReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // 알림 빌더 생성
        val notificationHelper = NotificationHelper(context)
        val notificationBuilder = notificationHelper.createNotification("알림", "알림 기간.")

        // 알림을 클릭 했을 때 실행될 인텐트 설정
        val targetIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, targetIntent, PendingIntent.FLAG_IMMUTABLE
        )

        // 알림에 인텐트 설정
        notificationBuilder.setContentIntent(pendingIntent)

        // 알림 표시
        notificationHelper.showNotification(1, notificationBuilder)
    }
}