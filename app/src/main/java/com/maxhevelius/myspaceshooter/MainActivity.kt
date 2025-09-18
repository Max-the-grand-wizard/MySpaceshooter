package com.maxhevelius.myspaceshooter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    private lateinit var musicPlayer: MusicPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        musicPlayer = MusicPlayer(assets)
         findViewById<Button>(R.id.startGameButton)?.setOnClickListener{
            Log.d(TAG, "Start game button pressed")

             musicPlayer.stop()

             val intent = Intent(this, GameActivity::class.java)
             startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()



        musicPlayer.start("menu_music.mp3")

        val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val longestDistance = prefs.getFloat(LONGEST_DIST, 0.0f)
        val highscore = findViewById<TextView>(R.id.highscore)
        //highscore.text = "Longest distance: $longestDistance km"
        highscore.text = getString(R.string.longest_distance, longestDistance.toInt())

    }
    override fun onPause() {
        super.onPause()
        musicPlayer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlayer.stop()

}
}