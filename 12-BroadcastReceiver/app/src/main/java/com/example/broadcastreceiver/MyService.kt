package com.example.broadcastreceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyService : Service() {
//    private lateinit var channelId: String
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        var channelId = getString(R.string.channel_default)
        channelId = createChannel()
        //TODO create noti here

        val listActivity = Intent()
    listActivity.component = ComponentName("com.example.broadcastreceiver", "com.example.broadcastreceiver.Stock")
        val pendingIntent = PendingIntent.getActivities(
            this,
            intent.getIntExtra("id", 0),
            arrayOf(listActivity),
            PendingIntent.FLAG_ONE_SHOT
        )
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Phone was Added:")
            .setContentText(intent.getStringExtra("phone_name"))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
    NotificationManagerCompat.from(this).notify(intent.getIntExtra("id", 0), notification)


        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private fun createChannel(): String{
        val channel = NotificationChannel(
            getString(R.string.channel_id),
            getString(R.string.channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )

        NotificationManagerCompat.from(this).createNotificationChannel(channel)
        return channel.id
    }
}