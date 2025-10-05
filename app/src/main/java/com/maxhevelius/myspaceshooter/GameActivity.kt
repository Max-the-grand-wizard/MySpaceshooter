package com.maxhevelius.myspaceshooter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    private val tag = "GameActivity"
    private lateinit var game : Game


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "Game Activity was launched")
        game = Game(applicationContext)
        setContentView(game)
    }

    override fun onPause() {
        super.onPause()
        game.onPause()
    }

    override fun onResume() {
        super.onResume()
        game.onResume()
    }

    override fun onDestroy() {
        game.onDestroy()
        super.onDestroy()

    }
}

