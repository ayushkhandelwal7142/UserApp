package com.akstudios.userapp.notiication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.akstudios.userapp.MainActivity
import com.akstudios.userapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage
import java.util.*

class FirebaseService: FirebaseMessagingService() {
    private val CHANNEL_ID = "channel_id"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val intent = Intent(this, MainActivity::class.java)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random().nextInt()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(manager)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val intent1 =
            PendingIntent.getActivities(this, 0, arrayOf(intent), PendingIntent.FLAG_ONE_SHOT)
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["message"])
            .setSmallIcon(R.drawable.avtar)
            .setAutoCancel(true)
            .setContentIntent(intent1)
            .build()
        manager.notify(notificationID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(manager: NotificationManager) {
        val channel =
            NotificationChannel(CHANNEL_ID, "channelName", NotificationManager.IMPORTANCE_HIGH)
        channel.description = "My description"
        channel.enableLights(true)
        channel.lightColor = Color.WHITE
        manager.createNotificationChannel(channel)
    }
}
