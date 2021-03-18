package com.example.phonestock

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyService3 : Service() {

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        var channel_id = "channel_default"
        channel_id = createChannel()

        val listActivity = Intent()
        listActivity.component = ComponentName("com.example.phonestock","com.example.phonestock.Stock")
        val pendingIntent = PendingIntent.getActivity(
            this,
            intent.getIntExtra("id",0),
            listActivity,
            PendingIntent.FLAG_ONE_SHOT
        )
        val notification = NotificationCompat.Builder(this, channel_id)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Phone Record Updated: ")
            .setContentText(intent.getStringExtra("phone_update"))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).notify(intent.getIntExtra("id",0), notification)

        return super.onStartCommand(intent, flags, startId)
    }

    private fun createChannel(): String {
        val channel = NotificationChannel(
            "channel_phone",
            "phone_channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        NotificationManagerCompat.from(this).createNotificationChannel(channel)

        return channel.id
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}