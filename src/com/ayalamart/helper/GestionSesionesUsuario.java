package com.ayalamart.helper;

import java.util.HashMap;

import com.ayalamart.appkiosco.ActPrincipal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class GestionSesionesUsuario {

	SharedPreferences pref_ap; 
	Editor editor_ap; 
	Context context_ap;
	int PRIVATE_MODE = 0; 
	private static final String TAG = GestionSesionesUsuario.class.getSimpleName(); 


	private static final String PREFER_NAME = "AndroidPrueba"; 
	private static final String USUARIO_LOGGEADO = "EstaLogeadoelUsuario"; 
	public static final String KEY_NAME = "name"; 
	public static final String KEY_EMAIL = "email"; 
	public static final String nombre = "nombre_str";
	public static final String apellido = "apellido_str";
	public static final String cedula = "cedula_str";
	public static final String correo = "correo_str";
	public static final String telefono = "telefono_str";
	public static final String contrasena = "contrasena_str";

	public GestionSesionesUsuario(Context context_c){
		this.context_ap = context_c; 
		pref_ap = context_ap.getSharedPreferences(PREFER_NAME, PRIVATE_MODE); 
		editor_ap = pref_ap.edit();	
	}

	public void crearSesionUSuario(String name, String email, String nombre_str, String apellido_str,
			String cedula_str, String correo_str, String telefono_str){
		editor_ap.putBoolean(USUARIO_LOGGEADO, true); 
		editor_ap.putString(PREFER_NAME, name); 
		editor_ap.putString(KEY_EMAIL, email); 
		editor_ap.putString(nombre, nombre_str); 
		editor_ap.putString(apellido, apellido_str);
		editor_ap.putString(cedula, cedula_str); 
		editor_ap.putString(correo, correo_str);
		editor_ap.putString(telefono, telefono_str); 
		//		editor_ap.putString(contrasena, contrasena_str);	
		editor_ap.commit(); 

		}
		public void iniciarSesionUsuario(String name, String email){
			editor_ap.putBoolean(USUARIO_LOGGEADO, true); 
			editor_ap.putString(PREFER_NAME, name); 
			editor_ap.putString(KEY_EMAIL, email); 
			editor_ap.commit(); 
		}


		public HashMap<String, String> getDetallesUsuario() {
			HashMap<String, String> usuario = new HashMap<String, String>();
			usuario.put(KEY_NAME, pref_ap.getString(KEY_NAME, null)); 
			usuario.put(KEY_EMAIL, pref_ap.getString(KEY_EMAIL, null)); 
			usuario.put(nombre, pref_ap.getString(nombre, null)); 
			usuario.put(apellido, pref_ap.getString(apellido, null));
			usuario.put(cedula, pref_ap.getString(cedula, null));
			usuario.put(correo, pref_ap.getString(correo, null));
			usuario.put(telefono, pref_ap.getString(telefono, null));
			usuario.put(contrasena, pref_ap.getString(contrasena, null));		
			return usuario; 
		}

		public void cerrarSesionUsuario(){
			editor_ap.clear(); 
			editor_ap.commit(); 

			Intent i_login = new Intent(context_ap, ActPrincipal.class); 
			i_login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
			i_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			context_ap.startActivity(i_login);
		}

		public boolean estaLogeadoelUsuario(){
			return pref_ap.getBoolean(USUARIO_LOGGEADO, false); 
		}

		public void llenarObjCliente(){


		}

	}
