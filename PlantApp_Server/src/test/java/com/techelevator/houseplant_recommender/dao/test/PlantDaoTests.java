package com.techelevator.houseplant_recommender.dao.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.houseplant_recommender.dao.PlantDAO;
import com.techelevator.houseplant_recommender.dao.PlantJDBCDAO;
import com.techelevator.houseplant_recommender.model.Plant;



public class PlantDaoTests {
	
	private static SingleConnectionDataSource dataSource;
	private PlantDAO dao;

	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/plants");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	@Before
	public void setup() {
		dao = new PlantJDBCDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	

	@Test
	public void random_plant_returns_a_random_plant() {

		Plant randomPlantOne = dao.getRandomPlant();
		Plant randomPlantTwo = dao.getRandomPlant();

		assertNotNull(randomPlantOne);
		assertNotNull(randomPlantTwo);
		 
		assertNotEquals(randomPlantOne, randomPlantTwo);
		
	}
	
	@Test
	public void recommend_plants_recommends_plant_one() {

		List<Plant> recommendedPlants = dao.recommendPlants("Beginner", "Low", false, false, false, false, true);

		assertNotNull(recommendedPlants);
		assertEquals(1, recommendedPlants.size());
		assertEquals("Snake Plant", recommendedPlants.get(0).getPlantName());
	}
	
	@Test
	public void recommend_plants_recommends_plant_two() {

		List<Plant> recommendedPlants = dao.recommendPlants("Expert", "Medium", true, true, true, true, false);

		assertNotNull(recommendedPlants);
		assertEquals(1, recommendedPlants.size());
		assertEquals("Prayer Plant", recommendedPlants.get(0).getPlantName());
	}
	
	@Test
	public void recommend_plants_recommends_plant_three() {

		List<Plant> recommendedPlants = dao.recommendPlants("Intermediate", "Medium", false, false, true, false, true);

		assertNotNull(recommendedPlants);
		assertEquals(2, recommendedPlants.size());
		assertEquals("Madagascar Jewel", recommendedPlants.get(0).getPlantName());
	}
	
	//These tests will be for the updated better search method
//	@Test
//	public void search_by_name_test_one() {
//
//		List<Plant> searchedPlants = dao.searchByName("calathea");
//
//		assertNotNull(searchedPlants);
//		assertEquals(7, searchedPlants.size());
//		assertEquals("Pinstripe Calathea", searchedPlants.get(0).getPlantName());
//	}
//	
//	@Test
//	public void search_by_name_test_two() {
//
//		List<Plant> searchedPlants = dao.searchByName("actu");
//
//		assertNotNull(searchedPlants);
//		assertEquals(3, searchedPlants.size());
//		assertEquals("Christmas Cactus", searchedPlants.get(0).getPlantName());
//	}
//	
//	@Test
//	public void search_by_name_test_three() {
//
//		List<Plant> searchedPlants = dao.searchByName("Money Plant");
//
//		assertNotNull(searchedPlants);
//		assertEquals(1, searchedPlants.size());
//		assertEquals("Chinese Money Plant", searchedPlants.get(0).getPlantName());
//	}
//	
	@Test
	public void search_by_name_test_one() {

		List<Plant> searchedPlants = dao.searchByName("Snake Plant");

		assertNotNull(searchedPlants);
		assertEquals(1, searchedPlants.size());
		assertEquals("Snake Plant", searchedPlants.get(0).getPlantName());
	}
	
	@Test
	public void save_to_my_plants_test() {
		
		Plant testPlant = dao.getRandomPlant();
		dao.savePlantToMyPlants(testPlant);
		List <Plant> myPlants = dao.viewMyPlants();
		
		assertNotNull(testPlant);
		assertNotNull(myPlants);
		assertEquals(testPlant.getPlantName(), myPlants.get(0).getPlantName());
	}
	
	@Test
	public void delete_from_my_plants_test() {
		
		Plant testPlant = dao.getRandomPlant();
		dao.savePlantToMyPlants(testPlant);
		List <Plant> myPlantsBefore = dao.viewMyPlants();
		dao.deletePlantFromMyPlants(testPlant);
		List <Plant> myPlantsAfter = dao.viewMyPlants();
		
		assertNotNull(testPlant);
		assertNotNull(myPlantsBefore);
		assertEquals(testPlant.getPlantName(), myPlantsBefore.get(0).getPlantName());
		assertEquals(0, myPlantsAfter.size());

	}
	
	@Test
	public void view_my_plant_details_test() {
		
		Plant testPlant = dao.getRandomPlant();
		dao.savePlantToMyPlants(testPlant);
		List <Plant> myPlants = dao.viewMyPlants();
		Plant plantDetails = dao.viewMyPlantPlantDetails(testPlant.getPlantId());
		
		assertNotNull(testPlant);
		assertNotNull(myPlants);
		assertEquals(testPlant.getPlantName(), myPlants.get(0).getPlantName());
		assertEquals(testPlant.getPlantName(), plantDetails.getPlantName());
	}
	
	@Test
	public void view_plant_details_test() {
		
		List <Plant> testPlants = dao.recommendPlants("Beginner", "Low", true, false, false, false, false);
		Plant plantDetails = dao.viewPlantDetails(testPlants.get(0).getPlantId());
		
		assertNotNull(testPlants);
		assertNotNull(plantDetails);
	}
	
}
