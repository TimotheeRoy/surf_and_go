package com.example.surf_and_go

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
data class ApiResponse(
    val location: Location,
    val forecast: Forecast
)

data class Location(
    val name: String,
    val country: String,
    val localtime: String
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val day: Day,
    val hour: List<Hour>
)

data class Day(
    val tides: List<Tides>
)

data class Tides(
    val tide: List<Tide>
)

data class Tide(
    val tideTime: String,
    val tideHeightMt: String,
    val tideType: String
)

data class Hour(
    val time: String,
    val tempC: Double,
    val condition: Condition,
    val windKph: Double,
    val windDir: String,
    val uv: Int,
    val sigHtMt: Double,
    val swellHtMt: Double,
    val swellDir: Int,
    val swellPeriodSecs: Int,
    val waterTempC: Double
)

data class Condition(
    val text: String,
    val icon: String
)
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