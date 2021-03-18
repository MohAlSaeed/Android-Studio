package com.example.stockfirebase

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.widget.ImageViewCompat
import java.util.*
import kotlin.properties.Delegates


/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {
    lateinit var sp: SharedPreferences
    lateinit var edt: SharedPreferences.Editor

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    // since (AppWidgetProvider) extends a (BroadcastReceiver) that means it has the (onReceive) method to be implemented thats wy we added it here below
    override fun onReceive(context: Context, intent: Intent) {
        sp = context.getSharedPreferences("Flag" , Context.MODE_PRIVATE)
        edt = sp.edit()
        val views =  RemoteViews(context.packageName, R.layout.new_app_widget)
        val appWidgetManager = AppWidgetManager.getInstance(context)
        var appWidgetId = intent.getIntExtra("appWidgetId", 0)
        super.onReceive(context, intent)
        // if here to check which bt we pressed
        if (intent.action == "com.example.stockfirebase.ACTION"){
            val f = sp.getInt("flag",1)
            if (f == 1){
                views.setImageViewResource(R.id.image1, R.drawable.myimage1)
                appWidgetManager.updateAppWidget(appWidgetId,views)
                updateAppWidget(context,appWidgetManager,appWidgetId)
                edt.putInt("flag",2)
                edt.apply()
            } else {
                views.setImageViewResource(R.id.image1, R.drawable.myimage2)
                appWidgetManager.updateAppWidget(appWidgetId,views)
                edt.putInt("flag",1)
                edt.apply()
            }
        }
    }
}

internal fun updateAppWidget(context: Context,appWidgetManager: AppWidgetManager,appWidgetId: Int) {

    var requestcode = 0
    val widgetText = context.getString(R.string.appwidget_text)
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    val intentWWW =Intent(Intent.ACTION_VIEW)
    intentWWW.data = Uri.parse("https://www.google.com")
    val pendingIntentwww = PendingIntent.getActivities(
        context,
        requestcode++,
        arrayOf(intentWWW),
        PendingIntent.FLAG_UPDATE_CURRENT,
    )
    views.setOnClickPendingIntent(R.id.bt1_w, pendingIntentwww)


    val intentAction=Intent("com.example.stockfirebase.ACTION")
    intentAction.component = ComponentName(context,NewAppWidget::class.java)
    intentAction.putExtra("img", R.drawable.myimage1)
    intentAction.putExtra("appWidgetId", appWidgetId)
    val pendingIntentAction = PendingIntent.getBroadcast(
        context,
        requestcode++,
        intentAction,
        PendingIntent.FLAG_UPDATE_CURRENT,
    )
    views.setOnClickPendingIntent(R.id.bt2_w, pendingIntentAction)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}