package com.techelevator.houseplant_recommender.model;

public class Plant {
	
	private int plantId;
	private String plantName;
	private String scientificName;
	private String careDifficulty;
	private String lightNeeds;
	private boolean prefersHumidity;
	private boolean petSafe;
	private boolean isUnusual;
	private boolean hangingBasket;
	private boolean isSucculent;
	private String careInstructions;
	private int plantNumber;
	
	
	public Plant() {};
	
	
	
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	
	
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	
	
	public String getScientificName() {
		return scientificName;
	}
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
	
	
	public String getCareDifficulty() {
		return careDifficulty;
	}
	public void setCareDifficulty(String careDifficulty) {
		this.careDifficulty = careDifficulty;
	}
	
	
	public String getLightNeeds() {
		return lightNeeds;
	}
	public void setLightNeeds(String lightNeeds) {
		this.lightNeeds = lightNeeds;
	}
	
	
	public boolean isPrefersHumidity() {
		return prefersHumidity;
	}
	public void setPrefersHumidity(boolean prefersHumidity) {
		this.prefersHumidity = prefersHumidity;
	}
	
	
	public boolean isPetSafe() {
		return petSafe;
	}
	public void setPetSafe(boolean petSafe) {
		this.petSafe = petSafe;
	}
	
	
	public boolean isHangingBasket() {
		return hangingBasket;
	}
	public void setHangingBasket(boolean hangingBasket) {
		this.hangingBasket = hangingBasket;
	}
	
	
	public boolean isSucculent() {
		return isSucculent;
	}
	public void setSucculent(boolean isSucculent) {
		this.isSucculent = isSucculent;
	}
	
	
	public String getCareInstructions() {
		return careInstructions;
	}
	public void setCareInstructions(String careInstructions) {
		this.careInstructions = careInstructions;
	}
	
	
	public int getPlantNumber() {
		return plantNumber;
	}
	public void setPlantNumber(int plantNumber) {
		this.plantNumber = plantNumber;
	}


	public boolean isUnusual() {
		return isUnusual;
	}
	public void setUnusual(boolean isUnusual) {
		this.isUnusual = isUnusual;
	}
	
	
	

}
