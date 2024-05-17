[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/QCl2HpkT)

# BRICE PROJECT

Surf_And_Go

## 1. DESCRIPTION DE L'APPLICATON

Météo des plages 2.0 pour surfeurs confirmés et débutants 

## 2. OBJECTIF DE l'APPLICATION

- Donner des informations relatives aux spots de plages tels que :  

	    - Horaire de la marée
		- Le vent
		- Température de l’eau
		- Niveau recommandé de surf
		- Taille des vagues
		- Note Brice de Nice
		- Le type de plage (cailloux ou sables)
		- Photo de la plage
		- Plan et/ou itinéraire
		- Location de matériel à coté ou pas ?
		- commentaire des utilisateurs
		- Début et fin de la saison pour le surf
- Permettre un référencement global de tous les spots de surf, d’abord au niveau national puis international  

- Créer une communauté de surfer

## 3. PERIMETRE  
 Application en français pour le MVP  

Modules existant sur web à intégrer sur l’app :  
  - géolocalisation  
  -  création de compte
## 4. EQUIPEMENT DE NOS CIBLES
L'application devra être compatible avec mobiles et tablettes ANDROID uniquement

## Documentation

[Documentation Kotlin Compose](https://www.w3schools.com/kotlin/kotlin_functions.php)


## Tech Stack

**Client:** Kotlin

**Server:** Go


## API Reference

#### Weather API - OpenWeather

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |



