package com.example.surf_and_go

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Spot (val name: String, val location: String, val image: String)
class SpotsList : AppCompatActivity() {
    val spots = arrayOf(
        Spot("Hendaye", "Pays Basque", "")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spots_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView: RecyclerView = findViewById(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SpotAdapter(spots)
    }
}
class SpotAdapter(private val spots: Array<Spot>) : RecyclerView.Adapter<SpotAdapter.SpotViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_spots_list, parent, false)
        return SpotViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpotViewHolder, position: Int) {
        val spot = spots[position]
        holder.bind(spot)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, SpotsList::class.java)

            intent.putExtra("spot_name", spot.name)
            intent.putExtra("spot_location", spot.location)
            intent.putExtra("spot_img", spot.image)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return spots.size
    }

    inner class SpotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(spot: Spot) {
            itemView.findViewById<TextView>(R.id.list).text = spot.name
        }
    }
}






