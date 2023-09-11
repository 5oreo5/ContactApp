package com.android.contectapp

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class AlertReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // 알림 빌더 생성
        val notificationHelper = NotificationHelper(context)

        // 기본값 설정
        val title = intent?.getStringExtra("title") ?: "제목"
        val content = intent?.getStringExtra("content") ?: "내용"

        if (intent?.action == "SCHEDULED_NOTIFICATION") {
            notificationHelper.showNotification(1, notificationHelper.createNotification(title, content))
        }

        val targetIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, targetIntent, PendingIntent.FLAG_IMMUTABLE
        )

        notificationHelper.showNotification(1, notificationHelper.createNotification(title, content).setContentIntent(pendingIntent))
    }
}