package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
)


// Appel de l'API Météo Weather

func meteo() {
	// Stock 1ere partie de l'URL 
	commentlappeler := "http://api.weatherapi.com/v1/marine.json?key=20d5504a766049328a285729241705&q="

	// Réccupérer les coordonées (longitude et latitude)
	getSpotsDsFromName ()



}

/*	

	// Faire la requête GET
	resp, err := http.Get(url)
	if err != nil {
		// Gérer l'erreur
		fmt.Println("Erreur lors de la requête:", err)
		return
	}
	// Fermer le corps de la réponse à la fin de la fonction
	defer resp.Body.Close()

	// Lire le corps de la réponse
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		// Gérer l'erreur de lecture
		fmt.Println("Erreur lors de la lecture du corps:", err)
		return
	}

	// Afficher le corps de la réponse
	fmt.Println(string(body))
