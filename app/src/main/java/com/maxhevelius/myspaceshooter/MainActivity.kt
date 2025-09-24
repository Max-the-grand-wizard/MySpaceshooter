package com.maxhevelius.myspaceshooter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val TAG = "MainMenuActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        MusicPlayer.start(assets, "menu_music.mp3")

        findViewById<Button>(R.id.startGameButton)?.setOnClickListener {
            Log.d(TAG, "Start game button pressed")



            startActivity(Intent(this, GameActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        MusicPlayer.start(assets, "menu_music.mp3")

        val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val longestDistance = prefs.getFloat(LONGEST_DIST, 0.0f)
        val highscore = findViewById<TextView>(R.id.highscore)
        highscore.text = getString(R.string.longest_distance, longestDistance.toInt())
    }

    override fun onPause() {
        super.onPause()
        MusicPlayer.pause()
    }


}

