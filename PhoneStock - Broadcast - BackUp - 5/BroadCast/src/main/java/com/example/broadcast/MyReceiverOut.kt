package com.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiverOut : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //        Toast.makeText(context, "Phone: ${intent.getStringExtra("phone_name")} was added.", Toast.LENGTH_SHORT).show()
        val serviceIntent = Intent(context, MyServiceOut::class.java)
        serviceIntent.putExtra("phone_name",intent.getStringExtra("phone_name"))
        serviceIntent.putExtra("id",intent.getIntExtra("id",0))
        context.startService(serviceIntent)
    }
}