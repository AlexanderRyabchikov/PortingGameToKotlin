package com.example.skender.kotlin

/**
 * Created by skender on 05.02.18.
 */
class GameManager{
    companion object {
        const val MAX_CIRCLES: Int = 10
        @JvmField
        var width: Int = 0
        @JvmField
        var heigth: Int = 0

        fun getHeigth(): Int = heigth
        fun getWidth(): Int = width
    }


    private lateinit var mainCircle: MainCircle
    private lateinit var circles: ArrayList<EnemyCircle>
    private lateinit var canvasView: CanvasView


    constructor(canvasView: CanvasView, w: Int, h: Int){
        this.canvasView = canvasView;
        width = w;
        heigth = h;
        initMainCircle();
        initEnemyCircles();
    }

    private fun initMainCircle() {

    }

    private fun initEnemyCircles() {
        var mainCircleArea: SimpleCircle = mainCircle.getCircleArea()
        circles = ArrayList<EnemyCircle>()
        for (i in 0..MAX_CIRCLES){
            var enemyCircle: EnemyCircle
            do{
                enemyCircle = EnemyCircle.getRandomCircle()
            }while(enemyCircle.isIntersect(mainCircle))

            circles.add(enemyCircle)
        }

        calculateAndSetCirclesColor()

    }

    private fun calculateAndSetCirclesColor() {
        for(circle: EnemyCircle in circles){
            circle.setEnemyOrFoodColorDependsOn(mainCircle)
        }
    }

    fun onDraw(){
        canvasView.drawCircle(mainCircle)
        for(circle: EnemyCircle in circles){
            canvasView.drawCircle(circle)
        }
    }

    fun onTouchEvent(x: Int, y: Int){
        mainCircle.moveMainCircleOnTouchAt(x, y)
        checkCollision()
        moveCircles()
    }

    private fun moveCircles() {
        for(circle: EnemyCircle in circles){
            circle.moveOnStep()
        }
    }

    private fun checkCollision() {
        var circleForDel: SimpleCircle? = null
        for (circle:SimpleCircle in circles){
            if (mainCircle.isIntersect(circle)){
                mainCircle.growRadius(circle)
                circleForDel = circle
                calculateAndSetCirclesColor()
                break
            }else{
                gameEnd("YOU LOSE")
                return
            }
        }
        if (circleForDel != null){
            circles.remove(circleForDel)
        }
        if (circles.isEmpty()){
            gameEnd("YOU WIN")
        }
    }

    private fun gameEnd(s: String) {
        canvasView.showMessage(s)
        mainCircle.initRadius()
        canvasView.redraw()
    }



}