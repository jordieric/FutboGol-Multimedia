package com.example.Jugades;

import com.example.futbogol.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class JugadesDetall extends Activity {
	
	private String jugada;
	private Button titolJugada;
	private TextView descripciojugada;
	private ImageView imatgejugada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jugades_detall);
		jugada = getIntent().getStringExtra("Jugada");
		
		titolJugada = (Button) findViewById(R.id.titoljugades);
		titolJugada.setText(jugada);
		
		descripciojugada = (TextView) findViewById(R.id.descripciojugada);
		imatgejugada = (ImageView) findViewById(R.id.imatgejugada);
		
		if(jugada.equals("Martell")){
			descripciojugada.setText(R.string.martell);	
			imatgejugada.setImageResource(R.drawable.martell);
		} else if (jugada.equals("Globus")){
			descripciojugada.setText(R.string.globus);
			imatgejugada.setImageResource(R.drawable.globus);
		} else if (jugada.equals("Canvi 1 - 3")){
			descripciojugada.setText(R.string.canvi13);
			imatgejugada.setImageResource(R.drawable.canvi13);
		} else if (jugada.equals("Canvi 1 - 2 - 1")){
			descripciojugada.setText(R.string.canvi121);
			imatgejugada.setImageResource(R.drawable.canvi121);
		} else if (jugada.equals("Canvi 2 - 3 - 1")){
			descripciojugada.setText(R.string.canvi231);
			imatgejugada.setImageResource(R.drawable.canvi231);
		}
				
	}
	
	

}
