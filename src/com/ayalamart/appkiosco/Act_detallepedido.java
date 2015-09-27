package com.ayalamart.appkiosco;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Act_detallepedido extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_detallepedido);

		Button cerrar = (Button)findViewById(R.id.button1); 
		cerrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Act_detallepedido.this, ActPrincipal.class);  
				startActivity(intent);	
			}
		});;

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {

				Intent intent = new Intent(Act_detallepedido.this, ActPrincipal.class);  
				startActivity(intent);
			}
		}, 90000);
	}
}
