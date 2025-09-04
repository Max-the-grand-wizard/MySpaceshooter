package com.maxhevelius.myspaceshooter

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView

class Game(context: Context?) : SurfaceView(context), Runnable, SurfaceHolder.Callback {
    private val TAG = "Game"
    lateinit var gameThread : Thread
    @Volatile var isRunning : Boolean = false
    val star = Star()


    init {
        holder?.addCallback(this)
    }


    override fun run() {
        Log.d(TAG, "run()")
        while (isRunning){
            update()
            render()
        }
    }

    private fun render() {
        val canvas = holder?.lockCanvas() ?: return
        canvas.drawColor(Color.BLUE)
        val paint= Paint()

        //render all entities
        star.render(canvas, paint )
        holder.unlockCanvasAndPost(canvas)

    }

    private fun update() {
        //update all objects(entities)
        star.update()

    }

    fun onPause(){
        Log.d(TAG, "onPause()")
    }

    fun onResume(){
        Log.d(TAG, "onResume()")

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Log.d(TAG, "surfaceCreated()")
        isRunning = true
        gameThread =Thread(this)
        gameThread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.d(TAG, "surfaceChanged()")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.d(TAG, "surfaceDestroyed()")
        isRunning = false
        gameThread.join()
    }


}