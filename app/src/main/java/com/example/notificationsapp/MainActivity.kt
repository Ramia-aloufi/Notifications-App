package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var builder: Notification.Builder
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    lateinit var notificationManager: NotificationManager
    lateinit var pendingIntent: PendingIntent
    lateinit var btn:Button
    lateinit var et:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.btn)
        et = findViewById(R.id.editTextTextPersonName)

        btn.setOnClickListener {
            var notitext = et.text.toString()
            notification(notitext)
        }





    }

    fun notification(text: String){
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, NotificationActivity::class.java)
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel(channelId,description,
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setContentTitle("My Notification")
                .setContentText(text)
        }
        else {
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setContentTitle("My Notification")
                .setContentText("Hello")
        }
        notificationManager.notify(1234, builder.build())
    }
}

