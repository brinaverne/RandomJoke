package com.example.randomjoke.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomjoke.R
import com.example.randomjoke.model.Joke
import com.example.randomjoke.viewmodel.JokeViewModel


class FavoriteJokesActivity : AppCompatActivity() {

    val viewmodel: JokeViewModel by lazy {
        ViewModelProvider(this).get(JokeViewModel::class.java)
    }

    lateinit var recyclerFavoriteJoke: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_jokes)


        recyclerFavoriteJoke = findViewById(R.id.recyclerview_favorite_joke)
        recyclerFavoriteJoke.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerFavoriteJoke.adapter = JokeAdapter(listOf(), this@FavoriteJokesActivity)

        viewmodel.selectAllFavoriteJokes()

        viewmodel.listJoke.observe(this@FavoriteJokesActivity, {
            recyclerFavoriteJoke?.adapter = JokeAdapter(it, this@FavoriteJokesActivity)






        })


    }

}

public class JokeAdapter(jokeList: List<Joke?>, context: Context): RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    var lista = jokeList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val cardview = LayoutInflater.from(parent.context).inflate(R.layout.cardview_joke, parent, false)
        return JokeViewHolder(cardview)

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val objectJoke = lista[position]
        val setup = objectJoke?.setup
        val punchline = objectJoke?.punchline
        holder.textJoke.text = "$setup\n$punchline"
    }


    class JokeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textJoke: TextView = view.findViewById(R.id.text_cardview_joke)
    }


}