package com.maxhevelius.myspaceshooter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

const val PLAYER_HEIGHT = 100

class Player(game: Game) : Entity() {
    private val bitmap = createScaledBitmap(game, R.drawable.player)

    init{
        width = bitmap.width.toFloat()
        height = bitmap.height.toFloat()

    }

    private fun createScaledBitmap(game : Game, resID : Int) : Bitmap{
        val orig = BitmapFactory.decodeResource(game.resources, resID)
        val ratio = PLAYER_HEIGHT.toFloat() / orig.height
        val newH = (orig.height * ratio).toInt()
        val newW = (orig.width * ratio).toInt()

        return Bitmap.createScaledBitmap(orig, newW, newH, true)
    }

    override fun render(canvas: Canvas, paint: Paint) {
        super.render(canvas, paint)
        canvas.drawBitmap(bitmap, x, y, paint )
    }
}