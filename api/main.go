package main

import (
	"log"
	"net/http"

	"github.com/gin-gonic/gin"
)

func main() {

	router := gin.Default()

	router.GET("/spot/:name", func(c *gin.Context) {
		name := c.Param("name") // on recup le parametre name de la route
		c.JSON(http.StatusOK, getMeteo(name))
	})

	router.GET("/spot", func(c *gin.Context) {
		c.JSON(http.StatusOK, getAllSpots())
	})

	//Serveur qui run
	router.Run(":8080")

	log.Println("Starting server on port 8080")
}
