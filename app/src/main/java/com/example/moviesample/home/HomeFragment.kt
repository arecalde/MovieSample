package com.example.moviesample.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesample.R
import com.example.moviesample.databinding.FragmentHomeBinding
import com.example.moviesample.databinding.MovieItemBinding
import com.example.moviesample.model.Movie

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val recyclerView = binding.movieRecyclerView

        viewModel.movies.observe(viewLifecycleOwner) { map ->
            recyclerView.adapter = MovieItemAdapter(map.toList().map { it.second }, viewLifecycleOwner)
        }
        return binding.root
    }
}

class MyViewHolder(private val binding: MovieItemBinding, private val lifecycleOwner: LifecycleOwner) : RecyclerView.ViewHolder(binding.getRoot()) {

    fun bind(item: Movie) {
        binding.lifecycleOwner = lifecycleOwner
        binding.movie = item
        binding.executePendingBindings()
    }
}

class MovieItemAdapter(private val items: List<Movie>, private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemBinding = DataBindingUtil.inflate<MovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )
        return MyViewHolder(itemBinding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: Movie = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size
}