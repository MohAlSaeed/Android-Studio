package com.example.phonestock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class UpdateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val serviceIntent = Intent(context, MyService3::class.java)
        serviceIntent.putExtra("phone_update",intent.getStringExtra("phone_update"))
        serviceIntent.putExtra("id",intent.getIntExtra("id",0))
        context.startService(serviceIntent)
    }
}