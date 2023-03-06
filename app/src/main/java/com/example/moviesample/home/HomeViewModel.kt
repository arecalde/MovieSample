package com.example.moviesample.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesample.model.Movie
import com.example.moviesample.network.MovieApi
import com.example.moviesample.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val fragmentName = "Home Fragment"

    private val _movies = MutableLiveData(mapOf<Int, Movie>())
    val movies: LiveData<Map<Int, Movie>> = _movies

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    init {
        viewModelScope.launch(Dispatchers.IO) { callMovies() }
    }
    suspend fun callMovies() {
        val apiService = RetrofitHelper.getInstance().create(MovieApi::class.java)
        val call = apiService.getMovies(
            mapOf("api_key" to "7bfe007798875393b05c5aa1ba26323e")
        )

        viewModelScope.launch(Dispatchers.Main) { _loading.value = true }
        val movies = call.body()?.results ?: return

        val movieMap = mutableMapOf<Int, Movie>()

        movies.forEach {
            val key = it.id ?: return@forEach
            movieMap[key] = it
        }

        _movies.postValue(movieMap)
        _loading.postValue(false)

    }
}