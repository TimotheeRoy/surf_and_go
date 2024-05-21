package main

type SpotDB struct {
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

type ApiResponse struct {
    Location Location `json:"Location"`
}

type Location struct {
	Name           string  `json:"name"`
	Region         string  `json:"region"`
	Country        string  `json:"country"`
	Lat            float64 `json:"lat"`
	Lon            float64 `json:"lon"`
	TzID           string  `json:"tz_id"`
	LocaltimeEpoch int64   `json:"localtime_epoch"`
	Localtime      string  `json:"localtime"`
}
