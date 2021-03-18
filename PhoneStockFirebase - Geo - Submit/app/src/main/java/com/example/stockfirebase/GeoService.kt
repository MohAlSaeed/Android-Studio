package com.example.stockfirebase

import android.app.*
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class GeoService : Service() {


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        var channel_id = "channel_default"
        channel_id = createChannel()

        val serviceIntent = Intent()
        serviceIntent.component = ComponentName("com.example.stockfirebase", "com.example.stockfirebase.MapsActivity")
        val pendingIntent = PendingIntent.getActivity(
            this,
            intent.getIntExtra("id",0),
            serviceIntent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val notification = NotificationCompat.Builder(this,channel_id)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("${intent.getStringExtra("status")} :")
            .setContentText("${intent.getStringExtra("name")} ")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).notify(intent.getIntExtra("id",0), notification)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createChannel(): String {
        val channel = NotificationChannel(
            "store_place",
            "store_channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        NotificationManagerCompat.from(this).createNotificationChannel(channel)
        return channel.id
    }


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}