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
        MusicPlayer.start(assets, "game_music.mp3")
        game = Game(this)
        setContentView(game)
    }

    override fun onPause() {
        super.onPause()
        game.onPause()
        MusicPlayer.pause()
    }

    override fun onResume() {
        super.onResume()
        game.onResume()
        MusicPlayer.resume()
    }

    override fun onDestroy() {
        game.onDestroy()
        super.onDestroy()

    }
}

