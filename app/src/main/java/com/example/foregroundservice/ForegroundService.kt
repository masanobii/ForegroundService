package com.example.foregroundservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ForegroundService : Service() {

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "com.example.sample_notification_channel_id"
        const val NOTIFICATION_CHANNEL_NAME = "sample_notification_channel"
        const val NOTIFICATION_CHANNEL_DESCRIPTION = "This is the sample notification!"
    }

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
        Log.d("debug", "onBind()")
    }

    override fun onCreate() {
        super.onCreate()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    //バックグラウンドで行う処理をここに記述　Int型の値を返却する
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("debug", "onStartCommand()")

        val channelId = NOTIFICATION_CHANNEL_ID
        val channelName = NOTIFICATION_CHANNEL_NAME
        val channelDescription = NOTIFICATION_CHANNEL_DESCRIPTION

        //Android 8.0 以上ではアプリの通知チャンネルを登録することが必要。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("debug", "ver 8.0 以上")

            val importance = NotificationManager.IMPORTANCE_DEFAULT //---*1
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        //通知をシステムに登録しています。
        val builder = NotificationCompat.Builder(this, channelId).apply {
            setSmallIcon(R.drawable.ic_launcher_background) //---*2
            setContentTitle("通知のタイトル")
            setContentText("通知するメッセージ")
            priority = NotificationCompat.PRIORITY_DEFAULT //---*3
        }
        val id1 = 0 //---*4
        NotificationManagerCompat.from(this).notify(id1, builder.build())

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
