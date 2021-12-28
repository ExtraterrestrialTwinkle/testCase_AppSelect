package com.smolianinovasiuzanna.movielist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smolianinovasiuzanna.movielist.R
import com.smolianinovasiuzanna.movielist.data.NyResponse
import com.smolianinovasiuzanna.movielist.databinding.ItemMovieBinding

class MovieAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(this, MovieCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as MovieHolder){
            holder.bind(differ.currentList[position])
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun setMovieList(newList: List<NyResponse.Movie>){
        differ.submitList(newList)
    }

    class MovieHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(movie: NyResponse.Movie){
            binding.titleTextView.text = movie.title
            binding.descriptionTextView.text = movie.description

            Glide.with(itemView)
                .load(movie.preview.previewLink)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_error)
                .into(binding.previewImageView)
        }
    }

    class MovieCallback: DiffUtil.ItemCallback<NyResponse.Movie>(){
        override fun areItemsTheSame(oldItem: NyResponse.Movie, newItem: NyResponse.Movie): Boolean {
            return newItem.title == oldItem.title
        }

        override fun areContentsTheSame(oldItem: NyResponse.Movie, newItem: NyResponse.Movie): Boolean {
            return newItem == oldItem
        }
    }
}