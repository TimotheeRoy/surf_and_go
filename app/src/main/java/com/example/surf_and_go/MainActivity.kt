package com.example.surf_and_go

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // cache la bar d'action
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SpotsListActivity::class.java)
            startActivity(intent)
            finish()
        }, 3900) //3900


        // Initialiser le MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.waves)

        // Définir un écouteur pour le MediaPlayer
        mediaPlayer.setOnPreparedListener {
            // La musique est prête à être jouée
            mediaPlayer.start()
        }



    }
    override fun onDestroy() {
        super.onDestroy()
        // Libérer les ressources du MediaPlayer
        mediaPlayer.release()
    }

}
