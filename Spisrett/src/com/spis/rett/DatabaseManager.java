package com.spis.rett;

import java.sql.PreparedStatement;
import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.spis.rett.model.NutritionInfo;
import com.spis.rett.model.Product;
import com.spis.rett.model.User;
public class DatabaseManager {

	private SQLiteDatabase database;
	private SpisrettSqliteHelper dbHelper;
	
	
	public DatabaseManager(Context context) {
	    dbHelper = new SpisrettSqliteHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public long addProduct(Product product) {
	    ContentValues values = new ContentValues();
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_CATEGORY,product.getProductCategory() );
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_SUBCATEGORY,product.getProductSubCategory() );
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_BRAND,product.getBrandName() );
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_BRAND,product.getBrandName() );
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_TYPE,product.getProductType() );
	    
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_PRDUCER, product.getProducerName());
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_SUBTITLE, product.getProductSubTitle());
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_GRADE, product.getProductGrade());
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_IMAGE, product.getProductIcon());
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_DIDYOUKNOW, product.getProductDidYouKnow());
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_CAL_PER_100_GM, product.getKcal_per_100_gram());
	    values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_CODE, product.getProductBarCode());


//	    open();
	    
	    long insertId =  database.insert(SpisrettSqliteHelper.TABLE_NAME_PRODUCT, null,
	        values);
