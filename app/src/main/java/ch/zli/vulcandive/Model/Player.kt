package ch.zli.vulcandive.Model

import android.graphics.drawable.AnimationDrawable
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import ch.zli.vulcandive.R
import kotlin.math.roundToInt
import kotlin.random.Random

class Player(image:ImageView) {

    private var image: ImageView = image


    init {

        image.setBackgroundResource(R.drawable.playeranimation)
        val frameAnimation = image.background as AnimationDrawable
        frameAnimation.start()



    }


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