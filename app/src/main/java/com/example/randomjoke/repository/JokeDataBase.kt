package com.example.randomjoke.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.randomjoke.model.Joke


@Database(entities = [Joke::class], version = 2)
abstract class JokeDataBase():RoomDatabase() {

    abstract fun jokeDao(): JokeDAO


    companion object {

        private lateinit var instance: JokeDataBase

        fun getDataBase(context: Context): JokeDataBase {

            if (!::instance.isInitialized) {
                synchronized(JokeDataBase::class) {
                    instance = Room.databaseBuilder(context, JokeDataBase::class.java, "jokedb")
                        .allowMainThreadQueries()
                        .addMigrations(MIGRATION_1_2)
                        .build()
                }

            }

            return instance

        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Exemplo: nenhuma alteração
            }

        }

    }

}

