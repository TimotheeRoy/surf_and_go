package com.example.surf_and_go

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Spot (val name: String, val location: String, val image: String)
class SpotsList : AppCompatActivity() {
    private val spots = arrayOf(
        Spot("Hendaye", "Pays Basque, France", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-29PHKbKLloaBoGE-SLOk2C_Ix9sPa9pHWkBC6y-cigl120DTrpucm47iLJ9-Q9Dqlaw&usqp=CAU"),
        Spot("La Côte des Basques", "Pays Basque, France", "https://cdt64.media.tourinsoft.eu/upload/Plage-de-la-Cote-des-Basques--Biarritz---Vue.jpg?width=1800"),
        Spot("La Néra", "Bourail, Nouvelle-Calédonie", "https://www.nouvellecaledonie.travel/app/uploads/nouvelle-caledonie/2023/02/thumbs/Surf-a-Bourail-Ben-Thouard-2029-07-16_v2-640x320-crop-1677039010.jpg"),
        Spot("Nazaré", "Leiria, Portugal", "https://img.redbull.com/images/c_crop,w_1497,h_748,x_3,y_97,f_auto,q_auto/c_scale,w_1200/redbullcom/2017/03/06/1331847907687_2/surf-gros-nazare-lucas-chumbinho"),
        Spot("Hossegor", "Pays Basque, France", "https://sportihome.com/uploads/spots/59a70f35b27eb115986b6247/large/1504121018914.jpg"),
        Spot("Passe de Dumbéa", "Nouméa, Nouvelle-Calédonie", "https://www.pacific-good-deal.com/wp-content/uploads/2023/08/1.webp"),
        Spot("Torami", "Chiba, Japan", "https://www.tokyoweekender.com/wp-content/uploads/2015/06/Kamogawa-surfing.png"),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spots_list)
        // cache la bar d'action
        supportActionBar?.hide()

        // On selectionne l'endroit où afficher
        val recyclerView: RecyclerView = findViewById(R.id.list)
        // On définit la façon dont on affiche la liste
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = SpotAdapter(spots)
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

            //Bouton qui sert à aller voir le détail d'un spot

            //pointeur "itemView" pour aller chercher l'élément par son ID
            val button = itemView.findViewById<Button>(R.id.spot_btn)
            //Dans le bouton, affiche le nom du spot
            button.text = spot.name

            //Quand on clique, redirige vers "SpotDetails"
            button.setOnClickListener{
                val intent = Intent(itemView.context, SpotDetails::class.java)
                //"Putextra" envoi la data ailleurs
                intent.putExtra("spotName", spot.name)
                intent.putExtra("spotImage", spot.image)
                intent.putExtra("spotLocation", spot.location)

                itemView.context.startActivity(intent)
            }
        }
    }
}






