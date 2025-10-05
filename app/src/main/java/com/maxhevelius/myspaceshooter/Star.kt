package com.maxhevelius.myspaceshooter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Star : Entity() {
    private val radius = RNG.nextInt(2, 5).toFloat()
    private val color = listOf(Color.WHITE, Color.YELLOW, Color.CYAN).random()


    init{

        x = RNG.nextInt(STAGE_WIDTH).toFloat()
        y = RNG.nextInt(STAGE_HEIGHT).toFloat()
        velX = -RNG.nextFloat() * 4f - 2f
        //velX = -6f

    }


    fun update(playerVelocity: Float) {
        super.update()
        x += velX - playerVelocity
        //x -= playerVelocity
        if (right < 0){
            x = STAGE_WIDTH.toFloat()
            centerY = RNG.nextInt(STAGE_HEIGHT).toFloat()
            velX = -RNG.nextFloat() * 4f - 2f //added
        }
    }



    override fun render(canvas: Canvas, paint: Paint) {
        super.render(canvas, paint)
        paint.color = color
        //paint.color = Color.YELLOW
        canvas.drawCircle(x, y, radius, paint)
    }

}