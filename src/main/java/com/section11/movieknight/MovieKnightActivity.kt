package com.section11.movieknight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.section11.movieknight.ui.MovieKnightPresenter

class MovieKnightActivity : AppCompatActivity() {

    lateinit var presenter: MovieKnightPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}