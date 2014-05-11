package com.spis.rett;

import java.util.Timer;
import java.util.TimerTask;

import com.spis.rett.model.NutritionInfo;
import com.spis.rett.model.Product;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
public class ProductDetailActivity extends Activity {

	Product product;
	
	private String productCode;
	private String didYouKnowString;
	
	Button buttonTabNutrition;
	Button buttonTab2;
	TextView tabText;
	ImageView imageViewAd;
	ImageView imageViewColseAd;
	LinearLayout linearLayout;
	LinearLayout linearLayoutProductDetail;
	AddManager addManager;
	
	Handler mainHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_product_detail_layout);
		Intent intent = getIntent();
		productCode=intent.getStringExtra("PRODUCT_CODE");
		didYouKnowString="";
		mainHandler=new Handler();
		imageViewAd=(ImageView)findViewById(R.id.image_bottom_ad);
		addManager=new AddManager(imageViewAd, mainHandler);
		
		product=new DatabaseManager(getApplicationContext()).getProduct(productCode);
		if(product!=null)
		{
			product.setTitleGradeAndColor(this);
			((TextView)findViewById(R.id.product_title)).setText(product.getProductTitle());
			((TextView)findViewById(R.id.product_sub_title)).setText(product.getProductSubTitle());
			int id = getResources().getIdentifier(product.getProductIcon(), "drawable", getPackageName());
			((ImageView)findViewById(R.id.product_logo)).setImageResource(id);
			
			((TextView)findViewById(R.id.text_kcal_per_100)).setText(""+product.getKcal_per_100_gram());
			((TextView)findViewById(R.id.text_grade)).setText(" "+product.getProductGradeChar());
			
			didYouKnowString=product.getProductDidYouKnow();
			
			linearLayout=(LinearLayout)findViewById(R.id.linearLayout_color_code_arrow);
			ImageView imageArrow=(ImageView)linearLayout.getChildAt(product.getColorIndex());
			imageArrow.setVisibility(View.VISIBLE);
			
			linearLayout=(LinearLayout)findViewById(R.id.linearLayout_grade_text);
			linearLayout.setBackgroundColor(product.getProductColor());
			
			
		}
		else 
		{
			((TextView)findViewById(R.id.product_title)).setText("There is no product with this code ");
		}
		
		buttonTabNutrition=(Button)findViewById(R.id.tab_button1);
		buttonTab2=(Button)findViewById(R.id.tab_button2);
		tabText=(TextView)findViewById(R.id.tab_text);
		imageViewColseAd=(ImageView)findViewById(R.id.imageview_ad_close);
		
		linearLayoutProductDetail=(LinearLayout)findViewById(R.id.linear_product_info);
		
		buttonTabNutrition.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				RelativeLayout rvLa =(RelativeLayout)findViewById(R.id.relativelayout4);
			
				rvLa.setBackgroundColor(0xFFFFFFFF);
		
				
				Log.i("xZing","1 clicked");
				if(product==null)
					return;
				((LinearLayout)findViewById(R.id.linearlayout_ad_container)).setVisibility(View.GONE);
				LayoutParams layoutParamsText=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				LayoutParams layoutParamsImage=new LayoutParams(LayoutParams.FILL_PARENT, 2);
				LayoutParams layoutParamsImageIn=new LayoutParams(LayoutParams.FILL_PARENT, 2);
				linearLayoutProductDetail.removeAllViews();
				Log.i("xZing","here1");
				TextView textView=new TextView(getApplicationContext());
				
				
				textView.setLayoutParams(layoutParamsText);
				textView.setText("Categoty : "+ product.getProductCategory() );
				textView.setGravity(Gravity.LEFT);
				textView.setPadding(0, 30, 0, 0);
				textView.setTextColor(getResources().getColor(android.R.color.black));
				linearLayoutProductDetail.addView(textView);
				
				Log.i("xZing","here2");
				ImageView imageView=new ImageView(getApplicationContext());
				imageView.setLayoutParams(layoutParamsImage);
				imageView.setImageResource(R.color.gray);
				linearLayoutProductDetail.addView(imageView);
				Log.i("xZing","here1");
				NutritionInfo nInfo=product.getNutritionInfo();
				if(nInfo==null)
				{
					textView=new TextView(getApplicationContext());
					
					textView.setLayoutParams(layoutParamsText);
					textView.setText("No Nutrition Info Found.");
					linearLayoutProductDetail.addView(textView);
					
					return;
				}
				String[] nutritionName=nInfo.getNutritionNames();
				double[] nutritionAmount=nInfo.getNutriotionAmount();
				Log.i("xZing","here2");
				if(nutritionName==null || nutritionName.length==0)
				{
					return;
				}
				LinearLayout insideLinearLayout;
				LayoutParams layoutParamsInsideLinear=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				for(int i=0;i<nutritionName.length;i++)
				{
					insideLinearLayout=new LinearLayout(getApplicationContext());
					insideLinearLayout.setLayoutParams(layoutParamsInsideLinear);
					insideLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
					insideLinearLayout.setPadding(150, 20, 100, 0);
					textView=new TextView(getApplicationContext());
					textView.setLayoutParams(layoutParamsText);
					textView.setText(nutritionName[i] );
					textView.setTextColor(getResources().getColor(android.R.color.black));
					textView.setGravity(Gravity.LEFT);
					insideLinearLayout.addView(textView);
					
					textView=new TextView(getApplicationContext());
					textView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,2f));
					textView.setText(""+nutritionAmount[i] );
					textView.setTextColor(getResources().getColor(android.R.color.black));
					textView.setGravity(Gravity.RIGHT);
					insideLinearLayout.addView(textView);
					
					linearLayoutProductDetail.addView(insideLinearLayout);
					
					imageView=new ImageView(getApplicationContext());
					imageView.setLayoutParams(layoutParamsImageIn);
					imageView.setPadding(150, 0, 20, 0);
					imageView.setImageResource(R.color.nutrifacts_bar);
					
//					imageView.getLayoutParams().width = 300;
					linearLayoutProductDetail.addView(imageView);
				}
				
				
				
			}
		});
		
		buttonTab2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.i("xZing","2 clicked "+didYouKnowString);
					if(product==null)
						return;
					((LinearLayout)findViewById(R.id.linearlayout_ad_container)).setVisibility(View.GONE);
					linearLayoutProductDetail.removeAllViews();
					LayoutParams layoutParamsText=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					TextView textView=new TextView(getApplicationContext());
					
					textView.setLayoutParams(layoutParamsText);
					if(product.getProductDidYouKnow()==null)
					{
						return;
					}
					textView.setText(""+ product.getProductDidYouKnow());
					
					linearLayoutProductDetail.addView(textView);
				}
			});
		
		imageViewColseAd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				((LinearLayout)findViewById(R.id.linearlayout_ad_container)).setVisibility(View.GONE);
				
//				ImageView imgLog=(ImageView)findViewById(R.id.image_dynamic_log);
//				imgLog.setVisibility(View.VISIBLE);
				
			}
		});
		
		((ImageView)findViewById(R.id.logo_spisrett_home_product_detail)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent homeIntent=new Intent(getApplicationContext(), SpisrettHome.class);
				homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(homeIntent);
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		addManager.showRandomAd();
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		addManager.stopShowingAd();
		super.onPause();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.product_detail, menu);
		return true;
	}

}
