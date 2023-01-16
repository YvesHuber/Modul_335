package ch.zli.vulcandive

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Game: AppCompatActivity(), SensorEventListener {


    var player: Player? = null
    private lateinit var sensorManager: SensorManager
    private var gyro: Sensor? = null
    private var rockList : ArrayList<Rock> = ArrayList()
    private var spawntimer : Int = 0
    private var scoretext : TextView? = null
    private var score : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        player =  Player(findViewById(R.id.player))
        scoretext = findViewById(R.id.count) as TextView
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)


    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        // Do something with this sensor data.
        updategamestate()
        moveplayer((event))


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

    fun moveplayer(event: SensorEvent){
        player?.moveX(event.values[1].toInt() * 100)
        player?.moveY(event.values[0].toInt() * 100)
    }

    fun updategamestate(){
        spawntimer++
        score++
        scoretext?.text = "score: " +score.toString()

        if(spawntimer >= 5){
            spawnRock()
            spawntimer = 0
        }
    }
    fun spawnRock(){

    }

}