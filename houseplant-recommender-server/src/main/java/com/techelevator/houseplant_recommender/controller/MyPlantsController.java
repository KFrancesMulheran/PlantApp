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
@RequestMapping("/myplants")

public class MyPlantsController {
	
	private PlantDAO dao;

    public MyPlantsController(JdbcTemplate jdbcTemplate) {
        this.dao = new PlantJDBCDAO(jdbcTemplate);
    }	
    
    
    @RequestMapping( path = "", method = RequestMethod.GET)
    public List<Plant> viewMyPlants(){
        return dao.viewMyPlants();
    }
	
    @RequestMapping( path = "/plantdetail", method = RequestMethod.GET)
    public Plant viewMyPlantPlantDetails(Plant selectedPlant){
        return dao.viewMyPlantPlantDetails(selectedPlant);
    }
    
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @RequestMapping( path = "/delete", method = RequestMethod.DELETE)
    public void deletePlantFromMyPlants(@RequestBody Plant unwantedPlant){
        dao.deletePlantFromMyPlants(unwantedPlant);
    }
    
    //I look forward to adding more functionality in the future

}
