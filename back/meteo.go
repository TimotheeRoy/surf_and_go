package main

import (
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"os"
	"strconv"
	"time"

	"github.com/joho/godotenv"
)

// Appel de l'API Météo Weather

func getMeteo(name string) ApiResponse {
	// Stock 1ere partie de l'URL
	err := godotenv.Load()
	if err != nil {
		panic(err)
	}

	API_URL := os.Getenv("API_URL")

	// Réccupérer les coordonées (longitude et latitude) et l'heure actuelle
	spot := getSpotDetailsFromName(name)

	longitute := spot.Longitude
	latitude := spot.Latitude

	hour := time.Now().Hour()


	// URL final à fetch
	url := API_URL + strconv.Itoa(hour) + "&q=" + latitude + "," + longitute

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

	return result
}
