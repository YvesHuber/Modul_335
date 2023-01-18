package ch.zli.vulcandive

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendNotification(){

    }

    fun startGame(view: View){

        startActivity(Intent(this,Game::class.java))

    }
    fun quitGame(){

        finish();
        System.exit(0);
    }
}