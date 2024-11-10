package com.example.tugaspertemuan10

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tugaspertemuan10.Movie
import com.example.tugaspertemuan10.MovieAdapter
import com.example.tugaspertemuan10.R
import com.example.tugaspertemuan10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi adapter
        movieAdapter = MovieAdapter(generateDummy()) { movie ->
            // Intent untuk membuka MovieDetail dan mengirim data movie
            val intent = Intent(this, MovieDetail::class.java).apply {
                putExtra("movieTitle", movie.title)
                putExtra("movieDuration", movie.duration)
                putExtra("movieRating", movie.rating)
                putExtra("movieImageResId", movie.imageResId)
            }
            startActivity(intent)
        }

        with(binding) {
            rvMovies.apply {
                adapter = movieAdapter
                layoutManager = GridLayoutManager(this@MainActivity, 2)
            }
        }
    }

    private fun generateDummy(): List<Movie> {
        return listOf(
            Movie(title = "Venom", duration = "1h 49m", rating = "R13+", imageResId = R.drawable.venom),
            Movie(title = "DOSA MUSYRIK", duration = "1h 32m", rating = "D17+", imageResId = R.drawable.dosamusyrik),
            Movie(title = "Inside Out 2", duration = "1h 36m", rating = "SU", imageResId = R.drawable.insideout),
            Movie(title = "Transformer One", duration = "1h 44m", rating = "R13+", imageResId = R.drawable.transformer),

            )
    }
}
