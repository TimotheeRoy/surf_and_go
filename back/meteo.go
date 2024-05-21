package main

import (
	"encoding/json"
	"fmt"
	"io"
	"net/http"
)

// Appel de l'API Météo Weather

func getMeteo(name string) Location {
	// Stock 1ere partie de l'URL
	startUrl := "http://api.weatherapi.com/v1/marine.json?key=3321138404bc4d6bbed101345242105&hour=14&q="  // faudra faire un .env pour la clé api et même l'url en vrai 

	// Réccupérer les coordonées (longitude et latitude)
	spot := getSpotDetailsFromName(name)

	longitute := spot.Longitude
	latitude := spot.Latitude


	// URL final à fetch
	url := startUrl + latitude + "," + longitute

	// Fetch
	resp, err := http.Get(url)
	if err != nil {
		fmt.Printf("Erreur lors de l'envoi de la requête : %v\n", err)
		panic(err)
	}

	// Lecture de ce qu'on a fetch, retourne un tableau de byte
	defer resp.Body.Close()
	body, err := io.ReadAll(resp.Body)
	if err != nil {
		fmt.Printf("Erreur lors de la lecture de la réponse : %v\n", err)
		panic(err)
	}

	// Traduit le tableau de byte en JSON
	var result ApiResponse
	if err := json.Unmarshal(body, &result); err != nil {
		fmt.Printf("Erreur lors de la désérialisation du JSON : %v\n", err)
		panic(err)
	}

	return result.Location
}