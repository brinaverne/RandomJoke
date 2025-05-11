package com.example.randomjoke.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomjoke.R
import com.example.randomjoke.model.Joke
import com.example.randomjoke.viewmodel.JokeViewModel

class MainActivity : AppCompatActivity() {


    lateinit var textJoke: TextView
    lateinit var btnNewJoke: Button
    lateinit var imgButtonGeneral: ImageButton
    lateinit var imgButtonProgramming: ImageButton
    lateinit var imgButtonFavorite: ImageButton
    lateinit var imgButtonAddFavorite: ImageButton
    lateinit var imgButtonMenu: ImageButton
    lateinit var objectJoke: Joke

    val viewmodel: JokeViewModel by lazy {
        ViewModelProvider(this).get(JokeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        textJoke = findViewById(R.id.text_joke)
        btnNewJoke = findViewById(R.id.button_new_joke)
        imgButtonGeneral = findViewById(R.id.image_button_all)
        imgButtonProgramming = findViewById(R.id.image_button_programming)
        imgButtonFavorite = findViewById(R.id.image_button_favorite)
        imgButtonAddFavorite = findViewById(R.id.image_button_add_to_favorite)
        imgButtonMenu = findViewById(R.id.image_button_menu)
        var category: Int = 0

        viewmodel.joke.observe(this@MainActivity, {
            if(it != null){
                var setup = it.setup
                var punchline = it.punchline
                textJoke.text = "$setup\n$punchline"

                objectJoke = it


            }

        })

        viewmodel.getJoke()



        imgButtonGeneral.setOnClickListener {
            handleImageButtonColor(imgButtonGeneral)
            category = 1
        }

        imgButtonProgramming.setOnClickListener {
            handleImageButtonColor(imgButtonProgramming)
            category = 2
        }

        imgButtonFavorite.setOnClickListener {
            handleImageButtonColor(imgButtonFavorite)
            category = 3
        }



        btnNewJoke.setOnClickListener {

            if (category != 0 && category < 3){
                viewmodel.getJokeByCategory(category)
            }
            else if (category == 3){
                viewmodel.getFavoriteJoke()
            }
            else{
                /*viewmodel.getJoke()*/
                viewmodel.getJoke()
            }

        }


        imgButtonAddFavorite.setOnClickListener {
            var favdialog = AlertDialog.Builder(this)
                .setTitle("Wish to move this joke to favorites?")
                .setPositiveButton(R.string.yes, object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        viewmodel.insertFavoriteJoke(objectJoke)
                    }
                })
                .setNegativeButton(R.string.no, object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }

                })
                .setCancelable(true)
                .create()

            favdialog.show()

        }


        imgButtonMenu.setOnClickListener {
            startActivity(Intent(this@MainActivity, FavoriteJokesActivity::class.java))
        }



    }


    fun handleImageButtonColor(id:ImageButton){
        imgButtonGeneral.setColorFilter(ContextCompat.getColor(this, R.color.green))
        imgButtonProgramming.setColorFilter(ContextCompat.getColor(this, R.color.green))
        imgButtonFavorite.setColorFilter(ContextCompat.getColor(this, R.color.green))
        id.setColorFilter(ContextCompat.getColor(this, R.color.white))

    }



}