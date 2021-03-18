package com.example.stockfirebase

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.location.GeofencingEvent

class GeoReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val geoEvent = GeofencingEvent.fromIntent(intent)
        val receiverIntent = Intent(context, GeoService::class.java)
        if (geoEvent.geofenceTransition == 1){
            for (geofence in geoEvent.triggeringGeofences){
                receiverIntent.putExtra("name", intent.getStringExtra("name"))
//                receiverIntent.putExtra("rad", intent.getStringExtra("rad"))
//                receiverIntent.putExtra("address", intent.getStringExtra("address"))
                receiverIntent.putExtra("status", "entering")
                receiverIntent.putExtra("id", intent.getIntExtra("id", 0))
                context.startService(receiverIntent)
            }
        }else {
            for (geofence in geoEvent.triggeringGeofences){
                receiverIntent.putExtra("name", intent.getStringExtra("name"))
//                receiverIntent.putExtra("rad", intent.getStringExtra("rad"))
//                receiverIntent.putExtra("address", intent.getStringExtra("address"))
                receiverIntent.putExtra("status", "leaving")
                receiverIntent.putExtra("id", intent.getIntExtra("id", 0))
                context.startService(receiverIntent)
            }
        }
    }
}