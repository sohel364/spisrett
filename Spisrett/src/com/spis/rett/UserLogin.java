package com.spis.rett;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends Activity {

	EditText userName;
	EditText password;
	Handler mainHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_login);
		userName=(EditText)findViewById(R.id.edittext_user_name);
		password=(EditText)findViewById(R.id.edittext_password);
		mainHandler=new Handler();
		((Button)findViewById(R.id.login_button)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Intent i = new Intent(getBaseContext(),UserLogin.class);
				new LongOperation().execute("");
				startActivity(new Intent(getApplicationContext(),SpisrettHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			}
		});
		
		((Button)findViewById(R.id.registration_button_from_login)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),UserRegistrationActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_login, menu);
		return true;
	}
	
	public void postData() throws Exception {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://teknofolk.com/spisrett_admin/slave/signup.php");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        String userNameString,passwordString;
	        userNameString=userName.getText().toString();
	        passwordString=password.getText().toString();
	        Log.i("reponse",userNameString);
	        Log.i("reponse",passwordString);
	        if(userNameString==null || passwordString==null || userNameString.equals("") || passwordString.equals(""))
	        {
	        	mainHandler.post(new Runnable() {
					@Override
					public void run() {
						 Toast.makeText(getApplicationContext(), "User Name or Password Not Valid", Toast.LENGTH_LONG).show();
					}
				});
	        	return;
	        }
	        	
	        
	        nameValuePairs.add(new BasicNameValuePair("username", userNameString));
	        nameValuePairs.add(new BasicNameValuePair("password", passwordString));
	    
	        nameValuePairs.add(new BasicNameValuePair("request_type", "2"));
	        
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        
	        HttpResponse response=null;
	        try{

	        // Execute HTTP Post Request
	          response = httpclient.execute(httppost);
	          HttpEntity entity = response.getEntity();
	          String resposeString=EntityUtils.toString(entity);
	          if(resposeString.equalsIgnoreCase("true"))
	          {
	        	  mainHandler.post(new Runnable() {
					
					@Override
					public void run() {
						 Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
					}
				});
	        	 
	          }
	          else 
	          {
	        	  mainHandler.post(new Runnable() {
						
						@Override
						public void run() {
							 Toast.makeText(getApplicationContext(), "Login UnSuccessfull", Toast.LENGTH_LONG).show();
						}
					});
	          }
	          Log.i("reponse",resposeString);
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
