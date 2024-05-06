package com.example.surf_and_go

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Spot (val name: String, val location: String, val image: String)
class SpotsListActivity : AppCompatActivity() {

    private val spots = arrayOf(
        Spot("Hendaye", "Pays Basque, France", ""),
        Spot("Côte des Basques", "Pays Basque, France", ""),
        Spot("La Néra", "Bourail, Nouvelle-Calédonie", ""),
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


        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_between_spots)
        recyclerView.addItemDecoration(SpacingItemDecoration(spacingInPixels))
    }
}

class SpotAdapter(private val spots: Array<Spot>) : RecyclerView.Adapter<SpotAdapter.SpotViewHolder>() {
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
            itemView.findViewById<Button>(R.id.spot_btn).text = spot.name
        }
    }
}

class SpacingItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) != parent.adapter?.itemCount?.minus(1)) {
            outRect.bottom = spaceHeight
        }
    }
}