package com.example.phonestock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class DeleteAllReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
//        Toast.makeText(context, "${intent.getStringExtra("phone_Remove")} .", Toast.LENGTH_SHORT).show()
        val serviceIntent = Intent(context, MyService2::class.java)
        serviceIntent.putExtra("phone_Remove",intent.getStringExtra("phone_Remove"))
        serviceIntent.putExtra("id",intent.getIntExtra("id",0))
        context.startService(serviceIntent)
    }
}