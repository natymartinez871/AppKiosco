package com.ayalamart.appkiosco;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActPrincipal extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_principal);
		
		
		Button but_menu = (Button)findViewById(R.id.but_menu); 
		but_menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent_menu = new Intent(getApplicationContext(), Act_Menu.class);
				startActivity(intent_menu);

			}
		});
		Button button_acercade = (Button) findViewById(R.id.but_acercade);
		button_acercade.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent_acercade = new Intent(getApplicationContext(), Act_acercade.class);
				startActivity(intent_acercade);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
