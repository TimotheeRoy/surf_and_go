package com.example.surf_and_go

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SpotDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spot_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //Les variables "get" vont chercher la data de SpotsListActivity
        val getName = intent.getStringExtra("spotName")

        //Les variables "name, image, etc" sont des pointeurs
        val name: TextView = findViewById<TextView>(R.id.name_detail)
        name.text = getName

        val getImage = intent.getStringExtra("spotImage")
        val image: ImageView = findViewById<ImageView>(R.id.image_detail)
      

        val getLocation = intent.getStringExtra("spotLocation")
        val location: TextView = findViewById<TextView>(R.id.location_detail)
        location.text = getLocation
    }
}