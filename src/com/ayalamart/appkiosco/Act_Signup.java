package com.ayalamart.appkiosco;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ayalamart.helper.AppController;
import com.ayalamart.helper.GestionSesionesUsuario;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Act_Signup extends Activity {
 
	private EditText nombre;
	private EditText apellido; 
	private EditText cedula;
	private EditText correo;
	private EditText telefono;
	GestionSesionesUsuario sesion; 
	private ProgressDialog pDialog;
	String urlCrearCliente = "http://10.10.0.99:8080/Restaurante/rest/createCliente"; 
	private static String TAG = Act_Signup.class.getSimpleName();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_signup);

		sesion = new GestionSesionesUsuario(getApplicationContext()); 

		nombre = (EditText)findViewById(R.id.nombre_signup); 
		apellido = (EditText)findViewById(R.id.apellido_signup);
		cedula = (EditText)findViewById(R.id.cedula_signup); 
		correo = (EditText)findViewById(R.id.email_signup); 
		telefono = (EditText)findViewById(R.id.telefono_signup); 

		
		pDialog = new ProgressDialog(this);
        pDialog.setMessage("Por favor espere...");
        pDialog.setCancelable(false);

		//declarando el mensaje de error, y colocandole el mismo color de fondo para esconderlo 
		final TextView mensaje_error = (TextView)findViewById(R.id.alerta_contr_no_match); 
		mensaje_error.setBackgroundColor(Color.parseColor("#96B497"));

		Button button_signup = (Button) findViewById(R.id.but_signup);
		button_signup.setOnClickListener(new OnClickListener() {		

			@Override
			public void onClick(View v) {	

				

				final String name = "nombre_ejm"; 
				final String email = "correo_ejm"; 
				final String nombre_str = "ClKi" + "-" + nombre.getText().toString(); 
				final String apellido_str = "ClKi" +"-" + apellido.getText().toString();
				final String cedula_str = cedula.getText().toString(); 
				final String correo_str = correo.getText().toString(); 
				final String telefono_str = telefono.getText().toString();
				final String contrasena_str = "passKiosco"; 
				

				pDialog.show();


				if (validarCorreo(correo_str)) {
					if (validarDatos(nombre_str, cedula_str, correo_str, telefono_str)) {
							//si el password es valido, entra al siguiente ciclo 
								String match = " ";
								mensaje_error.setBackgroundColor(Color.parseColor("#96B497"));
								mensaje_error.setText(match);
								Long idcliente = new Long(0); 
								String json = "{"+"apeCliente"+":"+apellido_str+","+"cedCliente"+":"+cedula_str+","+
										"emailCliente"+":"+correo_str+","+"estatus"+":"+"1"+","+"idCliente"+":"+idcliente+","+
										"nomCliente"+":"+nombre_str+","+"passCliente"+":"+contrasena_str+","+"telCliente"+":"+telefono_str+"}"; 
			
								JSONObject cliente_nuevo = null;
								try {
									cliente_nuevo = new JSONObject(json);
									
								} catch (JSONException e2) {

									e2.printStackTrace();
									Log.d(TAG, "ERROR DE JSON"); 
								} 
							

								JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST, 
										urlCrearCliente, cliente_nuevo, null , new Response.ErrorListener() {
									@Override
									public void onErrorResponse(VolleyError error) {
										VolleyLog.d(TAG, "Error: " + error.getMessage());
										
										Log.d(TAG, "Error: " + error.getMessage()); 
										/*Toast.makeText(getApplicationContext(),
												error.getMessage(), Toast.LENGTH_SHORT).show();
										*/
										// hide the progress dialog
										hidepDialog();
									}
								});	
								sesion.crearSesionUSuario(name, email, nombre_str, apellido_str, cedula_str, correo_str, telefono_str);
								Log.d(TAG, "LOGRO REGISTRARSE"); 
								hidepDialog();
								
								Intent intent_ppal = new Intent(getApplicationContext(), ActPrincipal.class); 
								intent_ppal.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
								intent_ppal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
								startActivity(intent_ppal);
								finish(); 
								// Adding request to request queue
								AppController.getInstance().addToRequestQueue(jsonObjReq);	
					}
				}
				else {
					correo.setError("Correo Inv�lido");
					hidepDialog();
				}
			}
		});
	}

	private boolean validarCorreo (String correo_str){
		String PATRON_CORREO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; 

		Pattern patron = Pattern.compile(PATRON_CORREO); 
		Matcher correlacionador = patron.matcher(correo_str); 
		return correlacionador.matches();	
	}

	private boolean validarPassword (String contrasena_str){

		if (contrasena_str != null && contrasena_str.length() > 5 ){
			return true; 
		}
		return false; 

	}

	private boolean validarDatos (String nombre_str, String cedula_str, String correo_str, String telefono_str ){

		if (nombre_str != null && cedula_str != null && telefono_str != null && correo_str != null ){
			return true; 
		}
		return false; 
	}
	private void showpDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hidepDialog() {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}

}
