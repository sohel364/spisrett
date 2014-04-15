package com.spis.rett;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SpisrettSqliteHelper extends SQLiteOpenHelper 
{
	public static final String DATABASE_NAME="spisrett"; 
	private static final int databaseVersion=1;
	
	public final static String TABLE_NAME_PRODUCT="product";
	public final static String COLUMN_PRODUCT_ID="product_id";
//	public final static String COLUMN_PRODUCT_NAME="product_name";
	public static final String COLUMN_PRODUCT_CATEGORY = "product_category";
	public static final String COLUMN_PRODUCT_SUBCATEGORY = "product_sub_category";
	public static final String COLUMN_PRODUCT_TYPE = "product_type";
	public static final String COLUMN_PRODUCT_BRAND = "product_brand";
	public static final String COLUMN_PRODUCT_PRDUCER = "producer_name";
	public static final String COLUMN_PRODUCT_SUBTITLE = "product_subtitle";
	public static final String COLUMN_PRODUCT_GRADE = "product_grade";
	public static final String COLUMN_PRODUCT_IMAGE= "product_image";
	public static final String COLUMN_PRODUCT_DIDYOUKNOW= "product_didyouknow";
	public static final String COLUMN_PRODUCT_CAL_PER_100_GM= "product_cal";
	public static final String COLUMN_PRODUCT_CODE = "product_barcode";
	
	public static final String TABLE_NAME_NUTRITION="nutrition";
	public static final String COLUMN_NUTRITION_ID="nutrition_id";
	public static final String COLUMN_NUTRITION_NAME="nutrition_name";
	
	public static final String TABLE_NAME_PRODUCTvsNUTRITION="product_nutrition";
	public static final String COLUMN_PRODUCTvsNUTRITION_AMOUNT="nutrition_amount";
	
	
	private static final String DATABASE_CREATE_TABLE_PRODUCT = "create table "+ TABLE_NAME_PRODUCT + "(" 
			+COLUMN_PRODUCT_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , "
//			+ COLUMN_PRODUCT_NAME
//			+ " text  , "
			+COLUMN_PRODUCT_CATEGORY
			+ " text ,"
			+COLUMN_PRODUCT_SUBCATEGORY
			+ " text ,"
			+COLUMN_PRODUCT_TYPE
			+ " text ,"
			+COLUMN_PRODUCT_BRAND
			+ " text ,"
			+COLUMN_PRODUCT_PRDUCER
			+ " text ,"
			+COLUMN_PRODUCT_SUBTITLE
			+ " text ,"
			+COLUMN_PRODUCT_GRADE
			+" INTEGER ,"
			+COLUMN_PRODUCT_IMAGE
			+ " text ,"
			+COLUMN_PRODUCT_CAL_PER_100_GM
			+" INTEGER ,"
			+COLUMN_PRODUCT_DIDYOUKNOW
			+ " text ,"
			+ COLUMN_PRODUCT_CODE
			+ " text "
			+");";
	
	private static final String DATABASE_CREATE_TABLE_NUTRITION= "create table "+ TABLE_NAME_NUTRITION + "(" 
			+COLUMN_NUTRITION_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , "
			+COLUMN_NUTRITION_NAME
			+ " text "
			+");";
	
	private static final String DATABASE_CREATE_TABLE_PRODUCTvsNUTRITION= "create table "+ TABLE_NAME_PRODUCTvsNUTRITION + "(" 
			+COLUMN_PRODUCT_ID
			+ " INTEGER  NOT NULL , "
			+COLUMN_NUTRITION_ID
			+ " INTEGER  NOT NULL , "
			+COLUMN_PRODUCTvsNUTRITION_AMOUNT
			+ " REAL NOT NULL DEFAULT 0 ,"
			+"PRIMARY KEY (\""+COLUMN_PRODUCT_ID+"\", \""+COLUMN_NUTRITION_ID+"\")"
			+");";
	
	
	public SpisrettSqliteHelper(Context contex)
	{
		super(contex,DATABASE_NAME,null,databaseVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_PRODUCT);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_NUTRITION);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_PRODUCTvsNUTRITION);
		
		db.execSQL(DATABASE_CREATE_TABLE_PRODUCT);
		db.execSQL(DATABASE_CREATE_TABLE_NUTRITION);
		db.execSQL(DATABASE_CREATE_TABLE_PRODUCTvsNUTRITION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		 Log.w("helper",
			        "Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
		
		 onCreate(db);
		
	}
	
	
}
