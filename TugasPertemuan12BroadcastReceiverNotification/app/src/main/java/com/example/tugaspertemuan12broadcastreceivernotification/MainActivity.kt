package com.example.tugaspertemuan12broadcastreceivernotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.tugaspertemuan12broadcastreceivernotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Counters
    private var likeCount = 0
    private var dislikeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

        binding.btnNotif.setOnClickListener {
            sendNotification()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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

    private fun sendNotification() {
        // Intent dan PendingIntent untuk tombol "Suka"
        val intentLike = Intent(this, NotifReceiver::class.java).apply {
            action = "ACTION_LIKE"
        }
        val pendingLike = PendingIntent.getBroadcast(
            this, 0, intentLike, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Intent dan PendingIntent untuk tombol "Tidak Suka"
        val intentDislike = Intent(this, NotifReceiver::class.java).apply {
            action = "ACTION_DISLIKE"
        }
        val pendingDislike = PendingIntent.getBroadcast(
            this, 1, intentDislike, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Menggunakan gambar Bitmap
        val bigPicture: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.sakura)

        // Membuat notifikasi
        val notification = NotificationCompat.Builder(this, "TEST_NOTIF")
            .setSmallIcon(R.drawable.ic_notif)
            .setContentTitle("Counter Beluga Cat")
            .setContentText("Counter Beluga Cat")
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bigPicture))
            .addAction(R.drawable.ic_like, "Suka", pendingLike)
            .addAction(R.drawable.ic_dislike, "Tidak Suka", pendingDislike)
            .setAutoCancel(true)
            .build()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification)
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val action = intent?.action
        when (action) {
            "ACTION_LIKE" -> {
                likeCount++
                updateCounters()
            }
            "ACTION_DISLIKE" -> {
                dislikeCount++
                updateCounters()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        // Mendaftar receiver untuk menerima broadcast dari NotifReceiver
        val filter = IntentFilter("UPDATE_COUNTERS")
        LocalBroadcastManager.getInstance(this).registerReceiver(updateReceiver, filter)

        loadCounters()
    }

    override fun onPause() {
        super.onPause()
        // Mencopot pendaftaran receiver saat activity tidak terlihat
        LocalBroadcastManager.getInstance(this).unregisterReceiver(updateReceiver)
    }

    // Membuat receiver untuk menerima broadcast dan memperbarui UI
    private val updateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.getStringExtra("action")
            if (action == "like") {
                likeCount++
            } else if (action == "dislike") {
                dislikeCount++
            }
            updateCounters() // Memperbarui tampilan UI dengan nilai terbaru
        }
    }


    private fun loadCounters() {
        val sharedPref = getSharedPreferences("counter_prefs", Context.MODE_PRIVATE)
        likeCount = sharedPref.getInt("like_count", 0)
        dislikeCount = sharedPref.getInt("dislike_count", 0)
        updateCounters()
    }


    private fun updateCounters() {
        // Simpan nilai counter di SharedPreferences setelah memperbarui UI
        val sharedPref = getSharedPreferences("counter_prefs", Context.MODE_PRIVATE)
        sharedPref.edit().putInt("like_count", likeCount).apply()
        sharedPref.edit().putInt("dislike_count", dislikeCount).apply()

        binding.txtLike.text = "Counter Suka: $likeCount"
        binding.txtDislike.text = "Counter Tidak Suka: $dislikeCount"
    }


}
