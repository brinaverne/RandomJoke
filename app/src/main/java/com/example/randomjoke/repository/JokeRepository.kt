package com.example.randomjoke.repository

import android.content.Context
import com.example.randomjoke.Remote
import com.example.randomjoke.model.Joke

class JokeRepository(context: Context) {

    var jokeDataBase = JokeDataBase.getDataBase(context).jokeDao()

    fun getJoke(): Joke? {
        return Remote().getRandomJoke()
    }

    fun getJokeByCategory(category: Int?): Joke?{
        return Remote().getJokeByCategory(category)
    }

    fun insertFavoriteJoke(joke:Joke){
        jokeDataBase.insert(joke)
    }

    fun getFavoriteJoke(): Joke{
        return jokeDataBase.selectOne()
    }

    fun selectAllFavoriteJokes(): MutableList<Joke>{
        return jokeDataBase.selectAll()
    }

    fun deleteOneFavoriteJoke(id: Int){
        jokeDataBase.deleteOne(id)
    }
}