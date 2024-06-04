package main

type SpotDB struct {
	Name      string
	Latitude  string
	Longitude string
}

type ApiResponse struct {
	Location Location `json:"Location"`
	Forecast Forecast `json:"Forecast"`
}

type Location struct {
	Name      string `json:"name"`
	Country   string `json:"country"`
	Localtime string `json:"localtime"`
}

// Forecast structure
type Forecast struct {
	Forecastday []Forecastday `json:"forecastday"`
}

// Forecastday structure
type Forecastday struct {
	Day  Day    `json:"day"`
	Hour []Hour `json:"hour"`
}

// Day structure
type Day struct {
	Tides []Tides `json:"tides"`
}

// Tides structure
type Tides struct {
	Tide []Tide `json:"tide"`
}

// Tide structure
type Tide struct {
	TideTime     string `json:"tide_time"`
	TideHeightMt string `json:"tide_height_mt"`
	TideType     string `json:"tide_type"`
}

// Condition structure
type Condition struct {
	Text string `json:"text"`
	Icon string `json:"icon"`
}

// Hour structure
type Hour struct {
	Time  string  `json:"time"`
	TempC float64 `json:"temp_c"`

	Condition Condition `json:"condition"`
	WindKph   float64   `json:"wind_kph"`
	WindDir   string    `json:"wind_dir"`

	Uv              float64 `json:"uv"`
	BigWavesHeight  float64 `json:"sig_ht_mt"`
	SwellHtMt       float64 `json:"swell_ht_mt"`
	SwellDir        float64 `json:"swell_dir"`
	SwellPeriodSecs float64 `json:"swell_period_secs"`
	WaterTempC      float64 `json:"water_temp_c"`
}