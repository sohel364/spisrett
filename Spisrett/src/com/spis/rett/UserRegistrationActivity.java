package com.spis.rett;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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

}
