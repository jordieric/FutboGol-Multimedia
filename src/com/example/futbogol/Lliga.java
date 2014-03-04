package com.example.futbogol;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Lliga extends Activity {

	private TextView lliganova;
	private ArrayList<String> lligues;
	private ArrayList<ObjecteLliga> lliguesObjecte;
	private LligaSQLiteHelper helper;
	private LligaConversor conversor;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lliga);

		lliganova = (TextView) findViewById(R.id.lliganova);
		lligues = new ArrayList<String>();
		lliguesObjecte = new ArrayList<ObjecteLliga>();
		
		// crear l'objecte que crea la connexió amb la BD
        helper = new  LligaSQLiteHelper(this, "Lliga.db", null, 1);
        // obtenir l'objecte BD 
        db = helper.getWritableDatabase();
        conversor = new LligaConversor(helper);
	}

	public void crealliga(View view) {
		String lliga = lliganova.getText().toString();
		ObjecteLliga lliga1 = new ObjecteLliga(lliga);

		if (lligues.contains(lliga)) {

			Toast lligaExisteix = Toast
					.makeText(getApplicationContext(),
							"Ja existeix una LLIGA amb aquest nom!",
							Toast.LENGTH_SHORT);
			lligaExisteix.show();

		} else if (lliga.equals("")) {

			Toast lligaExisteix = Toast.makeText(getApplicationContext(),
					"Has d'introduir un nom!", Toast.LENGTH_SHORT);
			lligaExisteix.show();
		} else {
			
			conversor.save(lliga1);
			lligues.add(lliga);
			lliguesObjecte.add(lliga1);

			Toast lligaExisteix = Toast.makeText(getApplicationContext(),
					"Lliga afegida!", Toast.LENGTH_SHORT);
			lligaExisteix.show();
			db.close();
		}

	}

	public void gestiolligues(View view) {

		if (lligues.isEmpty()) {
			Toast NoLligues = Toast.makeText(getApplicationContext(),
					"No hi ha lligues!", Toast.LENGTH_SHORT);
			NoLligues.show();
		} else {

			Intent iLligues = new Intent(Lliga.this, GestioLligues.class);
//			Bundle dades = new Bundle();
//			dades.putSerializable("lliguesCreades", lliguesObjecte);
//			iLligues.putExtras(dades);
			startActivity(iLligues);
		}
	}
}