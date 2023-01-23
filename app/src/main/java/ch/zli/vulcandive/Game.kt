package ch.zli.vulcandive

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import ch.zli.vulcandive.Model.Player
import ch.zli.vulcandive.Model.Rock


class Game: AppCompatActivity(), SensorEventListener {


    var player: Player? = null
    private lateinit var sensorManager: SensorManager
    private var gyro: Sensor? = null
    private var rockList : ArrayList<Rock> = ArrayList()
    private var spawnTimer : Int = 0
    private var repeats : Int = 0
    private var scoreText : TextView? = null
    private var score : Int = 0
    private var highscore : Int = 0
    private var myLayout: ConstraintLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        player =  Player(findViewById(R.id.player))
        scoreText = findViewById(R.id.count) as TextView
        myLayout = findViewById(R.id.myLayout) as ConstraintLayout
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        val sharedPreference = getSharedPreferences("data", Context.MODE_PRIVATE)
        if (sharedPreference.getInt("highscore", 0) != null){
            highscore = sharedPreference.getInt("highscore",0)
            var highscoretext = findViewById(R.id.highscoretext) as TextView
            highscoretext.text = "highscore: "+ highscore.toString()
        }

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        // Do something with this sensor data.
        updateGameState()
        movePlayer((event))


    }

    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()
        sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        sensorManager.unregisterListener(this)
    }


    //Spieler Bewegen je nach Sensoren Daten
    fun movePlayer(event: SensorEvent){
        player?.moveX(event.values[1].toInt() * 100)
        player?.moveY(event.values[0].toInt() * 100)
    }

    //Spiele Loop für Punkte und Gegner Spawnen
    fun updateGameState(){
        spawnTimer++
        score++
        scoreText?.text = "score: " +score.toString()

        if(spawnTimer >= 10){
            spawnRock()
            spawnTimer = 0
            repeats++
            if(repeats == 3){
                repeats++
                spawnRock()
                spawnRock()
                spawnRock()
            }
            if (repeats == 10){
                repeats++
                spawnRock()
                spawnRock()
                spawnRock()
                spawnRock()
                spawnRock()
                spawnRock()
                spawnRock()
                spawnRock()
                spawnRock()
            }

        }
        for (rock in rockList){
            rock.move()
            if (player?.let { rock.checkCollision(it) } == true){
                val i = Intent(this, EndScreen::class.java)
                i.putExtra("score", score)
                startActivity(i)
                finish();

            }
        }
    }

    //Gegner erstellen und der rockList hinzufügen
    fun spawnRock(){
        var rock: Rock =  Rock(this);
        rockList.add(rock)
        //dieser code mit hilfe von https://stackoverflow.com/questions/28071349/the-specified-child-already-has-a-parent-you-must-call-removeview-on-the-chil
        if (rock.getimg().getParent() != null) {
            (rock.getimg().getParent() as ViewGroup).removeView(rock.getimg()) // <- fix
        }
        myLayout?.addView(rock.getimg())
        myLayout?.requestLayout();
    }

}