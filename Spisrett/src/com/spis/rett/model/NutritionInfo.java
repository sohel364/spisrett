package com.spis.rett.model;

public class NutritionInfo {

	private String[] nutritionName;
	private int[] nutriotionId;
	private double[] nutriotionAmount;
	
	public NutritionInfo(String[] nutritionName, int[] nutriotionId,
			double[] nutriotionAmount) {
		super();
		this.nutritionName = nutritionName;
		this.nutriotionId = nutriotionId;
		this.nutriotionAmount = nutriotionAmount;
	}

	public NutritionInfo(String[] nutritionName, double[] nutriotionAmount) {
		super();
		this.nutritionName = nutritionName;
		this.nutriotionAmount = nutriotionAmount;
	}

	
	public String[] getNutritionNames() {
		return nutritionName;
	}

	public void setNutritionName(String[] nutritionName) {
		this.nutritionName = nutritionName;
	}

	public int[] getNutriotionId() {
		return nutriotionId;
	}

	public void setNutriotionId(int[] nutriotionId) {
		this.nutriotionId = nutriotionId;
	}

	public double[] getNutriotionAmount() {
		return nutriotionAmount;
	}

	public void setNutriotionAmount(double[] nutriotionAmount) {
		this.nutriotionAmount = nutriotionAmount;
	}
	
	
}
