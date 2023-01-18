package ch.zli.vulcandive

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random


class Rock(context: Context) {

    private var image: ImageView = ImageView(context)
    private var speed: Float = (Math.random() * 70 + 25).toFloat()
    private lateinit var constraintLayout: ConstraintLayout




    init {
        image.setX(Random.nextFloat() * 800 )
        image.setY(0f)
        image.setImageResource(R.drawable.rock)
        image.adjustViewBounds = true
        image.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )



        // Create a ConstraintLayout in which to add the ImageView
        constraintLayout = ConstraintLayout(context).apply {

            // Add the ImageView to the layout.
            addView(image)
        }


    }

    fun move() {
        println(image.getY())
        image.setY(image.getY() + speed)
    }
    fun getimg(): ImageView {
        return image
    }

}