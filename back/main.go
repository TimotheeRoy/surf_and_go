package main
import ("fmt")

type Spot struct {

	name string
	description string
	horaire string
	photo string
	niveau string
	taille_des_vagues string
	location_matos bool
	meteo string
	vent int

}

func main() {
	Spots := []Spot{
		{
			name:             "Biarritz",
			description:      "Spot de surf emblématique en France, connu pour ses vagues puissantes et ses paysages magnifiques.",
			horaire:          "6h - 21h",
			photo:            "biarritz.jpg",
			niveau:           "Intermédiaire à Avancé",
			taille_des_vagues: "1m - 4m",
			location_matos:   true,
			meteo:            "Ensoleillé",
			vent:             15,
		},
		{
			name:             "Hossegor",
			description:      "Réputé pour ses vagues tubulaires et ses compétitions internationales de surf.",
			horaire:          "6h - 20h",
			photo:            "hossegor.jpg",
			niveau:           "Avancé",
			taille_des_vagues: "1.5m - 5m",
			location_matos:   true,
			meteo:            "Partiellement nuageux",
			vent:             20,
		},
		{
			name:             "La Torche",
			description:      "Spot de surf en Bretagne, parfait pour les surfeurs de tous niveaux, offrant une grande variété de vagues.",
			horaire:          "7h - 19h",
			photo:            "la_torche.jpg",
			niveau:           "Tous niveaux",
			taille_des_vagues: "0.5m - 3m",
			location_matos:   false,
			meteo:            "Pluvieux",
			vent:             10,
		},
		{
			name:             "Lacanau",
			description:      "Un des spots de surf les plus connus en Gironde, offrant des vagues régulières et une ambiance conviviale.",
			horaire:          "6h - 22h",
			photo:            "lacanau.jpg",
			niveau:           "Débutant à Intermédiaire",
			taille_des_vagues: "1m - 3m",
			location_matos:   true,
			meteo:            "Ensoleillé avec quelques nuages",
			vent:             12,
		},
		{
			name:             "Cap Ferret",
			description:      "Spot de surf populaire sur la côte atlantique, avec des vagues adaptées à tous les niveaux de surfeurs.",
			horaire:          "6h - 20h",
			photo:            "cap_ferret.jpg",
			niveau:           "Tous niveaux",
			taille_des_vagues: "0.5m - 2.5m",
			location_matos:   false,
			meteo:            "Nuageux",
			vent:             8,
		},
	}

}	
