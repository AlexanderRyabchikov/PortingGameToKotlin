package com.example.skender.kotlin

import android.graphics.Color

/**
 * Created by skender on 05.02.18.
 */
class MainCircle : SimpleCircle {

    companion object {
        const val INIT_RADIUS: Int = 50
        const val CIRCLE_SPEED: Int = 100
        const val OUR_COLOR: Int = Color.BLUE
    }

    constructor(x: Int, y: Int):super(x, y, INIT_RADIUS){
        setColor(OUR_COLOR)
    }

    fun moveMainCircleOnTouchAt(x1: Int, y1: Int){
        var dx: Int = (x1 - x) * CIRCLE_SPEED / GameManager.getWidth()
        var dy: Int = (y1 - y) * CIRCLE_SPEED / GameManager.getHeigth()
        x += dx;
        y += dy;
    }

    fun initRadius(){
        radius = INIT_RADIUS
    }

    fun growRadius(circle:SimpleCircle){
        radius = Math.sqrt(
                Math.pow(radius.toDouble(), 2.0) + Math.pow(circle.radius.toDouble(), 2.0)).toInt()
    }
}