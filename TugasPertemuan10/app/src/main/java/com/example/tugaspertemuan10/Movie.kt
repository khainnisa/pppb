package com.example.tugaspertemuan10

data class Movie(
    val title: String="",      // Menyimpan judul film (e.g., "Venom: The Last Dance")
    val duration: String="",   // Menyimpan durasi film (e.g., "1 h 49 m")
    val rating: String="",     // Menyimpan rating usia (e.g., "R13+")
    val imageResId: Int  // Menyimpan URL gambar poster film
)
