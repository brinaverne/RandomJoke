package com.example.randomjoke.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
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
        /*recyclerFavoriteJoke.adapter = JokeAdapter(listOf(), this@FavoriteJokesActivity)*/

        viewmodel.selectAllFavoriteJokes()

        viewmodel.listJoke.observe(this@FavoriteJokesActivity, {
            recyclerFavoriteJoke?.adapter = object: JokeAdapter(it, this@FavoriteJokesActivity){
                override fun deleteOneFavoriteJoke(id: Int) {
                    viewmodel.deleteOneFavoriteJoke(id)
                    viewmodel.selectAllFavoriteJokes()
                    Toast.makeText(this@FavoriteJokesActivity, R.string.success_delete , Toast.LENGTH_SHORT).show()
                }

            }





        })


    }

}

public abstract class JokeAdapter(jokeList: List<Joke?>, context: Context): RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    var lista = jokeList
    var context = context

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
        holder.layoutJoke.setOnLongClickListener {
            var delDialog = AlertDialog.Builder(context)
                .setTitle(R.string.wish_delete)
                .setPositiveButton(R.string.yes, object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        deleteOneFavoriteJoke(objectJoke!!.id)
                    }
                })
                .setNegativeButton(R.string.no, object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }

                })
                .setCancelable(true)
                .create()

            delDialog.show()
            true
        }
    }


    class JokeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textJoke: TextView = view.findViewById(R.id.text_cardview_joke)
        var layoutJoke: ConstraintLayout = view.findViewById(R.id.layout_cardview_joke)
    }


    abstract fun deleteOneFavoriteJoke(id: Int)
}