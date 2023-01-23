package ch.zli.vulcandive.Model

import android.content.Context
import android.graphics.Rect
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import ch.zli.vulcandive.R
import kotlin.math.roundToInt
import kotlin.random.Random


class Rock(context: Context) {

    private var image: ImageView = ImageView(context)
    private var speed: Float = (Math.random() * 70 + 25).toFloat()
    private lateinit var constraintLayout: ConstraintLayout




    init {
        image.setX(Random.nextFloat() * 1100 )
        image.setY(-60f)
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
        // TODO: Kleine steine sind schneller als grosse
        val scale: Int = (Math.random() * 400 + 200).roundToInt()
        image.maxHeight = scale
        image.maxWidth = scale
        speed = (speed - scale * 0.087).toFloat()
        if (speed <= 0){
            speed = (Math.random() * 30 + 5).toFloat()
        }


    }

    fun move() {
        image.setY(image.getY() + speed)
    }
    fun getimg(): ImageView {
        return image
    }
    fun checkcollision(player: Player):Boolean {
        val playerimg = player.getImg()

        //Dieser Code wurde mit hilfe von https://www.tutorialkart.com/kotlin-android/detect-collision-between-two-sprites-bitmaps-in-android-game/ geschrieben

        val rockRect: Rect = Rect(image.x.toInt(), image.y.toInt(),
            (image.x+image.width).toInt(), (image.y+image.height).toInt()
        )
        val playerRect: Rect = Rect(playerimg.x.toInt(),
            playerimg.y.toInt(), (playerimg.x+playerimg.width -80).toInt(), (playerimg.y+playerimg.height -50).toInt()
        )

        if(rockRect.intersect(playerRect)){
            println("Collided.")
           return true
        }else {
            return false
        }
        //


    }

}