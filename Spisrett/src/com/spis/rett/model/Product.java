package com.spis.rett.model;

import com.spis.rett.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

public class Product {

	private int productId;
	private String productCategory;
	private String productSubCategory;
	private String productType;
	private String brandName;
	private String producerName;
	private String productTitle;
	private String productSubTitle;
	private int productGrade;
	private char productGradeChar;
	private int productColor;
	private String productIcon;
	private String productDidYouKnow;
	private NutritionInfo nutritionInfo;
	private int kcal_per_100_gram;
	
	private int colorIndex;
	private String productBarCode;
	
//	public Product(int productId, String productName, String productBarCode,
//			String productCategory) {
//		this.productId = productId;
//		this.productName = productName;
//		this.productBarCode = productBarCode;
//		this.productCategory = productCategory;
//	}

	
	
	
	
	
	
//	public Product(int productId, String productCategory,
//			String productSubCategory, String productType, String brandName,
//			String producerName, String productSubTitle, int kcal_per_100_gram,
//			String productBarCode) {
//		this.productId = productId;
//		
//		this.productCategory = productCategory;
//		this.productSubCategory = productSubCategory;
//		this.productType = productType;
//		this.brandName = brandName;
//		this.producerName = producerName;
//		this.productSubTitle = productSubTitle;
//		this.kcal_per_100_gram = kcal_per_100_gram;
//		this.productBarCode = productBarCode;
//		this.productTitle=producerName+" "+brandName+" "+productSubCategory;
//	}
//	
//	public Product( String productCategory,
//			String productSubCategory, String productType, String brandName,
//			String producerName, String productSubTitle, int kcal_per_100_gram,
//			String productBarCode) {
//		this.productCategory = productCategory;
//		this.productSubCategory = productSubCategory;
//		this.productType = productType;
//		this.brandName = brandName;
//		this.producerName = producerName;
//		this.productSubTitle = productSubTitle;
//		this.kcal_per_100_gram = kcal_per_100_gram;
//		this.productBarCode = productBarCode;
//		this.productTitle=producerName+" "+brandName+" "+productSubCategory;
//	}


	
	
	public Product( String productCategory,
			String productSubCategory, String productType, String brandName,
			String producerName, String productSubTitle, int productGrade,
			String productIcon, String productDidYouKnow,
			NutritionInfo nutritionInfo, int kcal_per_100_gram,
			String productBarCode) {
		super();
		this.productCategory = productCategory;
		this.productSubCategory = productSubCategory;
		this.productType = productType;
		this.brandName = brandName;
		this.producerName = producerName;
		this.productSubTitle = productSubTitle;
		this.productGrade = productGrade;
		this.productIcon = productIcon;
		this.productDidYouKnow = productDidYouKnow;
		this.nutritionInfo = nutritionInfo;
		this.kcal_per_100_gram = kcal_per_100_gram;
		this.productBarCode = productBarCode;
		
		
//		productColor=getProductColor(productGrade);
	}
	

	


	public Product(String productBarCode) {
		super();
		this.productBarCode = productBarCode;
	}


	public Product(int productId) {
		super();
		this.productId = productId;
	}

	public Product()
	{
		this.productId = -1;
		this.productCategory = null;
		this.productSubCategory = null;
		this.productType = null;
		this.brandName = null;
		this.producerName = null;
		this.productSubTitle = null;
		this.productGrade = -1;
		this.productIcon = null;
		this.productDidYouKnow = null;
		this.nutritionInfo = null;
		this.kcal_per_100_gram = -1;
		this.productBarCode = null;
		
		this.productTitle=null;
		this.productColor=-1;
	}

	public void  setTitleGradeAndColor(Context context) {
		
		this.productTitle=producerName+" "+brandName+" "+productSubCategory;
		int grade=this.productGrade;
		int[] colors={android.R.color.holo_red_dark,android.R.color.holo_red_light,android.R.color.holo_orange_dark,android.R.color.holo_orange_light,
				R.color.dark_yellow,R.color.yellow_light,android.R.color.holo_blue_dark,android.R.color.holo_blue_light,
				android.R.color.holo_green_dark,android.R.color.holo_green_light};
		
		colorIndex=grade/10;
		Resources res=context.getResources();
		productColor=res.getColor(colors[colorIndex]);

		productGradeChar=(char) ('E'-(colorIndex/2));
		
//		if(grade >=0 && grade <=5)
//		{
//			
//			productGradeChar='E';
//			return;
//		}
//		
//		
//		if(grade >=10 && grade <=15)
//		{
//			productGradeChar='E';
//			return;
//		}
//			
//		
//		if(grade >=20 && grade <=25)
//		{
//			productGradeChar='D';
//			return;
//		}
//		
//		if(grade >=30 && grade <=35)
//		{
//			productGradeChar='D';
//			return;
//		}
//		if(grade >=40 && grade <=45)
//		{
//			productGradeChar='C';
//			return;
//		}
//		if(grade >=50 && grade <=55)
//		{
//			productGradeChar='C';
//			return;
//		}
//		if(grade >=60 && grade <=65)
//		{
//			productGradeChar='B';
//			return;
//		}
//		if(grade >=70 && grade <=75)
//		{
//			productGradeChar='B';
//			return;
//		}
//		if(grade >=80 && grade <=85)
//		{
//			productGradeChar='A';
//			return;
//		}
//		productGradeChar='A';
		
	}
	
	
	public int getProductId() {
		return productId;
	}

	

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}


	public String getProductSubCategory() {
		return productSubCategory;
	}


	public void setProductSubCategory(String productSubCategory) {
		this.productSubCategory = productSubCategory;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	public String getProducerName() {
		return producerName;
	}


	public void setProducerId(String producerName) {
		this.producerName = producerName;
	}


	public String getProductTitle() {
		return productTitle;
	}


	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}


	public String getProductSubTitle() {
		return productSubTitle;
	}


	public void setProductSubTitle(String productSubTitle) {
		this.productSubTitle = productSubTitle;
	}


	public int getKcal_per_100_gram() {
		return kcal_per_100_gram;
	}


	public void setKcal_per_100_gram(int kcal_per_100_gram) {
		this.kcal_per_100_gram = kcal_per_100_gram;
	}

	public int getProductGrade() {
		return productGrade;
	}

	public void setProductGrade(int productGrade) {
		this.productGrade = productGrade;
	}

	public String getProductIcon() {
		return productIcon;
	}

	public void setProductIcon(String productIcon) {
		this.productIcon = productIcon;
	}

	public String getProductDidYouKnow() {
		return productDidYouKnow;
	}

	public void setProductDidYouKnow(String productDidYouKnow) {
		this.productDidYouKnow = productDidYouKnow;
	}


	public NutritionInfo getNutritionInfo() {
		return nutritionInfo;
	}


	public void setNutritionInfo(NutritionInfo nutritionInfo) {
		this.nutritionInfo = nutritionInfo;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}




	public char getProductGradeChar() {
		return productGradeChar;
	}



	public int getProductColor() {
		return productColor;
	}



	public int getColorIndex() {
		return colorIndex;
	}

	
	
	
	
	
}
