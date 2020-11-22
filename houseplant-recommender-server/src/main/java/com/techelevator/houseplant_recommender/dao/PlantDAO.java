package com.techelevator.houseplant_recommender.dao;

import java.util.List;
import  com.techelevator.houseplant_recommender.model.Plant;

public interface PlantDAO {

	public Plant getRandomPlant();
	
	public List<Plant> recommendPlants();
	
	public void savePlantToMyPlants(Plant savedPlant);
	
	public List<Plant> viewMyPlants();
	
	public void deletePlantFromMyPlants(Plant unwantedPlant);
	
	public Plant viewPlantDetails(Plant selectedPlant);
	
	public Plant viewMyPlantPlantDetails(Plant selectedPlant);

	public List<Plant> recommendPlants(String careDifficulty, String lightNeeds, String prefersHumidity, boolean petSafe,
			boolean unusual, boolean hangingBasket, boolean succulent);
	
	public Plant searchByName(String name);

	
		
}