//	    close();
	    return insertId;
	   
	  }
	  
	  public void addProductNutrition(String[] nutritionNames,int[] productId,int[] nutritionId,double[] amount)
	  {
		  ContentValues  values ;
		  open();
		  for(String nutName:nutritionNames)
		  {
			  addNutritions(0,nutName);
			  
		  }
		  
		  
		  
		  
		 for(int i=0;i<productId.length;i++)
		 {
			 values = new ContentValues();
			 values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_ID, productId[i]);
			 values.put(SpisrettSqliteHelper.COLUMN_NUTRITION_ID, nutritionId[i]);
			 values.put(SpisrettSqliteHelper.COLUMN_PRODUCTvsNUTRITION_AMOUNT, amount[i]);
			 
			 database.insert(SpisrettSqliteHelper.TABLE_NAME_PRODUCTvsNUTRITION, null,
				        values);
		 }
		 close();
		  
	  }
	  public void addProductNutrition(int productId,int nutritionId,double amount)
	  {
		  ContentValues  values ;
//		  open();
		  values = new ContentValues();
		  values.put(SpisrettSqliteHelper.COLUMN_PRODUCT_ID, productId);
			 values.put(SpisrettSqliteHelper.COLUMN_NUTRITION_ID, nutritionId);
			 values.put(SpisrettSqliteHelper.COLUMN_PRODUCTvsNUTRITION_AMOUNT, amount);
			 database.insert(SpisrettSqliteHelper.TABLE_NAME_PRODUCTvsNUTRITION, null,
				        values);
//		close();
	  }
	  
	  public void addNutritions(int i,String nutrition)
	  {
		  ContentValues  values ;
//		  open();
	
			  values = new ContentValues();
			  if(i!=0)
			  {
				  values.put(SpisrettSqliteHelper.COLUMN_NUTRITION_ID, i);
			  }
			  values.put(SpisrettSqliteHelper.COLUMN_NUTRITION_NAME,nutrition);
			  database.insert(SpisrettSqliteHelper.TABLE_NAME_NUTRITION, null,
				        values);
//		close();

	  }
	  
	  public Product getProduct(String barCode)
	  {
		  String[] allColumnsProduct = { SpisrettSqliteHelper.COLUMN_PRODUCT_ID,SpisrettSqliteHelper.COLUMN_PRODUCT_CATEGORY,SpisrettSqliteHelper.COLUMN_PRODUCT_SUBCATEGORY
					,SpisrettSqliteHelper.COLUMN_PRODUCT_TYPE,SpisrettSqliteHelper.COLUMN_PRODUCT_BRAND,SpisrettSqliteHelper.COLUMN_PRODUCT_PRDUCER,
					SpisrettSqliteHelper.COLUMN_PRODUCT_SUBTITLE,SpisrettSqliteHelper.COLUMN_PRODUCT_GRADE,SpisrettSqliteHelper.COLUMN_PRODUCT_IMAGE,
					SpisrettSqliteHelper.COLUMN_PRODUCT_DIDYOUKNOW,SpisrettSqliteHelper.COLUMN_PRODUCT_CAL_PER_100_GM,SpisrettSqliteHelper.COLUMN_PRODUCT_CODE};
		  open();
		  Product product=null;
		  int productId;
		  Log.i("xZing","code in dbM : "+barCode);
		    Cursor cursor = database.query(SpisrettSqliteHelper.TABLE_NAME_PRODUCT,
		        allColumnsProduct, SpisrettSqliteHelper.COLUMN_PRODUCT_CODE+" = \""+barCode+"\"", null, null, null, null);
		  
		    if(cursor.getCount()>0)
		    {
		    	cursor.moveToFirst();
		    	
		    	for(int i=0;i< cursor.getColumnCount();i++)
		    	{
		    		if(i==0 || i==7 || i== 10)
		    		{
		    			Log.i("xZing",cursor.getColumnName(i)+"  "+cursor.getInt(i));
		    			continue;
		    		}
		    		
		    		Log.i("xZing",cursor.getColumnName(i)+"  "+cursor.getString(i));

		    	}
		    	cursor.moveToFirst();
		    	productId=cursor.getInt(0);
		    	
		    	product =new Product(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), 
			    		cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),cursor.getString(9), getProductNutrition(productId),cursor. getInt(10),cursor.getString(11));
		    	
		    }
		    cursor.close();
		    close();
		    return product;
	  }
	
	  
	  public NutritionInfo getProductNutrition(int productId)
	  {
		  open();
		  
		  NutritionInfo nutritionInfo=null;
		  String[] allColumnsProductNutritio={SpisrettSqliteHelper.COLUMN_NUTRITION_ID,SpisrettSqliteHelper.COLUMN_PRODUCTvsNUTRITION_AMOUNT};
		  double[] nutirionAmounts;
		  String[] nutritionNames;
		  int rowCount;
		  int id;
		  Cursor cursor = database.query(SpisrettSqliteHelper.TABLE_NAME_PRODUCTvsNUTRITION,
				  allColumnsProductNutritio, SpisrettSqliteHelper.COLUMN_PRODUCT_ID+" = \""+productId+"\"", null, null, null, null);
		  rowCount=cursor.getCount();
		  if(rowCount>0)
		  {
			  nutirionAmounts=new double[rowCount];
			  nutritionNames=new String[rowCount];
			
			  for(int i=0;i<rowCount;i++)
			  {
				  cursor.moveToPosition(i);
				 id=cursor.getInt(0);
				 nutritionNames[i]= getNutritionName(id);
				 nutirionAmounts[i]=cursor.getDouble(1);
			  }
			 nutritionInfo=new NutritionInfo(nutritionNames, nutirionAmounts);
		  }
		  cursor.close();
		  close();
		  return nutritionInfo;
	  }
	  
	  public String getNutritionName(int nutritionId)
	  {
		  String[] columns={SpisrettSqliteHelper.COLUMN_NUTRITION_NAME};
		  Cursor cursor = database.query(SpisrettSqliteHelper.TABLE_NAME_NUTRITION,columns,
				   SpisrettSqliteHelper.COLUMN_NUTRITION_ID+" = \""+nutritionId+"\"", null, null, null, null);
		  if(cursor.getCount()>0)
		  {
			  cursor.moveToFirst();
			  String s= cursor.getString(0);
			  cursor.close();
			  return s;
		  }
		  return null;
	  }
	
	  public ArrayList<Product> categorySearch(String keyWord)
	  {
		  ArrayList<Product> products=null;
		  String[] allColumnsProduct = { SpisrettSqliteHelper.COLUMN_PRODUCT_ID,SpisrettSqliteHelper.COLUMN_PRODUCT_CATEGORY,SpisrettSqliteHelper.COLUMN_PRODUCT_SUBCATEGORY
					,SpisrettSqliteHelper.COLUMN_PRODUCT_TYPE,SpisrettSqliteHelper.COLUMN_PRODUCT_BRAND,SpisrettSqliteHelper.COLUMN_PRODUCT_PRDUCER,
					SpisrettSqliteHelper.COLUMN_PRODUCT_SUBTITLE,SpisrettSqliteHelper.COLUMN_PRODUCT_GRADE,SpisrettSqliteHelper.COLUMN_PRODUCT_IMAGE,
					SpisrettSqliteHelper.COLUMN_PRODUCT_DIDYOUKNOW,SpisrettSqliteHelper.COLUMN_PRODUCT_CAL_PER_100_GM,SpisrettSqliteHelper.COLUMN_PRODUCT_CODE};
		  
		  String[] columnsToSearch = { SpisrettSqliteHelper.COLUMN_PRODUCT_ID,SpisrettSqliteHelper.COLUMN_PRODUCT_CATEGORY,SpisrettSqliteHelper.COLUMN_PRODUCT_SUBCATEGORY
					,SpisrettSqliteHelper.COLUMN_PRODUCT_TYPE,SpisrettSqliteHelper.COLUMN_PRODUCT_BRAND,SpisrettSqliteHelper.COLUMN_PRODUCT_PRDUCER,
					SpisrettSqliteHelper.COLUMN_PRODUCT_SUBTITLE,SpisrettSqliteHelper.COLUMN_PRODUCT_GRADE,
					SpisrettSqliteHelper.COLUMN_PRODUCT_DIDYOUKNOW,SpisrettSqliteHelper.COLUMN_PRODUCT_CAL_PER_100_GM,SpisrettSqliteHelper.COLUMN_PRODUCT_CODE};
		  StringBuilder whereString=new StringBuilder();
		  
		  whereString.append(columnsToSearch[0]+" LIKE '%"+keyWord+"%'");
		  
		  for(int i=1;i<columnsToSearch.length;i++)
		  {
			  whereString.append(" OR "+columnsToSearch[i]+" LIKE '%"+keyWord+"%'");
			  
		  }
		  open();
		  Cursor cursor = database.query(SpisrettSqliteHelper.TABLE_NAME_PRODUCT,
				  allColumnsProduct,whereString.toString() , null, null, null, SpisrettSqliteHelper.COLUMN_PRODUCT_CATEGORY);
		  
		  
		  int rowCount;
		  Product tempProduct;
		  rowCount=cursor.getCount();
		  if(rowCount>0)
		  {
			  products=new ArrayList<Product>();
			  for(int i=0;i<rowCount;i++)
			  {
				  cursor.moveToPosition(i);
				  tempProduct =new Product(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), 
				    		cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),cursor.getString(9), getProductNutrition(cursor.getInt(0)),
				    		cursor. getInt(10),cursor.getString(11));
				  products.add(tempProduct);
			  }
			
		  }
		  cursor.close();
		  close();
		  return products;
	  }

	  public long addUser(User user)
	  {
		  ContentValues values = new ContentValues();
		    values.put(SpisrettSqliteHelper.COLUMN_USER_DEVICEID,user.getDeviceId());
		    values.put(SpisrettSqliteHelper.COLUMN_USER_NAME,user.getUserName() );
		    values.put(SpisrettSqliteHelper.COLUMN_USER_PASSWORD,user.getPassword() );
		    values.put(SpisrettSqliteHelper.COLUMN_USER_EMAIL,user.getEmail() );
		    values.put(SpisrettSqliteHelper.COLUMN_USER_TYPE,user.getUserType());

		    open();
		    
		    long insertId =  database.insert(SpisrettSqliteHelper.TABLE_NAME_USER, null,
		        values);
		    close();
		    return insertId;
	  }
	 
	  /* Only to inset data at first time run from the given execl file
	   * 
	   * 
	   * */
	  public void addOfflineData(String[] nutrition,String[][] value,float nutricats[][])
	  {
		  int dataLimit=50;
		  open();
			for (int i = 0; i < nutrition.length; i++) {
					addNutritions(i+1, nutrition[i]);
			}
				
		     Product product;
			
			for (int i = 0; i < dataLimit; i++) {
				
			product=new Product(value[i][1], value[i][5], value[i][2], value[i][4], value[i][3],value[i][6], 1,i+1+".png",
					value[i][7], null, value[i][9]==null?0:Integer.parseInt( value[i][9]), value[i][8]);
		
			
			addProduct(product);
			}

			int j;
			for (int i = 0; i < dataLimit; i++) {
				Log.i(" inserting "," "+(i+1 ));
				for(j=0;j<39;j++)
				{
					if(nutricats[i][j]==0)
						continue;
					addProductNutrition(i+1, j+1, nutricats[i][j]);
				}
			
			}
			

	        close();
	
		  
	  }
	  
	  
	} 