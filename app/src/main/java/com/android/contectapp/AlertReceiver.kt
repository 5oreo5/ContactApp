package com.android.contectapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class AlertReceiver:BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
       var notificationHepler:NotificationHelper= NotificationHelper(context)
        var nb:NotificationCompat.Builder=notificationHepler.getchannelNotofication()
        notificationHepler.getManager().notify(1,nb.build())
    }

}