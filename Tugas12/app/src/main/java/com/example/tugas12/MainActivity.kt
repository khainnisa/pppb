package com.example.tugas12

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugas12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val updateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            updateCounters()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel() // Panggil fungsi ini
        checkNotificationPermission()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotif.setOnClickListener {
            sendNotification()
        }

        // Register BroadcastReceiver
        // Jangan gunakan registerReceiver di sini
//        registerReceiver(updateReceiver, IntentFilter("com.example.tugas12.UPDATE_COUNTER"))
//        updateCounters()
    }

    private fun sendNotification() {
        val intentSuka = Intent(this, NotifReceiver::class.java).apply {
            action = "ACTION_SUKA"
        }
        val pendingSuka = PendingIntent.getBroadcast(
            this, 0, intentSuka, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val intentTidakSuka = Intent(this, NotifReceiver::class.java).apply {
            action = "ACTION_TIDAK_SUKA"
        }
        val pendingTidakSuka = PendingIntent.getBroadcast(
            this, 1, intentTidakSuka, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, "TEST_NOTIF")
            .setSmallIcon(R.drawable.ic_notif)
            .setContentTitle("Counter Beluga Cat")
            .setContentText("Counter Beluga Cat")
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.sakura)))
            .addAction(R.drawable.ic_like, "Suka", pendingSuka)
            .addAction(R.drawable.ic_dislike, "Tidak Suka", pendingTidakSuka)
            .setAutoCancel(true)
            .build()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification)
        // Tambahkan Log
        Log.d("MainActivity", "Mengirim notifikasi...")
        manager.notify(1, notification)
    }

    private fun updateCounters() {
        binding.txtLike.text = "Counter Suka: ${CounterManager.getGanteng()}"
        binding.txtDislike.text = "Counter Tidak Suka: ${CounterManager.getJelek()}"


    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(updateReceiver)
    }


    private fun checkNotificationPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                Log.d("MainActivity", "Izin notifikasi belum diberikan.")
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
            } else {
                Log.d("MainActivity", "Izin notifikasi sudah diberikan.")
            }
        }
    }


    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "TEST_NOTIF",
                "Test Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel untuk notifikasi tes"
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

//    override fun onStart() {
//        super.onStart()
//        registerReceiver(updateReceiver, IntentFilter("UPDATE_COUNTER"))
//    }
//
//    override fun onStop() {
//        super.onStop()
//        unregisterReceiver(updateReceiver)
//    }


}