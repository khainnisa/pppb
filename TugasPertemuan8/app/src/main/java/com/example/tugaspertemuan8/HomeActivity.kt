package com.example.tugaspertemuan8

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Ambil data username dari Intent
        val username = intent.getStringExtra("USERNAME")

        // Tampilkan username di TextView atau gunakan untuk keperluan lain
        val mainUsn = findViewById<TextView>(R.id.main_usn)
        mainUsn.text = "$username!"
    }
}