package com.example.randomjoke.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.randomjoke.model.Joke
import com.example.randomjoke.repository.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class JokeViewModel(application: Application) : AndroidViewModel(application) {


    var repository = JokeRepository(application.applicationContext)
    var joke = MutableLiveData<Joke?>()
    var listJoke = MutableLiveData<List<Joke?>>()

    fun getJoke() = GlobalScope.launch(Dispatchers.IO){
        joke.postValue(repository.getJoke())
    }

    fun getJokeByCategory(category: Int?) = GlobalScope.launch(Dispatchers.IO){
        joke.postValue(repository.getJokeByCategory(category))
    }

    fun getFavoriteJoke(){
        joke.postValue(repository.getFavoriteJoke())
    }

    fun insertFavoriteJoke(joke:Joke){
        repository.insertFavoriteJoke(joke)
    }

    fun selectAllFavoriteJokes(){
        listJoke.postValue(repository.selectAllFavoriteJokes())
    }




}