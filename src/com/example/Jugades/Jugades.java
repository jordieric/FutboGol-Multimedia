package com.example.Jugades;

import com.example.futbogol.R;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class Jugades extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jugades);
	}
	
	public void clicmartell(View view){
		Intent martell = new Intent(Jugades.this, JugadesDetall.class);
		martell.putExtra("Jugada", "Martell");
		startActivity(martell);
	}

	public void clicglobus(View view){
		Intent globus = new Intent(Jugades.this, JugadesDetall.class);
		globus.putExtra("Jugada", "Globus");
		startActivity(globus);
	}
	
	public void clic13(View view){
		Intent c13 = new Intent(Jugades.this, JugadesDetall.class);
		c13.putExtra("Jugada", "Canvi 1 - 3");
		startActivity(c13);
	}
	
	public void clic121(View view){
		Intent c121 = new Intent(Jugades.this, JugadesDetall.class);
		c121.putExtra("Jugada", "Canvi 1 - 2 - 1");
		startActivity(c121);
	}
	
	public void clic231(View view){
		Intent c231 = new Intent(Jugades.this, JugadesDetall.class);
		c231.putExtra("Jugada", "Canvi 2 - 3 - 1");
		startActivity(c231);
	}
	
}
