package ch.zli.vulcandive

import android.widget.ImageView

class Player(image:ImageView) {

    private var image: ImageView = image

    fun moveX(value: Int) {
        println(image.getX())
        if(image.getX().minus(value) >= 30 && image.getX().minus(value) <= 1100){
            image.getX().minus(value)?.let { image.setX(it) }
        }
    }
    fun moveY(value: Int) {
        println(image.getY())

        if(image.getY().minus(value) >= 30 && image.getY().minus(value) <= 2300) {
            image.getY().minus(value)?.let { image.setY(it) }
        }
    }

}