package com.maxhevelius.myspaceshooter

import android.content.res.AssetManager
import android.media.MediaPlayer

class MusicPlayer(private val assetManager: AssetManager) {
    private var bgm: MediaPlayer? = null

    fun start(fileName: String) {
        if (bgm == null) {
            val afd = assetManager.openFd(fileName)
            bgm = MediaPlayer()
            bgm?.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            afd.close()
            bgm?.isLooping = true
            bgm?.prepare()
        }
        bgm?.start()
    }

    fun pause() {
        bgm?.pause()
    }

    fun stop() {
        bgm?.stop()
        bgm?.release()
        bgm = null
    }
}
