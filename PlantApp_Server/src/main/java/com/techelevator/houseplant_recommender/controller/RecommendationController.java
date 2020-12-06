package com.techelevator.houseplant_recommender.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.houseplant_recommender.dao.PlantDAO;
import com.techelevator.houseplant_recommender.dao.PlantJDBCDAO;
import com.techelevator.houseplant_recommender.model.Plant;



@RestController
@RequestMapping("/recommend")

public class RecommendationController {
	
	private PlantDAO dao;

    public RecommendationController(JdbcTemplate jdbcTemplate) {
        this.dao = new PlantJDBCDAO(jdbcTemplate);
    }	  

	
    @RequestMapping( path = "", method = RequestMethod.GET)
    public List<Plant> recommendPlants(String careDifficulty, String lightNeeds, boolean prefersHumidity,
			boolean petSafe, boolean unusual, boolean hangingBasket, boolean succulent){
        return dao.recommendPlants(careDifficulty, lightNeeds, prefersHumidity,
        	petSafe, unusual, hangingBasket, succulent);
    }
	
    @RequestMapping( path = "/plantdetail", method = RequestMethod.GET)
    public Plant viewPlantDetails(int plantId){
        return dao.viewPlantDetails(plantId);
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "/save", method = RequestMethod.POST)
    public void savePlantToMyPlants(@RequestBody Plant savedPlant){
        dao.savePlantToMyPlants(savedPlant);
    }
	
}
