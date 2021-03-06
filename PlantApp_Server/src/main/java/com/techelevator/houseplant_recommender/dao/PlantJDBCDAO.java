package com.techelevator.houseplant_recommender.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

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
	
	public PlantJDBCDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	  
	  
	@Override
	public Plant getRandomPlant(){
		
	Plant randomPlant = new Plant(); 
		
	Random random = new Random();
	int searchId = random.nextInt(100);
	boolean searchIdExists = false;	
	
	String sql = "SELECT * FROM plants WHERE plant_id = ?";

		while (searchIdExists == false) {
		
			SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, searchId);
		
			if(rowSet.next()) {
				randomPlant = mapRowToPlant(rowSet);
				searchIdExists = true;
			} else {
				searchId = random.nextInt(100);
			}
		}
		return randomPlant;	
	}
	
	
	
	@Override
	public List<Plant> recommendPlants(String careDifficulty, String lightNeeds, boolean prefersHumidity,
						boolean petSafe, boolean unusual, boolean hangingBasket, boolean succulent) {
		
		List<Plant> recommendedPlants = new ArrayList();
		
		String sql = "SELECT * FROM plants WHERE " +
					 "care_difficulty = ? AND " +
					 "light_needs = ? AND " +
					 "prefers_humidity = ? AND " +
					 "pet_safe = ? AND " +
					 "is_unusual = ? AND " +
					 "hanging_basket = ? AND " +
					 "is_succulent = ?";
				
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, careDifficulty, lightNeeds, prefersHumidity,
						petSafe, unusual, hangingBasket, succulent);
        
        while(results.next()) {
            Plant plant = mapRowToPlant(results);
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
	public List<Plant> searchByName(String name) {
		
		List<Plant> searchedPlant = new ArrayList();
		
		//This is a better search, once I figure out how to implement it properly,
		//I will  update this method
		//String sql = "SELECT * FROM plants WHERE plant_name ILIKE '% ? %' ";
		
		String sql = "SELECT * FROM plants WHERE plant_name = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
		
        while(results.next()) {
            Plant plant = mapRowToPlant(results);
            searchedPlant.add(plant);
		
        }
		return searchedPlant;		
	}
	
	
	
	@Override
	public void savePlantToMyPlants(Plant savedPlant) {
		
		String sql = "INSERT INTO my_plants (plant_id) VALUES (?)";
	
		jdbcTemplate.update(sql, savedPlant.getPlantId());	
		
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
		
		String sql = "DELETE FROM my_plants WHERE plant_id = " +
				"(SELECT plant_id FROM plants WHERE plant_name = ?);";
		
		jdbcTemplate.update(sql, unwantedPlant.getPlantName());	
		
	}
		
	@Override
	public Plant viewMyPlantPlantDetails(int plantId) {
		
		Plant thisPlant = new Plant();
		
		String sql = "SELECT * FROM my_plants INNER JOIN plants USING (plant_id) WHERE plant_id = ?";
		
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, plantId);
        
        if(results.next()) {
        thisPlant= mapRowToPlant(results);
        }
        return thisPlant;	
	}
	
	@Override
	public Plant viewPlantDetails(int plantId) {
		
		Plant thisPlant = new Plant();
		
		String sql = "SELECT * FROM plants WHERE plant_id = ?;";
		
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, plantId);
        
        if(results.next()) {
        thisPlant= mapRowToPlant(results);
        }
        return thisPlant;	
	}

	
    private Plant mapRowToPlant(SqlRowSet rs) {
    	
        Plant plant = new Plant();
        
        plant.setPlantId(rs.getInt("plant_id"));
        plant.setPlantName(rs.getString("plant_name"));
        plant.setScientificName(rs.getString("scientific_name"));
        plant.setCareDifficulty(rs.getString("care_difficulty"));
        plant.setLightNeeds(rs.getString("light_needs"));
        plant.setPrefersHumidity(rs.getBoolean("prefers_humidity"));
        plant.setPetSafe(rs.getBoolean("pet_safe"));
        plant.setUnusual(rs.getBoolean("is_unusual"));
        plant.setHangingBasket(rs.getBoolean("hanging_basket"));
        plant.setSucculent(rs.getBoolean("is_succulent"));
        plant.setCareInstructions(rs.getString("care_instructions"));

        return plant;
    }

    
    private Plant mapRowToMyPlants(SqlRowSet rs) {
    	
    	Plant myPlant = new Plant();
    	
    	myPlant.setPlantName(rs.getString("plant_name"));
    	
    	return myPlant;
    }






    
}
