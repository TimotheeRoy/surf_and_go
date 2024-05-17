package main

type SpotDB struct {
	_id       string
	City      string
	Latitude  string
	Longitude string
}

type Spot struct {
	Name              string
	Description       string
	Horaire           string
	Photo             string
	Niveau            string
	Taille_des_vagues string
	Location_matos    bool
	Meteo             string
	Vent              int
}

type SpotsName struct {
	Name string
}
