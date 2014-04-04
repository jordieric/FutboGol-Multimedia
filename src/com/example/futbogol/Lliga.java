package com.example.futbogol;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
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
	private Cursor cursorLligues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lliga);

		lliganova = (TextView) findViewById(R.id.iniciCognom);
		lligues = new ArrayList<String>();
		lliguesObjecte = new ArrayList<ObjecteLliga>();

		// crear l'objecte que crea la connexió amb la BD
		helper = new LligaSQLiteHelper(this, "Lliga.db", null, 1);
		// obtenir l'objecte BD
		db = helper.getWritableDatabase();
		conversor = new LligaConversor(helper);
	}

	public void crealliga(View view) {
		String lliga = lliganova.getText().toString();
		ObjecteLliga lliga1 = new ObjecteLliga(lliga);
		cursorLligues = conversor.getAll();

		if (lliga.equals("")) {
			Toast lligaExisteix = Toast.makeText(getApplicationContext(),
					"Has d'introduir un nom!", Toast.LENGTH_SHORT);
			lligaExisteix.show();
		} else if (cursorLligues.moveToFirst()) {
			if (!comprovarNom(cursorLligues, lliga)) {
				conversor.save(lliga1);
				Toast lligaExisteix = Toast.makeText(getApplicationContext(),
						"Lliga afegida!", Toast.LENGTH_SHORT);
				lligaExisteix.show();
				db.close();
			}

		} else {

			conversor.save(lliga1);
			// lligues.add(lliga);
			// lliguesObjecte.add(lliga1);

			Toast lligaExisteix = Toast.makeText(getApplicationContext(),
					"Lliga afegida!", Toast.LENGTH_SHORT);
			lligaExisteix.show();
			db.close();
		}
	}

	public boolean comprovarNom(Cursor cursor, String lliga) {

		String nomBBDD;
		boolean trobat = false;

		while (cursor.moveToNext() && !trobat) {
			nomBBDD = cursorLligues.getString(0);

			if (nomBBDD.equals(lliga)) {

				Toast lligaExisteix = Toast.makeText(getApplicationContext(),
						"Ja existeix una LLIGA amb aquest nom!",
						Toast.LENGTH_SHORT);
				lligaExisteix.show();

				trobat = true;
			}
		}
		return trobat;
	}

	public void gestiolligues(View view) {

		cursorLligues = conversor.getAll();

		if (cursorLligues.moveToFirst() == false) {
			Toast NoLligues = Toast.makeText(getApplicationContext(),
					"No hi ha lligues!", Toast.LENGTH_SHORT);
			NoLligues.show();
		} else {

			Intent iLligues = new Intent(Lliga.this, GestioLligues.class);
			// Bundle dades = new Bundle();
			// dades.putSerializable("lliguesCreades", lliguesObjecte);
			// iLligues.putExtras(dades);
			startActivity(iLligues);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lliga, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// action with ID action_refresh was selected
		case R.id.stopMusic:
			if (MenuInicialMusical.mediaPlayer != null) {
				MenuInicialMusical.mediaPlayer.stop();
				return true;
			}
			break;
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
}