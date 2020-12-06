package com.techelevator.houseplant_recommender.controller;

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
@RequestMapping("/random")

public class RandomController {
	
	private PlantDAO dao;

    public RandomController(JdbcTemplate jdbcTemplate) {
        this.dao = new PlantJDBCDAO(jdbcTemplate);
    }	
	
    @RequestMapping( path = "", method = RequestMethod.GET)
    public Plant getRandomPlant(){
        return dao.getRandomPlant();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "/save", method = RequestMethod.POST)
    public void savePlantToMyPlants(@RequestBody Plant savedPlant){
        dao.savePlantToMyPlants(savedPlant);
    }

}
