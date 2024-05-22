package com.example.surf_and_go

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Data class for Spot
data class Spot(val name: String)

// Retrofit API interface
interface SurfSpotApi {
    @GET("/spotsList")
    fun getSurfSpots(): Call<List<Spot>>
}

// RetrofitInstance singleton
object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val api: SurfSpotApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SurfSpotApi::class.java)
    }
}

class SpotsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_spots_list)

        // Hide the action bar
        supportActionBar?.hide()

        // Initialize the SearchView and set properties
        val searchView: SearchView = findViewById(R.id.edit_text_input)
        val searchTextView: EditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text)
        searchTextView.setHint("Search")
        searchTextView.setHintTextColor(Color.parseColor("#B6C3AD7D"))
        searchTextView.setTextColor(Color.BLACK)

        // Set up RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch data from API and set the adapter
        RetrofitInstance.api.getSurfSpots().enqueue(object : Callback<List<Spot>> {
            override fun onResponse(call: Call<List<Spot>>, response: Response<List<Spot>>) {
                if (response.isSuccessful) {
                    val surfSpots = response.body() ?: emptyList()
                    Log.d("SpotsListActivity", "Received ${surfSpots.size} spots")
                    recyclerView.adapter = SpotAdapter(surfSpots)
                } else {
                    Log.e("SpotsListActivity", "API response not successful, response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Spot>>, t: Throwable) {
                Log.e("SpotsListActivity", "API call failed", t)
            }
        })

        // Set up SearchView listener
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val adapter = recyclerView.adapter as? SpotAdapter
                adapter?.filter(query ?: "")
                return true
            }
        })
    }
}

class SpotAdapter(private var spots: List<Spot>) : RecyclerView.Adapter<SpotAdapter.SpotViewHolder>() {

    private val spotsBackup = spots.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_display, parent, false)
        return SpotViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpotViewHolder, position: Int) {
        val spot = spots[position]
        holder.bind(spot)
    }

    override fun getItemCount(): Int {
        return spots.size
    }

    inner class SpotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(spot: Spot) {
            val button = itemView.findViewById<Button>(R.id.spot_btn)
            button.text = spot.name
            button.setOnClickListener {
                val intent = Intent(itemView.context, SpotDetailsActivity::class.java)
                intent.putExtra("spotName", spot.name)
                itemView.context.startActivity(intent)
            }
        }
    }

    fun filter(query: String) {
        spots = if (query.isEmpty()) {
            spotsBackup
        } else {
            spotsBackup.filter { it.name.startsWith(query, true) }
        }
        notifyDataSetChanged()
    }
}
