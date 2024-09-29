package com.example.modul6

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    // Fungsi onCreate dipanggil ketika aktivitas dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second) // Menggunakan layout 'activity_second'

        // Ambil data yang dikirim melalui Intent dari MainActivity
        val tujuan = intent.getStringExtra("TUJUAN") // Mengambil data tujuan kereta
        val tanggal = intent.getStringExtra("TANGGAL") // Mengambil data tanggal keberangkatan
        val waktu = intent.getStringExtra("WAKTU") // Mengambil data waktu keberangkatan
        val nama = intent.getStringExtra("NAMA") // Mengambil data nama pemesan

        // Inisialisasi TextView dari layout untuk menampilkan data yang diterima
        val tujuanTextView = findViewById<TextView>(R.id.regisTujuan) // TextView untuk tujuan kereta
        val tanggalTextView = findViewById<TextView>(R.id.regisTanggal) // TextView untuk tanggal keberangkatan
        val waktuTextView = findViewById<TextView>(R.id.regisJam) // TextView untuk waktu keberangkatan
        val namaTextView = findViewById<TextView>(R.id.regisNama) // TextView untuk nama pemesan

        // Set text pada masing-masing TextView dengan data yang diterima dari Intent
        tujuanTextView.text = "Tujuan: $tujuan"
        tanggalTextView.text = "Tanggal: $tanggal"
        waktuTextView.text = "Waktu: $waktu"
        namaTextView.text = "Nama: $nama" // Menampilkan nama pemesan
    }
}
