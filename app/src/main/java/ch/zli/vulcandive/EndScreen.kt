package ch.zli.vulcandive

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class EndScreen : AppCompatActivity() {

    private var score : Int = 0;
    private var highscore : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.endscreen)
        val sharedPreference = getSharedPreferences("data", Context.MODE_PRIVATE)

        score = intent.getIntExtra("score",0)
        if (sharedPreference.getInt("highscore", 0) != null){
            highscore = sharedPreference.getInt("highscore",0)
        }
        val text: TextView = findViewById(R.id.Score)
        val highscoretext: TextView = findViewById(R.id.highscore)
        println(getIntent().extras)
        println(score)
        text.text = "Your score was: " + score.toString()
        highscoretext.text = "Highest score was: " + highscore.toString()

        if (score >= highscore){
            if (score > highscore){
                text.text = "New Highscore: " + score.toString()
                highscoretext.text = "New Highscore: " + score.toString()
            }
            var editor = sharedPreference.edit()
            editor.putInt("highscore", score)
            editor.commit()
        }



    }


    //Notification abschicken
    fun sendNotification(){
        createNotificationChannel();
        val intent = Intent(this, MainMenu::class.java).apply {}
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val sharedPreference = getSharedPreferences("data", Context.MODE_PRIVATE)
        var highscore = 0
        if (sharedPreference.getInt("highscore", 0) != null){
            highscore = sharedPreference.getInt("highscore",0)
        }

        var builder = NotificationCompat.Builder(this, "diver")
            .setSmallIcon(R.drawable.rock)
            .setContentTitle("VulcanDive")
            .setContentText("Can you beat your last highscore of " + highscore +" ?")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(12314123, builder.build())
        }
    }


    //createNotificationChannel wurde von der Android Studio Dokumentation Kopiert
    //https://developer.android.com/develop/ui/views/notifications/build-notification#Priority

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "VulcanDive"
            val descriptionText = "VulcanDive"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("diver", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    //Activity wechseln
    fun restart(view: View){
        val i = Intent(this, MainMenu::class.java)
        i.putExtra("score", score)
        startActivity(i)
        finish();
    }

    override fun onPause() {
        super.onPause()
        sendNotification()
    }

    override fun onDestroy() {
        super.onDestroy()
        sendNotification()

    }

}