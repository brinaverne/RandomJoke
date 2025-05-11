package com.example.randomjoke.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.randomjoke.model.Joke


@Database(entities = [Joke::class], version = 1)
abstract class JokeDataBase():RoomDatabase() {

    abstract fun jokeDao(): JokeDAO

    companion object{

        private lateinit var instance: JokeDataBase

        fun getDataBase(context: Context):JokeDataBase{

            if (!::instance.isInitialized){
                synchronized(JokeDataBase::class){
                    instance = Room.databaseBuilder(context, JokeDataBase::class.java, "jokedb")
                        .allowMainThreadQueries()
                        .build()
                }

            }

            return instance

        }

    }

}