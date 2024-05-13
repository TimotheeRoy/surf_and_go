package com.example.surf_and_go

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView

data class Spot (val name: String, val location: String, val image: String)
class SpotsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_spots_list)

        // cache la barre d'action
        supportActionBar?.hide()

        // Récupération de la référence à la SearchView par son ID
        val searchView: SearchView = findViewById(R.id.edit_text_input)
        //TODO: si on veut changer couleur croix, peut être faire "searchView.set qqch

        // Récupération de la référence à l'EditText de la SearchView par son ID
        val searchTextView: EditText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        // texte initial et sa couleur
        searchTextView.setHint("Search")
        searchTextView.setHintTextColor(Color.parseColor("#B6C3AD7D"))
        // couleur du texte de recherche
        searchTextView.setTextColor(Color.BLACK)

        // "écoute" chaque lettre tapée
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            // fonction appelée à l'appui de la touche entrée (ne sert pas)
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            // fonction appelée à chaque changement du texte
            override fun onQueryTextChange(query: String?): Boolean {
                // récupération de la RecyclerView par son ID
                val recyclerView: RecyclerView = findViewById(R.id.list)
                // récupération de l'adapter de la RecyclerView
                val adapter: SpotAdapter = recyclerView.adapter as SpotAdapter
                if (query != null) {
                    // appel de la fonction de filtrage avec le texte tapé
                    adapter.filter(query)
                }
                return true
            }
        })

        // On selectionne l'endroit où afficher la liste
        val recyclerView: RecyclerView = findViewById(R.id.list)
        // On définit les actions sur la liste
        recyclerView.adapter = SpotAdapter()
    }
}

class SpotAdapter() : RecyclerView.Adapter<SpotAdapter.SpotViewHolder>() {
    private var spots = listOf(
        Spot("Hendaye", "Pays Basque, France", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-29PHKbKLloaBoGE-SLOk2C_Ix9sPa9pHWkBC6y-cigl120DTrpucm47iLJ9-Q9Dqlaw&usqp=CAU"),
        Spot("La Côte des Basques", "Pays Basque, France", "https://cdt64.media.tourinsoft.eu/upload/Plage-de-la-Cote-des-Basques--Biarritz---Vue.jpg?width=1800"),
        Spot("La Néra", "Bourail, Nouvelle-Calédonie", "https://www.nouvellecaledonie.travel/app/uploads/nouvelle-caledonie/2023/02/thumbs/Surf-a-Bourail-Ben-Thouard-2029-07-16_v2-640x320-crop-1677039010.jpg"),
        Spot("Nazaré", "Leiria, Portugal", "https://img.redbull.com/images/c_crop,w_1497,h_748,x_3,y_97,f_auto,q_auto/c_scale,w_1200/redbullcom/2017/03/06/1331847907687_2/surf-gros-nazare-lucas-chumbinho"),
        Spot("Hossegor", "Pays Basque, France", "https://sportihome.com/uploads/spots/59a70f35b27eb115986b6247/large/1504121018914.jpg"),
        Spot("Passe de Dumbéa", "Nouméa, Nouvelle-Calédonie", "https://www.pacific-good-deal.com/wp-content/uploads/2023/08/1.webp"),
        Spot("Torami", "Chiba, Japan", "https://www.tokyoweekender.com/wp-content/uploads/2015/06/Kamogawa-surfing.png"),
        Spot("Grande Anse du Diamant", "Martinique, France", "https://www.guidemartinique.com/images/plages/grande-anse-diamant-336.jpg"),
    )

    // sauvegarde des spots
    private val spotsBackup = spots

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
                val intent = Intent(itemView.context, SpotDetailsActivity::class.java)
                //"Putextra" envoi la data ailleurs
                intent.putExtra("spotName", spot.name)
                intent.putExtra("spotImage", spot.image)
                intent.putExtra("spotLocation", spot.location)

                itemView.context.startActivity(intent)
            }
        }
    }

    // Fonction de filtrage des spots
    fun filter(query: String) {
        // filtrage des spots (la variable "spots" étant modifiée, on part toujours de la sauvegarde "spotsBackup")
        spots = spotsBackup.filter { s: Spot -> s.name.startsWith(query, true) }
        // appel de cette fonction interne à l'adapter lui spécifiant un changement des données pour qu'il change l'affichage
        notifyDataSetChanged()
    }
}
