package com.spis.rett;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserRegistrationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_registration);
		
		((Button)findViewById(R.id.registration_button)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new LongOperation().execute("");
			}
		});
		
		((Button)findViewById(R.id.login_button_from_reg)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),UserLogin.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.user_registration, menu);
		return true;
	}
	
	public void postData() throws Exception {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://teknofolk.com/spisrett_admin/slave/signup.php");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("username", "polen"));
	        nameValuePairs.add(new BasicNameValuePair("password", "Cool"));
	        nameValuePairs.add(new BasicNameValuePair("email", "email@b.com"));
	        nameValuePairs.add(new BasicNameValuePair("type", "1"));
	        nameValuePairs.add(new BasicNameValuePair("status", "1"));
	        nameValuePairs.add(new BasicNameValuePair("request_type", "1"));
	        
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        
	        HttpResponse response=null;
	        try{

	        // Execute HTTP Post Request
	          response = httpclient.execute(httppost);
	        }
	        
	        catch(Exception ex){
	        	throw ex;
	        }
	        
	        if(response!=null){
	        	Log.d("ttt",response.toString());
	        }
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	} 


	private class LongOperation extends AsyncTask<String, Void, String> {

	        @Override
	        protected String doInBackground(String... params) {
	            try {
					postData();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return "Executed";
	        }

	        @Override
	        protected void onPostExecute(String result) {
	            //TextView txt = (TextView) findViewById(R.id.output);
	           // txt.setText("Executed"); // txt.setText(result);
	            // might want to change "executed" for the returned string passed
	            // into onPostExecute() but that is upto you
	        }

	        @Override
	        protected void onPreExecute() {}

	        @Override
	        protected void onProgressUpdate(Void... values) {}
	    }

	
}

