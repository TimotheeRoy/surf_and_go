package com.example.surf_and_go

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class SpotDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spot_details)
        // cache la bar d'action
        supportActionBar?.hide()

        //Les variables "get" vont chercher la data de SpotsListActivity
        val name = intent.getStringExtra("spotName")

        //Les variables "name, image, etc" sont des pointeurs
        val nameView: TextView = findViewById<TextView>(R.id.name_detail)
        nameView.text = name

        val imageUrl = intent.getStringExtra("spotImage")
        val imageView: ImageView = findViewById<ImageView>(R.id.image_detail)
        Picasso.get()
            .load(imageUrl)
            .into(imageView)


        val location = intent.getStringExtra("spotLocation")
        val locationView: TextView = findViewById<TextView>(R.id.location_detail)
        locationView.text = location

        val button: Button = findViewById<Button>(R.id.button_detail)
        button.setOnClickListener(){
            val intent = Intent(applicationContext, SpotsListActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}