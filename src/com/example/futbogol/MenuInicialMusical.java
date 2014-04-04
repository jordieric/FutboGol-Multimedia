package com.example.futbogol;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.Jugades.Jugades;

public class MenuInicialMusical extends Activity {

	static final String SONG = "http://www.fcsongs.com/themesongs/UEFA%20Champions%20League%20Theme.mp3";
	// l'objecte amb el qual es fa la reproducció del fitxer
	public static MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_inicial_musical);

		new TascaCarrega().execute(SONG);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_inicial_musical, menu);
		return true;
	}

	public void jugades(View view) {
		Intent jugades = new Intent(MenuInicialMusical.this, Jugades.class);
		Bundle b = new Bundle();
		b.putSerializable("accio", Accio.JUGADES);
		jugades.putExtras(b);
		startActivity(jugades);
	}

	public void videojugades(View view) {
		Intent videos = new Intent(MenuInicialMusical.this, Jugades.class);
		Bundle b = new Bundle();
		b.putSerializable("accio", Accio.VIDEO);
		videos.putExtras(b);
		startActivity(videos);
	}

	public void ferequip(View view) {
		Intent ferequip = new Intent(MenuInicialMusical.this,
				CrearEquipFotos.class);
		startActivity(ferequip);
	}

	public void lligues(View view) {
		Intent lligues = new Intent(MenuInicialMusical.this, Lliga.class);
		startActivity(lligues);
	}

	public void simulagol(View view) {
		if (MenuInicialMusical.mediaPlayer != null) {
			MenuInicialMusical.mediaPlayer.stop();
		}
		Intent simulagol = new Intent(MenuInicialMusical.this,
				GravacioGol.class);
		startActivity(simulagol);
	}

	/**
	 * Implementació de la tasca asíncrona
	 * 
	 * @author Marc Nicolau
	 * 
	 */
	class TascaCarrega extends AsyncTask<String, Void, Void> {
		ProgressDialog pd = new ProgressDialog(MenuInicialMusical.this);

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pd.setMessage("Espera el gran HIMNE...");
			pd.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			mediaPlayer = MediaPlayer.create(MenuInicialMusical.this,
					Uri.parse(params[0]));
			mediaPlayer.start();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			pd.dismiss();
			super.onPostExecute(result);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// action with ID action_refresh was selected
		case R.id.stopMusic:
			if (MenuInicialMusical.mediaPlayer != null) {
				MenuInicialMusical.mediaPlayer.stop();
			}
			break;
		}
		return true;
	}

}
