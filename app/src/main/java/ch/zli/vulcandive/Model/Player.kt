package ch.zli.vulcandive.Model

import android.widget.ImageView

class Player(image:ImageView) {

    private var image: ImageView = image

    //-1 for flip
    //Move Player on X and Y

    fun moveX(value: Int) {
        if(image.getX().minus(value) >= 30 && image.getX().minus(value) <= 1100){
            image.getX().minus(value* -1)?.let { image.setX(it) }
        }
    }
    fun moveY(value: Int) {
        if(image.getY().minus(value) >= 30 && image.getY().minus(value) <= 2300) {
            image.getY().minus(value * -1)?.let { image.setY(it) }
        }
    }

    fun getImg(): ImageView {
        return image
    }
}