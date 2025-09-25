package com.maxhevelius.myspaceshooter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Star : Entity() {
    private val radius = RNG.nextInt(2, 6).toFloat()


    init{

        x = RNG.nextInt(STAGE_WIDTH).toFloat()
        y = RNG.nextInt(STAGE_HEIGHT).toFloat()
        velX = -6f

    }


    fun update(playerVelocity: Float) {
        super.update()
        x -= playerVelocity
        if (right < 0){
            x = STAGE_WIDTH.toFloat()
            centerY = RNG.nextInt(STAGE_HEIGHT).toFloat()
        }
    }



    override fun render(canvas: Canvas, paint: Paint) {
        super.render(canvas, paint)
        val colors = listOf(Color.WHITE, Color.YELLOW, Color.CYAN)
        paint.color = colors.random()

        //paint.color = Color.YELLOW
        canvas.drawCircle(x, y, radius, paint)
    }

}