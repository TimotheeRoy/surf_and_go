package com.example.surf_and_go

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // démarre la musique de fond
        //startService(Intent(this, BackgroundSoundService::class.java))

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // cache la bar d'action
        supportActionBar?.hide()


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SpotsListActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}
