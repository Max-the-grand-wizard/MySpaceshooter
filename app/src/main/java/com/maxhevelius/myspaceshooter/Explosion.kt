package com.maxhevelius.myspaceshooter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.SystemClock

const val EXPLOSION_DURATION = 75L // Explosion in ms

class Explosion(
    private val frames: List<Bitmap>

) {
    private var x: Float = 0f
    private var y: Float = 0f
    var isActive: Boolean = false
        private set

    private var startTime: Long = 0L

    fun activate(centerX: Float, centerY: Float) {
        x = centerX - frames[0].width / 2f
        y = centerY - frames[0].height / 2f
        startTime = SystemClock.uptimeMillis()
        isActive = true
    }

    fun update() {
        if (!isActive) return
        val elapsed = SystemClock.uptimeMillis() - startTime
        val totalDuration = EXPLOSION_DURATION * frames.size
        if (elapsed >= totalDuration) isActive = false

        }


fun render(canvas: Canvas, paint: Paint) {
    if (!isActive) return
    val elapsed = SystemClock.uptimeMillis() - startTime
    val frameIndex = ((elapsed / EXPLOSION_DURATION).toInt()).coerceAtMost(frames.size - 1)
    canvas.drawBitmap(frames[frameIndex], x, y, paint)
}

}
