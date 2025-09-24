package com.maxhevelius.myspaceshooter

import android.content.res.AssetManager
import android.media.MediaPlayer
import android.util.Log

object MusicPlayer {
    private var bgm: MediaPlayer? = null
    private var currentFile: String? = null
    private var isPaused: Boolean = false

    fun start(assetManager: AssetManager, fileName: String) {
        try {

            if (bgm != null && currentFile == fileName && bgm?.isPlaying == true) return


            if (bgm != null) stop()

            val afd = assetManager.openFd(fileName)
            bgm = MediaPlayer()
            bgm?.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            bgm?.isLooping = true
            bgm?.prepare()
            bgm?.start()
            afd.close() // close first after start
            currentFile = fileName
            isPaused = false
        } catch (e: Exception) {
            Log.e("MusicPlayer", "Failed to start $fileName: ${e.message}")
        }
    }

    fun pause() {
        if (bgm?.isPlaying == true) {
            bgm?.pause()
            isPaused = true
        }
    }

    fun resume() {
        if (bgm != null && isPaused) {
            bgm?.start()
            isPaused = false
        }
    }




    fun stop() {
        try {
            bgm?.stop()
        } catch (e: Exception) {
            Log.e("MusicPlayer", "stop() failed: ${e.message}")
        }
        try {
            bgm?.release()
        } catch (e: Exception) {
            Log.e("MusicPlayer", "release() failed: ${e.message}")
        }
        bgm = null
        currentFile = null
        isPaused = false
    }
}
