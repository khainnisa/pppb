package com.example.tugaspertemuan8

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // Metode onCreate dipanggil saat activity pertama kali dibuat.
        // setContentView digunakan untuk menampilkan layout activity_home.

        // Ambil data username dari Intent
        val username = intent.getStringExtra("USERNAME")
        // Ambil nilai dari extra Intent dengan key "USERNAME", yang berisi string username.
        // Nilai ini akan diterima dari activity yang memulai HomeActivity.

        // Tampilkan username di TextView atau gunakan untuk keperluan lain
        val mainUsn = findViewById<TextView>(R.id.main_usn)
        // Cari TextView dengan ID main_usn di layout activity_home.

        mainUsn.text = "$username!"
        // Setel teks dari TextView menjadi nilai username yang diterima dari Intent.
        // Misalnya, jika username adalah "John", maka teks akan menjadi "John!".
    }
}
