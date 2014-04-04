package com.example.Jugades;

import com.example.futbogol.Accio;
import com.example.futbogol.MenuInicialMusical;
import com.example.futbogol.R;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class Jugades extends Activity {

	private Accio accio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jugades);

		accio = (Accio) getIntent().getSerializableExtra("accio");
	}

	public void clicmartell(View view) {
		if (accio == Accio.JUGADES) {
			Intent martell = new Intent(Jugades.this, JugadesDetall.class);
			martell.putExtra("Jugada", "Martell");
			startActivity(martell);
		} else {
			if (MenuInicialMusical.mediaPlayer != null) {
				MenuInicialMusical.mediaPlayer.stop();
			}
			Intent martell = new Intent(Jugades.this, JugadesVideos.class);
			martell.putExtra("Jugada", "Martell");
			startActivity(martell);
		}
	}

	public void clicglobus(View view) {
		if (accio == Accio.JUGADES) {
			Intent globus = new Intent(Jugades.this, JugadesDetall.class);
			globus.putExtra("Jugada", "Globus");
			startActivity(globus);
		} else {
			if (MenuInicialMusical.mediaPlayer != null) {
				MenuInicialMusical.mediaPlayer.stop();
			}
			Intent globus = new Intent(Jugades.this, JugadesVideos.class);
			globus.putExtra("Jugada", "Globus");
			startActivity(globus);
		}
	}

	public void clic13(View view) {
		if (accio == Accio.JUGADES) {
			Intent c13 = new Intent(Jugades.this, JugadesDetall.class);
			c13.putExtra("Jugada", "Canvi 1 - 3");
			startActivity(c13);
		} else {
			if (MenuInicialMusical.mediaPlayer != null) {
				MenuInicialMusical.mediaPlayer.stop();
			}
			Intent c13 = new Intent(Jugades.this, JugadesVideos.class);
			c13.putExtra("Jugada", "Canvi 1 - 3");
			startActivity(c13);
		}
	}

	public void clic121(View view) {
		if (accio == Accio.JUGADES) {
			Intent c121 = new Intent(Jugades.this, JugadesDetall.class);
			c121.putExtra("Jugada", "Canvi 1 - 2 - 1");
			startActivity(c121);
		} else {
			if (MenuInicialMusical.mediaPlayer != null) {
				MenuInicialMusical.mediaPlayer.stop();
			}
			Intent c121 = new Intent(Jugades.this, JugadesVideos.class);
			c121.putExtra("Jugada", "Canvi 1 - 2 - 1");
			startActivity(c121);
		}
	}

	public void clic231(View view) {
		if (accio == Accio.JUGADES) {
			Intent c231 = new Intent(Jugades.this, JugadesDetall.class);
			c231.putExtra("Jugada", "Canvi 2 - 3 - 1");
			startActivity(c231);
		} else {
			if (MenuInicialMusical.mediaPlayer != null) {
				MenuInicialMusical.mediaPlayer.stop();
			}
			Intent c231 = new Intent(Jugades.this, JugadesVideos.class);
			c231.putExtra("Jugada", "Canvi 2 - 3 - 1");
			startActivity(c231);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jugades, menu);
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
