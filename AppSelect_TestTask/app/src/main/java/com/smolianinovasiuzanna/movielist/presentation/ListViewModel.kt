package com.smolianinovasiuzanna.movielist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smolianinovasiuzanna.movielist.data.MovieRepository
import com.smolianinovasiuzanna.movielist.data.NyResponse
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val movieListLiveData = MutableLiveData<NyResponse.MovieList>()
    private val errorLiveData = MutableLiveData<Throwable>()

    val movieList: LiveData<NyResponse.MovieList>
        get() = movieListLiveData

    val error: LiveData<Throwable>
        get() = errorLiveData

    fun loadList(){
        viewModelScope.launch {
            try{
                movieListLiveData.postValue(repository.showMovieFeed())
                } catch(t: Throwable){
                    errorLiveData.postValue(t)
                }
        }
    }

}