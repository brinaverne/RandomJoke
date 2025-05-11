package com.example.randomjoke

import android.util.Log
import com.example.randomjoke.model.Joke
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request

class HttpHelper {

    fun getJoke(url: String): Joke {
        val client = OkHttpClient.Builder()
        val request = Request.Builder().url(url).build()
        val response = client.build().newCall(request).execute()
        val retorno = response.body()!!.string()

        Log.w("REQUEST-GET", String.format("GET-RETORNO: %s", retorno))
        return Gson().fromJson<Joke>(retorno, Joke::class.java)


    }

    fun getJokeByCategory(url: String): Joke?{
        val client = OkHttpClient.Builder()
        val request = Request.Builder().url(url).build()
        val response = client.build().newCall(request).execute()
        val retorno = response.body()!!.string()

        Log.w("REQUEST-GET", String.format("GET-RETORNO: %s", retorno))
        val jokeList: List<Joke> = Gson().fromJson(retorno, object : TypeToken<List<Joke>>() {}.type)
        return jokeList.firstOrNull()
    }

}