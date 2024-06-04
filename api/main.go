package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

func Handler(w http.ResponseWriter, r *http.Request) {

	router := gin.Default()

	router.GET("/spot/:name", func(c *gin.Context) {
		name := c.Param("name") // on recup le parametre name de la route
		c.JSON(http.StatusOK, getMeteo(name))
	})

	router.GET("/spot", func(c *gin.Context) {
		c.JSON(http.StatusOK, getAllSpots())
	})

	//Serveur qui run
	router.ServeHTTP(w, r)
}
