package com.example.tugaspertemuan10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tugaspertemuan10.databinding.ActivityMovieDetailBinding

class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tangkap data dari Intent
        val movieTitle = intent.getStringExtra("movieTitle")
        val movieDuration = intent.getStringExtra("movieDuration")
        val movieRating = intent.getStringExtra("movieRating")
        val movieImageResId = intent.getIntExtra("movieImageResId", 0) // Ambil imageResId sebagai Int

        // Set data ke tampilan
        with(binding) {
            textTitle.text = movieTitle
            textDuration.text = movieDuration
            textRating.text = movieRating
            Glide.with(this@MovieDetail)
                .load(movieImageResId)
                .into(imagePoster) // Pastikan ini sesuai dengan ID ImageView di layout `activity_movie_detail.xml`

            // Fungsi icon back untuk kembali ke halaman sebelumnya
            iconBack.setOnClickListener {
                finish() // Menutup MovieDetail Activity dan kembali ke halaman sebelumnya
            }
        }
    }
}
