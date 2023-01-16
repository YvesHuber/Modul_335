package ch.zli.vulcandive

import android.widget.ImageView

class Player(image:ImageView) {

    private var image: ImageView? = image



    fun moveX(value: Int) {
        println(image)

        image?.getX()?.minus(value)?.let { image?.setX(it) }
    }
    fun moveY(value: Int) {

        image?.getY()?.minus(value)?.let { image?.setY(it) }
    }

}