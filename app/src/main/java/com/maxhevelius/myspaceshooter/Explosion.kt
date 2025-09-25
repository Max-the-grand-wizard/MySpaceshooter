package com.maxhevelius.myspaceshooter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.SystemClock

const val EXPLOSION_DURATION = 500L // Explosion in ms

class Explosion(
    private val bitmap: Bitmap
) {
    var x: Float = 0f
    var y: Float = 0f
    var isActive: Boolean = false
        private set

    private var startTime: Long = 0L

    fun activate(centerX: Float, centerY: Float) {
        x = centerX - bitmap.width / 2f
        y = centerY - bitmap.height / 2f
        startTime = SystemClock.uptimeMillis()
        isActive = true
    }

    fun update() {
        if (!isActive) return
        val now = SystemClock.uptimeMillis()
        if (now - startTime > EXPLOSION_DURATION) {
            isActive = false
        }
    }

    fun render(canvas: Canvas, paint: Paint) {
        if (!isActive) return
        canvas.drawBitmap(bitmap, x, y, paint)
    }
}
