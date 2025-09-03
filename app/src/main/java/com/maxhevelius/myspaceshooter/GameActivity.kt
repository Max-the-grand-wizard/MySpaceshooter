package com.maxhevelius.myspaceshooter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    private val TAG = "GameActivity"
    lateinit var game : Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = Game(this)
        setContentView(game)
        Log.d(TAG, "Game Activity was launched")
    }
    override fun onPause() {
        super.onPause()
        game.onPause()
    }

    override fun onResume() {
        game.onResume()
        super.onResume()
    }
}