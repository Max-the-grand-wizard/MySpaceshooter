package com.maxhevelius.myspaceshooter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    private val TAG = "GameActivity"
    lateinit var game : Game
    private lateinit var musicPlayer: MusicPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Game Activity was launched")
        musicPlayer = MusicPlayer(assets)
        musicPlayer.start("game_music.mp3", )
        game = Game(this)
        setContentView(game)
    }
    override fun onPause() {
        super.onPause()
        game.onPause()
        musicPlayer.pause()
    }

    override fun onResume() {
        game.onResume()
        super.onResume()
        musicPlayer.start("game_music.mp3")
    }

    override fun onDestroy() {
        game.onDestroy()
        super.onDestroy()
        musicPlayer.stop()
    }
}