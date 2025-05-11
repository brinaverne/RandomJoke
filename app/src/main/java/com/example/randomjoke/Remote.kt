package com.example.randomjoke

import android.util.Log
import com.example.randomjoke.model.Joke

class Remote {

    val apiBaseUrl = "https://official-joke-api.appspot.com"

    fun getRandomJoke(): Joke? {
        var response: Joke?
        var url = "$apiBaseUrl/random_joke"

        try {
            val apiResponse = HttpHelper().getJoke(url)
            response = apiResponse

        }catch (e:Exception){
            response = null
            Log.e("deu merda - random joke", e.message, e)
        }

        return response

    }

    fun getJokeByCategory(category: Int?): Joke?{
        var response: Joke?
        var url = ""

        when (category){
            1 -> url = "$apiBaseUrl/jokes/general/random"
            2 -> url = "$apiBaseUrl/jokes/programming/random"
        }


        try {
            val apiResponse = HttpHelper().getJokeByCategory(url)
            response = apiResponse

        }catch (e:Exception){
            response = null
            Log.e("deu merda - get joke by category", e.message, e)
        }

        return response
    }

}