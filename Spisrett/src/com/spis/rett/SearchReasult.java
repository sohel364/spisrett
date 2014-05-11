package com.spis.rett;

import java.util.ArrayList;

import com.spis.rett.model.Product;
import com.spis.rett.model.search.Child;
import com.spis.rett.model.search.Parent;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchReasult  extends  Activity  
{
    //Initialize variables
    private static final String STR_CHECKED = " has Checked!";
    private static final String STR_UNCHECKED = " has unChecked!";
    private int ParentClickStatus=-1;
    private int ChildClickStatus=-1;
    private ArrayList<Parent> parents;
    ImageView imageViewAd;
    ExpandableListView expandableListView;
    AddManager addManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
         
        setContentView(R.layout.activity_search_reasult);
        Resources res = this.getResources();
        Drawable devider = res.getDrawable(R.drawable.arrow_close);
         
        expandableListView=(ExpandableListView)findViewById(R.id.search_result);
        
         
        expandableListView.setGroupIndicator(null);
        expandableListView.setDivider(devider);
        expandableListView.setChildDivider(devider);
        expandableListView.setDividerHeight(1);
        registerForContextMenu(expandableListView);
         
        //Creating static data in arraylist
        final ArrayList<Parent> dummyList = buildDummyData();
         
        // Adding ArrayList data to ExpandableListView values
        loadHosts(dummyList);
        
        
        ImageButton imgbtSearchBarcode; 
        imgbtSearchBarcode= (ImageButton)findViewById(R.id.image_button1);
        imageViewAd=(ImageView)findViewById(R.id.image_bottom_ad);
        addManager=new AddManager(imageViewAd, new Handler());
        
        imgbtSearchBarcode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				   Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			       intent.putExtra("SCAN_MODE", "PRODUCT_MODE");//for Qr code, its "QR_CODE_MODE" instead of "PRODUCT_MODE"
			       intent.putExtra("SAVE_HISTORY", false);//this stops saving ur barcode in barcode scanner app's history
			       startActivityForResult(intent, 0);

			}
		});
        
        
        ImageView imageViewColseAd;
        imageViewColseAd = (ImageView)findViewById(R.id.imageview_ad_close);
        
  		imageViewColseAd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

			((LinearLayout)findViewById(R.id.linearlayout_ad_container)).setVisibility(View.GONE);
			
			ImageView imgLog=(ImageView)findViewById(R.id.image_dynamic_log);
			imgLog.setVisibility(View.VISIBLE);
				
			}
		});
  		
  		((ImageView)findViewById(R.id.logo_spisrett_home_search_result)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent homeIntent=new Intent(getApplicationContext(), SpisrettHome.class);
				homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(homeIntent);
			}
		});
        
    }
     
    /**
     * here should come your data service implementation
     * @return
     */
    private ArrayList<Parent> buildDummyData()
    {
    	
    	 ArrayList<Product> productArrayList=new DatabaseManager(getApplicationContext()).categorySearch(getIntent().getStringExtra("KEY_WORD"));
 		
 		if(productArrayList==null || productArrayList.size()==0)
 		{
 			return null;
 		}
         

 		
        // Creating ArrayList of type parent class to store parent class objects
        ArrayList<Parent> list = new ArrayList<Parent>();
        String categoryParetn="";
        String category;
        Parent parent;
        ArrayList<Child> children;
        Child child ;
        Product tempProduct;
        int i;
        int j;
        for (i = 0,j=0; i < productArrayList.size() ; )
        {
        	
        	tempProduct=productArrayList.get(i);
        	category=tempProduct.getProductCategory();
        	categoryParetn=new String(category);
        	
        	parent = new Parent();
        	parent.setName(""+i);
            parent.setText1(tempProduct.getProductCategory());
            children=new ArrayList<Child>();
            j=0;
        	while(categoryParetn.equalsIgnoreCase(category))
        	{
        		child = new Child();
        		child.setName("" + j);
        		
        		tempProduct.setTitleGradeAndColor(getApplicationContext());
                child.setText1(tempProduct.getProductTitle());
                child.setImageName(tempProduct.getProductIcon());
                child.setProductCode(tempProduct.getProductBarCode());
                children.add(child);

                i++;
                j++;
                
                if(i >= productArrayList.size())
                {
                	break;
                }
                tempProduct=productArrayList.get(i);
                category=tempProduct.getProductCategory();
        	}
                
        	parent.setChildren(children);
        	list.add(parent);
            
        }
 
        return list;
   }
     
     
    private void loadHosts(final ArrayList<Parent> newParents)
    {
        if (newParents == null)
            return;
         
        parents = newParents;
         
        if (expandableListView.getExpandableListAdapter() == null)
        {
            final MyExpandableListAdapter mAdapter = new MyExpandableListAdapter(this,parents);
             
            
            expandableListView.setAdapter(mAdapter);
        }
        else
        {
            ((MyExpandableListAdapter)expandableListView.getExpandableListAdapter()).notifyDataSetChanged();
        }   
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
    
    
}

