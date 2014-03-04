package com.example.futbogol;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CampioDAM extends Activity {
	
	private Map<Integer, Equip> equipsDAM = new HashMap<Integer, Equip>();
	private Equip guanyador;
	private TextView nom, defensa, davanter;
	private ImageView premi;
	private Map<Integer,String> premis = new HashMap<Integer, String>();
	private int numeroPremi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campio_dam);
		
		nom = (TextView) findViewById(R.id.nom);
		defensa = (TextView) findViewById(R.id.defensa);
		davanter = (TextView) findViewById(R.id.davanter);
		premi = (ImageView) findViewById(R.id.premi);
		
		this.equipsDAM.put(0, new Equip("Eric Gutierrez", "Jordi Coll", "Torpedos"));
		this.equipsDAM.put(1, new Equip("Oriol Comas", "Aniol Ronaldo", "Tirites"));
		this.equipsDAM.put(2, new Equip("Jordi Reixach", "Mercedes Zorrilla", "Persistencia"));
		this.equipsDAM.put(3, new Equip("Dani Cano", "Ernest Puigdemont", "Pokemons"));
		
		this.premis.put(0, "http://www.motorexperience.es/images/cars/ferrari458600.jpg");
		this.premis.put(1, "http://upload.wikimedia.org/wikipedia/commons/8/8e/Premio_Rally.png");
		this.premis.put(2, "http://4.bp.blogspot.com/-RAvH4gcpc4E/UZFQel9pWsI/AAAAAAAAB4k/6HAm5lAQZxs/s1600/Jamon-Serrano-Bodega-Leyenda.jpg");
		
		int numeroEquip = (int) (Math.random() * 4);
		numeroPremi = (int) (Math.random() * 3);
		
		guanyador = equipsDAM.get(numeroEquip);
		
		nom.setText(guanyador.getNom());
		defensa.setText(guanyador.getDefensa());
		davanter.setText(guanyador.getDavanter());
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				final Bitmap bmp = loadImageFromNetwork(premis.get(numeroPremi));

				premi.post(new Runnable() {

					@Override
					public void run() {
						premi.setImageBitmap(bmp);
					}

				});
			}
		}).start();
	}
	
	public void pulsar(View view) {

	}

	class TascaDescarrega extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {

			return loadImageFromNetwork(params[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			premi.setImageBitmap(result);
		}

	}

	private Bitmap loadImageFromNetwork(String string) {

		Bitmap bitmap = null;

		try {
			bitmap = BitmapFactory.decodeStream((InputStream) new URL(string)
					.getContent());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
}
