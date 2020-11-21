package com.techelevator.houseplant_recommender.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.houseplant_recommender.model.Plant;


@Component
public class PlantJDBCDAO implements PlantDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public PlantJDBCDAO(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }
	
	  
	  
	@Override
	public Plant getRandomPlant(){
		
	Plant randomPlant = new Plant();
		
	Random random = new Random();
	int searchId = random.nextInt(100);
	boolean searchIdExists = false;	
	
	String sql = "SELECT * FROM plants WHERE plant_id = ?";

		while (!searchIdExists) {
		
			SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, searchId);
		
			if(rowSet.next()) {
				randomPlant = mapRowToRandomPlant(rowSet);
				searchIdExists = true;
			} else {
				int newSearchId = random.nextInt(100);
			}
		}
		return randomPlant;	
	}
	
	
	
	@Override
	public List<Plant> recommendPlants(String careDifficulty, String lightNeeds, String prefersHumidity,
						boolean petSafe, boolean unusual, boolean hangingBasket, boolean succulent) {
		
		List<Plant> recommendedPlants = new ArrayList();
		
		String sql = "SELECT * FROM plants WHERE " +
					 "care_difficulty = ? && " +
					 "light_needs = ? && " +
					 "prefers_humidity = ? && " +
					 "pet_safe = ? && " +
					 "is_unusual = ? && " +
					 "hanging_basket = ? && " +
					 "is_succulent = ?";
				
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, careDifficulty, lightNeeds, prefersHumidity,
						petSafe, unusual, hangingBasket, succulent);
        
        while(results.next()) {
            Plant plant = mapRowToRandomPlant(results);
            recommendedPlants.add(plant);
		
        }
		return recommendedPlants;	
	}
	
	@Override
	public List<Plant> recommendPlants() {
		// with fewer parameters
		return null;
	}
	
	@Override
	public Plant searchByName(String name) {
		
		Plant searchedPlant = new Plant();
		
		String sql = "SELECT * FROM plants WHERE plant_name ILIKE ?";
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, name);
		
		if(rowSet.next()) {
			searchedPlant = mapRowToRandomPlant(rowSet);
		}
		
		return searchedPlant;
		
	}
	
	
	
	@Override
	public void savePlantToMyPlants(Plant savedPlant) {
		
		String sql = "INSERT INTO my_plants (plant_number, plant_id) VALUES (DEFAULT, ?)";
	
		jdbcTemplate.update(sql, savedPlant.getPlantNumber(), savedPlant.getPlantId());	
		
	}
	
	@Override
	public List<Plant> viewMyPlants() {
		
		List<Plant> myPlants = new ArrayList();
		
		String sql = "SELECT plant_name FROM my_plants JOIN plants USING (plant_id)";
		
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        
        while(results.next()) {
            Plant plant = mapRowToMyPlants(results);
            myPlants.add(plant);
		
        }
        return myPlants;	
	}
	
	@Override
	public void deletePlantFromMyPlants(Plant unwantedPlant) {
		
		String sql = "DELETE FROM my_plants JOIN plants USING plant_id WHERE plant_name = ?";
		
		jdbcTemplate.update(sql, unwantedPlant.getPlantName());	
		
	}
		
	@Override
	public Plant viewPlantDetailsFromMyPlants(Plant selectedPlant) {
		
		Plant thisPlant = new Plant();
		
		String sql = "SELECT * FROM my_plants JOIN plants USING (plant_id) WHERE plant_name = ?";
		
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        
        if(results.next()) {
        thisPlant= mapRowToRandomPlant(results);
        }
        return thisPlant;	
	}
	
	

	
    private Plant mapRowToRandomPlant(SqlRowSet rs) {
    	
        Plant randomPlant = new Plant();
        
        randomPlant.setPlantId(rs.getInt("plant_id"));
        randomPlant.setPlantName(rs.getString("plant_name"));
        randomPlant.setScientificName(rs.getString("scientific_name"));
        randomPlant.setCareDifficulty(rs.getString("care_difficulty"));
        randomPlant.setLightNeeds(rs.getString("light_needs"));
        randomPlant.setPrefersHumidity(rs.getBoolean("prefers_humidity"));
        randomPlant.setPetSafe(rs.getBoolean("pet_safe"));
        randomPlant.setUnusual(rs.getBoolean("is_unusual"));
        randomPlant.setHangingBasket(rs.getBoolean("hanging_basket"));
        randomPlant.setSucculent(rs.getBoolean("is_succulent"));
        randomPlant.setCareInstructions(rs.getString("care_instructions"));

        return randomPlant;
    }

    
    private Plant mapRowToMyPlants(SqlRowSet rs) {
    	
    	Plant myPlant = new Plant();
    	
    	myPlant.setPlantName(rs.getString("plant_name"));
    	
    	return myPlant;
    }






    
}
