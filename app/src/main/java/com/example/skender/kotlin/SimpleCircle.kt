package com.example.skender.kotlin

/**
 * Created by skender on 05.02.18.
 */
open class SimpleCircle(var x: Int, var y: Int, var radius: Int) {
    /*companion object {
        const val SIZE_FREE_AREA: Int = 100
    }*/
    private var color: Int = 0

    fun setColor(color: Int){
        this.color = color
    }
    fun getColor():Int = this.color

    //fun getCircleArea():SimpleCircle = SimpleCircle(this.x, this.y, this.radius * SIZE_FREE_AREA)

    fun isIntersect(circle: SimpleCircle):Boolean = this.radius + circle.radius >=
            Math.sqrt(Math.pow((this.x - circle.x).toDouble(), 2.0) +
                    Math.pow((this.y - circle.y).toDouble(), 2.0))

}