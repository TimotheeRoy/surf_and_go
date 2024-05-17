package main

import (
	"log"
	"net/http"

	"github.com/gin-gonic/gin"
)

func main() {

	router := gin.Default()

	router.GET("/:name", func(c *gin.Context) {
		name := c.Param("name") // on recup le parametre name du
		c.JSON(http.StatusOK, getSpotDetailsFromName(name))
	})

	router.GET("/spotslist", func(c *gin.Context) {
		c.JSON(http.StatusOK, getAllSpots())
	})

	//Serveur qui run
	router.Run()

	log.Println("Starting server on port 8080")
}
