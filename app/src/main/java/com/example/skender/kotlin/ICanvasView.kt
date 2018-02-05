package com.example.skender.kotlin

/**
 * Created by skender on 05.02.18.
 */
interface ICanvasView {
    fun drawCircle(mainCircle: SimpleCircle);
    fun redraw();
    fun showMessage(text: String);
}