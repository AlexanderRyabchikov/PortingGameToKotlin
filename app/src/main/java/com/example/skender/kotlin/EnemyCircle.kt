package com.example.skender.kotlin

import android.graphics.Color
import java.util.*

/**
 * Created by skender on 05.02.18.
 */
class EnemyCircle(x: Int, y: Int, radius: Int,private  var dx: Int, private var dy: Int) :
                                                            SimpleCircle(x, y, radius) {
    companion object {
        const private val TO_RADIUS: Int = 110
        const private val RANDOM_SPEED: Int = 5
        const private val FROM_RADIUS: Int = 10
        const private val ENEMY_COLOUR: Int = Color.RED
        const private val FOOR_COLOR: Int = Color.GREEN

        fun getRandomCircle():EnemyCircle{

            var random: Random = Random()
            var x: Int = random.nextInt(GameManager.getWidth())
            var y: Int = random.nextInt(GameManager.getHeigth())
            var radius: Int = FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS)
            var dx: Int = -RANDOM_SPEED + random.nextInt(RANDOM_SPEED * 2)
            var dy: Int = -RANDOM_SPEED + random.nextInt(RANDOM_SPEED * 2)

            var enemyCircle: EnemyCircle = EnemyCircle(x, y, radius, dx, dy)
            enemyCircle.setColor(ENEMY_COLOUR)
            return enemyCircle
        }
    }

    fun setEnemyOrFoodColorDependsOn(mainCircle: MainCircle) =
    if(isSmallerThan(mainCircle)){
        setColor(FOOR_COLOR)
    }else{
        setColor(ENEMY_COLOUR)
    }

    private fun isSmallerThan(circle: SimpleCircle): Boolean = radius < circle.radius

    fun moveOnStep(){
        x+=dx;
        y+=dy;
        checkBounds();

    }

    private fun checkBounds() {
        if (x > GameManager.getWidth() || x < 0){
            dx = -dx;
        }

        if (y > GameManager.getHeigth() || y < 0){
            dy = -dy;
        }
    }
}