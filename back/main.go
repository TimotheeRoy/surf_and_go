package main

import (
	"log"
	"net/http"

	"github.com/gin-gonic/gin"
)

type Spot struct {
	name              string
	description       string
	horaire           string
	photo             string
	niveau            string
	taille_des_vagues string
	location_matos    bool
	meteo             string
	vent              int
}

func main() {
	Spots := []Spot{
		{
			Name:              "Biarritz",
			Description:       "Spot de surf emblématique en France, connu pour ses vagues puissantes et ses paysages magnifiques.",
			Horaire:           "6h - 21h",
			Photo:             "biarritz.jpg",
			Niveau:            "Intermédiaire à Avancé",
			Taille_des_vagues: "1m - 4m",
			Location_matos:    true,
			Meteo:             "Ensoleillé",
			Vent:              15,
		},
		{
			Name:              "Hossegor",
			Description:       "Réputé pour ses vagues tubulaires et ses compétitions internationales de surf.",
			Horaire:           "6h - 20h",
			Photo:             "hossegor.jpg",
			Niveau:            "Avancé",
			Taille_des_vagues: "1.5m - 5m",
			Location_matos:    true,
			Meteo:             "Partiellement nuageux",
			Vent:              20,
		},
		{
			Name:              "La Torche",
			Description:       "Spot de surf en Bretagne, parfait pour les surfeurs de tous niveaux, offrant une grande variété de vagues.",
			Horaire:           "7h - 19h",
			Photo:             "la_torche.jpg",
			Niveau:            "Tous niveaux",
			Taille_des_vagues: "0.5m - 3m",
			Location_matos:    false,
			Meteo:             "Pluvieux",
			Vent:              10,
		},
		{
			Name:              "Lacanau",
			Description:       "Un des spots de surf les plus connus en Gironde, offrant des vagues régulières et une ambiance conviviale.",
			Horaire:           "6h - 22h",
			Photo:             "lacanau.jpg",
			Niveau:            "Débutant à Intermédiaire",
			Taille_des_vagues: "1m - 3m",
			Location_matos:    true,
			Meteo:             "Ensoleillé avec quelques nuages",
			Vent:              12,
		},
		{
			Name:              "Cap Ferret",
			Description:       "Spot de surf populaire sur la côte atlantique, avec des vagues adaptées à tous les niveaux de surfeurs.",
			Horaire:           "6h - 20h",
			Photo:             "cap_ferret.jpg",
			Niveau:            "Tous niveaux",
			Taille_des_vagues: "0.5m - 2.5m",
			Location_matos:    false,
			Meteo:             "Nuageux",
			Vent:              8,
		},
	}

	router := gin.Default()
	router.GET("/", func(c *gin.Context) {
		c.JSON(http.StatusOK, gin.H{
			"message": Spots,
		})
	})

	//Serveur qui run
	router.Run()

	log.Println("Starting server on port 8080")
}
