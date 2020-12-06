package com.techelevator.houseplant_recommender.model.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.techelevator.houseplant_recommender.model.Plant;

public class PlantModelTests {  
	

	@Test
	public void plant_model_finctions_as_expected() {

		Plant testPlant = new Plant();

			testPlant.setCareDifficulty("Beginner");
			testPlant.setCareInstructions("Water it.");
			testPlant.setHangingBasket(true);
			testPlant.setLightNeeds("Low");
			testPlant.setPetSafe(false);
			testPlant.setPlantId(1000);
			testPlant.setPlantName("String of Plants");
			testPlant.setPlantNumber(1001);
			testPlant.setPrefersHumidity(false);
			testPlant.setScientificName("Prettius Plantius");
			testPlant.setSucculent(true);
			testPlant.setUnusual(true);
			
			assertNotNull(testPlant);
			assertEquals("Beginner", testPlant.getCareDifficulty());
			assertEquals("Water it.", testPlant.getCareInstructions());
			assertEquals(true, testPlant.isHangingBasket());
			assertEquals("Low", testPlant.getLightNeeds());
			assertEquals(false, testPlant.isPetSafe());
			assertEquals(1000, testPlant.getPlantId());
			assertEquals("String of Plants", testPlant.getPlantName());
			assertEquals(1001, testPlant.getPlantNumber());
			assertEquals(false, testPlant.isPrefersHumidity());
			assertEquals("Prettius Plantius", testPlant.getScientificName());
			assertEquals(true, testPlant.isSucculent());
			assertEquals(true, testPlant.isUnusual());

			
		}

}
