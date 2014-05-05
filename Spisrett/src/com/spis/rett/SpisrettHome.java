package com.spis.rett;




import com.google.zxing.integration.android.IntentIntegrator;

import com.spis.rett.model.Product;

import android.os.Bundle;
import android.app.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SpisrettHome extends Activity {

	ImageButton imgbtSearchBarcode;
	ImageButton imgbtEnterKeyWord;
	ImageButton imgbtKeywordSearch;
	Button buttonBrowse;
	ImageView imageViewAd;
	ImageView imageViewColseAd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spisrett_home);
		
		final IntentIntegrator scanIntegrator = new IntentIntegrator(this);	  		
  		imgbtSearchBarcode = (ImageButton)findViewById(R.id.image_button1);
  		imgbtEnterKeyWord= (ImageButton)findViewById(R.id.image_button2);
  		imgbtKeywordSearch=(ImageButton) findViewById(R.id.img_butt_search_text);
  		buttonBrowse=(Button)findViewById(R.id.btnBrows);
  		imageViewColseAd=(ImageView)findViewById(R.id.imageview_ad_close);
		imageViewAd=(ImageView)findViewById(R.id.image_bottom_ad);
		
  		addDemoProduct("1234");
		
  		imgbtSearchBarcode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				   Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			       intent.putExtra("SCAN_MODE", "PRODUCT_MODE");//for Qr code, its "QR_CODE_MODE" instead of "PRODUCT_MODE"
			       intent.putExtra("SAVE_HISTORY", false);//this stops saving ur barcode in barcode scanner app's history
			       startActivityForResult(intent, 0);

			}
		});
  		
  		
  		imgbtKeywordSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					
				String keyWord=((EditText)findViewById(R.id.editTextCode)).getText().toString();
				
				if(!keyWord.isEmpty()){
				Log.i("xZing","code "+keyWord);
					
				Intent productDeatailIntent=new Intent(getApplicationContext(), SearchReasult.class);
				productDeatailIntent.putExtra("KEY_WORD",keyWord);
				startActivity(productDeatailIntent);

				}
				

			}
		});
  		
  		imgbtEnterKeyWord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String keyWord=((EditText)findViewById(R.id.editTextCode)).getText().toString();
				
				Log.i("xZing","code "+keyWord);
				
			
				Intent productDeatailIntent=new Intent(getApplicationContext(), SearchReasult.class);
				productDeatailIntent.putExtra("KEY_WORD",keyWord);
				startActivity(productDeatailIntent);

				
			}
		});
  		
  		buttonBrowse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent productDeatailIntent=new Intent(getApplicationContext(), SearchReasult.class);
				productDeatailIntent.putExtra("KEY_WORD","");
				startActivity(productDeatailIntent);
			}
		});
  		
  		
  		imageViewColseAd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

			((LinearLayout)findViewById(R.id.linearlayout_ad_container)).setVisibility(View.GONE);
			
			ImageView imgLog=(ImageView)findViewById(R.id.image_dynamic_log);
			imgLog.setVisibility(View.VISIBLE);
				
			}
		});
		
  		((Button)findViewById(R.id.registration_button_on_home)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent regIn=new Intent(getApplicationContext(), UserRegistrationActivity.class);
				startActivity(regIn);
			}
		});
	}
	
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		if(requestCode == 0){         
			if(resultCode == RESULT_OK)         
			{
				
				String contents = intent.getStringExtra("SCAN_RESULT");  
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");      
				if(contents!=null)
				{
					Intent productDeatailIntent=new Intent(this, ProductDetailActivity.class);
					productDeatailIntent.putExtra("PRODUCT_CODE", contents);
					startActivity(productDeatailIntent);
				}
			}
   
			else if(resultCode == RESULT_CANCELED)         
			{              // Handle cancel             
				Log.i("xZing", "Cancelled");        
			}
		}
	}
	
	private void addDemoProduct(String code)
	{
		deleteDatabase(SpisrettSqliteHelper.DATABASE_NAME);
		
		DatabaseManager databaseManager=new DatabaseManager(getApplication());
		
//		Hovedkategori	Produkttype	 Produsent	Merke(br)	Sub kategori	Størrelse/Smak
//1		Meieri(Diary)	Melk(Milk)	 Tine		Ekstra Lett	Melk	1,75 liter
//11	Pålegg 	Makrell	Stabburet	Grovhakket filet i tomatsaus	Makrell	110 g	It contains Omega3 4,7g  EPA 1,1  DHA 1,9  
//20	Pålegg 	Storfe & svin	Gilde	Servelat		100 g
//69	Pizza	Pizza	Stabburet	Grandiosa	Pizza	Pepperoni -500 g
//77	Meieri(Diary)	Smør	Mills	Vita hjetego	Bordmargarin	Lett- 400 g


		
		Product product1=new Product( "Meieri(Diary)", "Melk", "Melk(Milk)", "Ekstra Lett", "Tine", "", 90,"product1",
				"http://www.tine.no/produkter/melk/melk/tinemelk-ekstra-lett",null,38, "7038010000911");
		
		Product product2=new Product( "Pålegg", "Makrell", "Makrell", "Grovhakket filet i tomatsaus", "Stabburet", "", 75,"product2"
				,"It contains Omega3 4,7g  EPA 1,1  DHA 1,9 ",null,242, "7039010016285");
		
		Product product3=new Product( "Pålegg", "Makrell", "Storfe & svin", "", "Gilde", "Servelat", 60,"product2",
				"",null,235, "7037204745027");
		Product product4=new Product( "Pizza", "Pizza", "Pizza", "", "Grandiosa", "Stabburet", 60,"product1",
				"",null,245, "7039010019828");
		Product product5=new Product( "Meieri(Diary)", "Bordmargarin", "Smør", "", "Vita hjetego", "Mills", 50,"product1",
				"",null,360, "7036110002989");
		
		Product product6=new Product( "Meieri(Diary)", "Bordmargarin", "Smør", "", "Vita hjetego", "Mills", 50,"product1",
				null,null,360, "1");
		
		String[] nutritionName={"Protein","Karbohydrater","	Fett","	Mettet","	Trans","Enumettet","Flerumettet	Kolesterol","Stivelse","Sukker",
				"	Sammalt ","	Tilsatt sukker","	Kostfiber","	Retinol	Beta-karoten","Vitamin A","	Vitamin D	"," Vitamin E","	Tiamin	",
				"Riboflavin ","	Niacin","	Vitamin B6","	Folat","	Vitamin B12","Vitamin C","	Kalsium	Jern","	Natrium	Kalium","	Magnesium",
				"	Sink","	Selen	","Kobber","	Fosfor","	Jod	Salt"};

		Log.i("xZing",databaseManager.addProduct(product1)+"");
		Log.i("xZing",databaseManager.addProduct(product2)+"");
		Log.i("xZing",databaseManager.addProduct(product3)+"");
		Log.i("xZing",databaseManager.addProduct(product4)+"");
		Log.i("xZing",databaseManager.addProduct(product5)+"");
		Log.i("xZing",databaseManager.addProduct(product6)+"");
		
		int[] prductIds ={1,  	 	1, 		 1,  		2	,	2	,	2  ,	2 ,		2 ,    3,	3,	  3,	3,5,  	5, 		 5,  		4	,	4	,	4  ,	6 ,		6 };
		int[] nuIds		={4,   		5,  	 6, 		1	,	2	,	3  ,	4 ,		5,		1, 	3,	  5,	4,4,   		5,  	 6, 		1	,	2	,	3  ,	4 ,		5};
		double[] amounts ={ 10.5,     11,	0.5,		1   ,	5   ,	11 ,	25 ,	75 , 	5,	10,	  11.5,	17 ,10.5,     11,	0.5,		1   ,	5   ,	11 ,	25 ,	75 };
		
		databaseManager.addNutrition(nutritionName, prductIds, nuIds, amounts);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.spisrett_home, menu);
		return true;
	}

}
