package com.example.skender.kotlin


import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast

/**
 * Created by skender on 06.02.18.
 */

class CanvasView : View, ICanvasView{

    companion object {
        private var Width: Int = 0
        private var Heigth: Int = 0
    }

    private lateinit var paint: Paint
    private lateinit var canvas: Canvas
    private var toast: Toast? = null

    private var gestureDetector:GestureDetector

    private var gameManager: GameManager

    override fun drawCircle(mainCircle: SimpleCircle) {
        paint.color=mainCircle.getColor()
        canvas.drawCircle(mainCircle.x.toFloat(),
                mainCircle.y.toFloat(),
                mainCircle.radius.toFloat(),
                paint)

    }

    override fun redraw() = invalidate()

    override fun showMessage(text: String) {
        if(toast != null){
            toast!!.cancel()
        }

        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast!!.show()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initWidthAndHeight(context)
        gameManager = GameManager(this, Width, Heigth)
        gestureDetector = GestureDetector(context, GestureListener(context))
        initPaint()
    }

    private fun initPaint(){
        paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL

    }

    private fun initWidthAndHeight(context: Context) {
        var windowManager: WindowManager =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        var displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        Width = displayMetrics.widthPixels
        Heigth = displayMetrics.heightPixels

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.canvas = canvas!!
        gameManager.onDraw()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x: Int = event!!.x.toInt()
        var y: Int = event!!.y.toInt()
        if (event.action == MotionEvent.ACTION_MOVE){
            gameManager.onTouchEvent(x, y)
        }
        invalidate()
        return gestureDetector.onTouchEvent(event)

    }
}