package com.example.futbogol;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class GestioLligues extends Activity {

	private ArrayList<ObjecteLliga> lligues = new ArrayList<ObjecteLliga>();
	private ArrayList<ObjecteLliga> LliguesJoc = new ArrayList<ObjecteLliga>();
	private ListView llistaLligues;
	private ArrayAdapterLliga adapter;
	
	private LligaSQLiteHelper helper;
	private LligaConversor conversor;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestio_lligues);
		
		// crear l'objecte que crea la connexió amb la BD
        helper = new  LligaSQLiteHelper(this, "Lliga.db", null, 1);
        // obtenir l'objecte BD 
        
        conversor = new LligaConversor(helper);

		//lligues = (ArrayList<ObjecteLliga>) getIntent().getSerializableExtra("lliguesCreades");
		llistaLligues = (ListView) findViewById(R.id.llistatotesleslligues);

//		for (int i = 0; i < lligues.size(); i++) {
//			LliguesJoc.add(lligues.get(i));
//		}

		Cursor cursor = conversor.getAll();
		
		adapter = new ArrayAdapterLliga(this, cursor);
		llistaLligues.setAdapter(adapter);
		llistaLligues.setClickable(true);

		llistaLligues
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent,
							final View view, int position, long id) {

						ObjecteLliga lligaTeclejada = (ObjecteLliga)adapter.getItem(position);

						Intent detallsLliga = new Intent(GestioLligues.this,
								DetallsLliga.class);

						detallsLliga.putExtra("nomLliga",lligaTeclejada.getNom());

						startActivity(detallsLliga);
					}
				});
	}

}
