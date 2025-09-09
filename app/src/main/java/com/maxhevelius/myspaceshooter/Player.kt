package com.maxhevelius.myspaceshooter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.math.MathUtils.clamp
import kotlin.math.absoluteValue

const val PLAYER_HEIGHT = 75
const val GRAVITY = 0.5f
const val DRAG = 0.97f
const val ACCELERATION = 0.8f // on the x axis
const val BOOST_FORCE = -0.8f // on the y axis
const val MAX_VEL = 20f
const val VELOCITY_EPSILON = 0.01f //small threshold for snapping to 0
const val PLAYER_STARTING_HEALTH = 3
const val PLAYER_MARGIN_X = 20f

class Player(game: Game) : Entity() {
    private val bitmap = createScaledBitmap(game, R.drawable.player)
    var health = PLAYER_STARTING_HEALTH
    var distanceTraveled = 0f

    init{
        width = bitmap.width.toFloat()
        height = bitmap.height.toFloat()
        respawn()

    }

    fun respawn(){
        distanceTraveled = 0f
        x= PLAYER_MARGIN_X
        health = PLAYER_STARTING_HEALTH
        centerY = STAGE_HEIGHT/2.0f
        velY = 0f
        velX = 0f
    }

    override fun onCollision(that: Entity) {
        super.onCollision(that)
        health--
    }

    private fun createScaledBitmap(game : Game, resID : Int) : Bitmap{
        val orig = BitmapFactory.decodeResource(game.resources, resID)
        val ratio = PLAYER_HEIGHT.toFloat() / orig.height
        val newH = (orig.height * ratio).toInt()
        val newW = (orig.width * ratio).toInt()
        return Bitmap.createScaledBitmap(orig, newW, newH, true)
    }

    fun update(isBoosting : Boolean) {
        super.update()
        applyDrag()
        applyGravity()
        if (isBoosting){
            applyBoost()
        }
       // x += velX
        y += velY
        distanceTraveled += velX
        if (bottom > STAGE_HEIGHT){
            bottom = STAGE_HEIGHT.toFloat()
            velY = 0f

        }
    }

    private fun applyBoost() {
        velX += ACCELERATION
        velY += BOOST_FORCE
        velX = clamp(velX, 0f, MAX_VEL)

    }

    private fun applyGravity() {
        velY += GRAVITY
        velY = clamp(velY, -MAX_VEL, MAX_VEL)
    }

    private fun applyDrag() {
        velX *= DRAG
        velY *= DRAG
        if (velX.absoluteValue < VELOCITY_EPSILON) velX =0f
        if (velY.absoluteValue < VELOCITY_EPSILON) velY =0f
    }

    override fun render(canvas: Canvas, paint: Paint) {
        super.render(canvas, paint)
        canvas.drawBitmap(bitmap, x, y, paint )
    }
}