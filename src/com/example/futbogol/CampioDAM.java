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
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class CampioDAM extends Activity {

	private Map<Integer, Equipo> equipsDAM = new HashMap<Integer, Equipo>();
	private Equipo guanyador;
	private TextView nom, defensa, davanter;
	private ImageView premi;
	private Map<Integer, String> premis = new HashMap<Integer, String>();
	private int numeroPremi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campio_dam);

		nom = (TextView) findViewById(R.id.nom);
		defensa = (TextView) findViewById(R.id.defensa);
		davanter = (TextView) findViewById(R.id.tvCognom);
		premi = (ImageView) findViewById(R.id.premi);

		this.equipsDAM.put(0, new Equipo("Eric Gutierrez", "Jordi Coll",
				"Torpedos"));
		this.equipsDAM.put(1, new Equipo("Oriol Comas", "Aniol Ronaldo",
				"Tirites"));
		this.equipsDAM.put(2, new Equipo("Marc Gurt", "Mercedes Zorrilla",
				"Persistencia"));
		this.equipsDAM.put(3, new Equipo("Dani Cano", "Ernest Puigdemont",
				"Pokemons"));
		this.equipsDAM.put(4, new Equipo("David Roig", "Marc Soy", "Suspal"));
		this.equipsDAM.put(5, new Equipo("Marc Nicolau", "Frederico",
				"En 5 minuts..."));
		this.equipsDAM.put(6, new Equipo("Xicu.Traïdor", "Jordi.ASIX",
				"ASIX - DAM....."));
		this.equipsDAM.put(7, new Equipo("Dolors", "Jaume", "DJ !StopMusic"));
		this.equipsDAM
				.put(8, new Equipo("Navpreet", "Jaspreet", "Force India"));
		this.equipsDAM.put(9, new Equipo("Agustín", "Toni Ricart",
				"A vore nois / xiquets!"));
		this.equipsDAM.put(10, new Equipo("Aleix Guix", "Jordi Reixach",
				"DRuendes"));
		this.equipsDAM.put(11, new Equipo("Jordi Juvanteny", "Marta",
				"Coixos Team!"));

		this.premis.put(0,
				"http://www.motorexperience.es/images/cars/ferrari458600.jpg");
		this.premis
				.put(1,
						"http://upload.wikimedia.org/wikipedia/commons/8/8e/Premio_Rally.png");
		this.premis
				.put(2,
						"http://4.bp.blogspot.com/-RAvH4gcpc4E/UZFQel9pWsI/AAAAAAAAB4k/6HAm5lAQZxs/s1600/Jamon-Serrano-Bodega-Leyenda.jpg");
		this.premis
				.put(3,
						"http://1.bp.blogspot.com/_0U5nGYWz87U/S-sObO4jbbI/AAAAAAAAAGc/JJY6ISi5VKY/s320/pu%C3%B1etazo.jpg");

		int numeroEquip = (int) (Math.random() * 11);
		numeroPremi = (int) (Math.random() * 4);

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.campio_dam, menu);
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
