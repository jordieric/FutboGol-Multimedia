package com.example.futbogol;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DetallsLliga extends Activity {

	//private ArrayList<ObjecteLliga> LliguesJoc = new ArrayList<ObjecteLliga>();
	private String lliga;
	
	private LligaSQLiteHelper helper;
	private LligaConversor conversor;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalls_lliga);

		// crear l'objecte que crea la connexió amb la BD
        helper = new  LligaSQLiteHelper(this, "Lliga.db", null, 1);
        // obtenir l'objecte BD 
        db = helper.getReadableDatabase();
        
        conversor = new LligaConversor(helper);
        
        lliga = getIntent().getStringExtra("nomLliga");
		
	}

	public void eliminalliga(View view) {
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					conversor.remove(lliga);
					
					//LliguesJoc.remove(lliga);
					Toast lligaEliminar = Toast.makeText(
							getApplicationContext(),
							"Lliga Eliminada amb èxit!", Toast.LENGTH_SHORT);
					lligaEliminar.show();

					Intent Inici = new Intent(DetallsLliga.this, Index.class);
					startActivity(Inici);

				case DialogInterface.BUTTON_NEGATIVE:
					break;
				}
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Estàs completament segur?")
				.setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("No", dialogClickListener).show();
	}

	public void miraDAM(View view) {
		Intent campioDAM = new Intent(DetallsLliga.this, CampioDAM.class);
		startActivity(campioDAM);
	}

}
