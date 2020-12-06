package com.techelevator.houseplant_recommender.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.houseplant_recommender.dao.PlantDAO;
import com.techelevator.houseplant_recommender.dao.PlantJDBCDAO;
import com.techelevator.houseplant_recommender.model.Plant;


@RestController
@RequestMapping("/search")

public class SearchController {
	
	private PlantDAO dao;

    public SearchController(JdbcTemplate jdbcTemplate) {
        this.dao = new PlantJDBCDAO(jdbcTemplate);
    }	
    
    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public List<Plant> searchByName(@PathVariable String name) {
    	return dao.searchByName(name);
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "/save", method = RequestMethod.POST)
    public void savePlantToMyPlants(@RequestBody Plant savedPlant){
        dao.savePlantToMyPlants(savedPlant);
    }

}
