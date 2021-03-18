package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PhoneReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
//        Toast.makeText(context,"Phone: ${intent.getStringExtra( "name")} was added.",
//        Toast.LENGTH_SHORT
//        ).show()
        val serviceIntent = Intent(context, MyService::class.java)
        serviceIntent.putExtra("phone_name", intent.getStringExtra("phone_name"))
        serviceIntent.putExtra("id", intent.getIntExtra("id", 0))
        context.startService(serviceIntent)
    }
}