package com.example.randomjoke.repository

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.randomjoke.model.Joke

@Dao
interface JokeDAO {

    @Insert
    fun insert(joke:Joke)

    @Query("SELECT * FROM JOKE")
    fun selectAll(): MutableList<Joke>

    @Query("SELECT * FROM JOKE ORDER BY RANDOM() LIMIT 1")
    fun selectOne(): Joke


}