package com.example.playmusic_relativelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log  // Pastikan Anda mengimpor Log
import androidx.core.content.ContextCompat
import android.widget.RelativeLayout

//MainActivity adalah kelas utama yang diperluas dari AppCompatActivity.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        Metode ini dipanggil ketika aktivitas pertama kali dibuat. Di dalamnya:
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainLayout = findViewById<RelativeLayout>(R.id.main)

        // Log untuk memastikan bahwa mainLayout ditemukan
        Log.d("MainActivity", "mainLayout: $mainLayout")

        // Mengatur background dengan gambar
        mainLayout?.setBackgroundResource(R.drawable.gradient)

    }
}

