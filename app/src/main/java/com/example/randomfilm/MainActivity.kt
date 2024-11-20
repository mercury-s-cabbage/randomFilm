package com.example.randomfilm

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val filmName = findViewById<TextView>(R.id.film_name)
        val changeButton = findViewById<Button>(R.id.change_film_button)
        val renewButton = findViewById<Button>(R.id.renew_button)
        var movies = resources.getStringArray(R.array.films).toMutableList()

        changeButton.setOnClickListener {
            val randomMovie = RandFilm(movies)
            filmName.text = randomMovie
        }

        renewButton.setOnClickListener{
            filmName.text = getString(R.string.first)
            movies = resources.getStringArray(R.array.films).toMutableList()
        }
    }

    private fun RandFilm(movies: MutableList<String>): String
    {
        if (movies.isNotEmpty())
        {
            val s = movies.random()
            movies.remove(s)
            return s
        }
        else
        {
            return resources.getString(R.string.end)
        }
    }
}