package com.example.tugaspertemuan10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugaspertemuan10.databinding.ItemMoviesBinding


typealias OnClickMovies = (Movie) -> Unit

class MovieAdapter(
    private val listMovies: List<Movie>,
    private val onClick: OnClickMovies
) : RecyclerView.Adapter<MovieAdapter.ItemMovieViewHolder>() {
    inner class ItemMovieViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            with(binding) {
                movieTitle.text = data.title
                movieDuration.text = data.duration
                movieRating.text = data.rating

                itemView.setOnClickListener {
                    onClick(data)
                }

                Glide.with(binding.root.context)
                    .load(data.imageResId)
                    .into(binding.movieImage) // Pastikan ini sesuai dengan ID ImageView di item layout
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }
}
