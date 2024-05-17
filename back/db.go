package main

import (
	"context"

	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

func Connectdb() *mongo.Client {
	uri := "mongodb+srv://leastattner:IdiyWmR0hb6FjPdf@surfandgo.xamxbka.mongodb.net/?retryWrites=true&w=majority&appName=SURFANDGO"

	client, err := mongo.Connect(context.TODO(), options.Client().
		ApplyURI(uri))
	if err != nil {
		panic(err)
	}

	return client
}

func Disconnectdb(client *mongo.Client) {
	client.Disconnect((context.TODO()))
}

// getSpotDetailsFromName récupère les détails d'un spot de surf basé sur son nom de ville.
func getSpotDetailsFromName(name string) SpotDB {
	client := Connectdb()

	// Accès à la collection "spots" de la base de données "surfAndGo".
	coll := client.Database("surfAndGo").Collection("spots")

	// Création d'un filtre pour rechercher un document où le champ "city" correspond au nom fourni.
	filter := bson.D{{Key: "city", Value: name}}

	// Déclaration d'une variable pour stocker le résultat de la requête.
	var result SpotDB

	// Exécution de la requête pour trouver un document correspondant au filtre
	// FindOne : 2 paramètres -> un contexte, ici pas clair donc context.TODO() (pas une bonne paratique)
	//						  -> un filtre, ici filter qui une variable de type BSON (un json binaire)
	// la fonction retourne un pointeur (uber)
	theOne := coll.FindOne(context.TODO(), filter)

	// Décode le résultat de la requête et le stocke à l'adresse de la variable result
	err := theOne.Decode(&result)

	// Si une erreur survient (par exemple, si aucun document n'est trouvé), elle est gérée ici.
	if err != nil {
		// panic interrompt l'exécution du programme en cas d'erreur.
		panic(err)
	}

	// Déconnexion de la base de données MongoDB.
	Disconnectdb(client)

	// Retourne le résultat contenant les détails du spot de surf.
	return result
}

func getAllSpots() []SpotsName {
	client := Connectdb()

	coll := client.Database("surfAndGo").Collection("spots")

	filter := bson.D{}

	var result []SpotsName

	theAll, err := coll.Find(context.TODO(), filter)
	println("Ici c'est The ALL go: ", theAll)
	// Si une erreur survient (par exemple, si aucun document n'est trouvé), elle est gérée ici.
	if err != nil {
		// panic interrompt l'exécution du programme en cas d'erreur.
		panic(err)
	}

	err = theAll.Decode(&result)

	// Si une erreur survient (par exemple, si aucun document n'est trouvé), elle est gérée ici.
	if err != nil {
		// panic interrompt l'exécution du programme en cas d'erreur.
		panic(err)
	}

	// Déconnexion de la base de données MongoDB.
	Disconnectdb(client)

	// Retourne le résultat contenant les détails du spot de surf.
	return result
}
