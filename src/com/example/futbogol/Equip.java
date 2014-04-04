package com.example.futbogol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class Equip extends Activity {

	private ImageView p1, d1, d2, d3, m1, m2, m3, g1, g2, g3, g4;
	private File[] fotos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_equip);

		fotos = (File[]) getIntent().getSerializableExtra("fotos");

		p1 = (ImageView) findViewById(R.id.p1);
		d1 = (ImageView) findViewById(R.id.d1);
		d2 = (ImageView) findViewById(R.id.d2);
		d3 = (ImageView) findViewById(R.id.d3);
		m1 = (ImageView) findViewById(R.id.m1);
		m2 = (ImageView) findViewById(R.id.m2);
		m3 = (ImageView) findViewById(R.id.m3);
		g1 = (ImageView) findViewById(R.id.g1);
		g2 = (ImageView) findViewById(R.id.g2);
		g3 = (ImageView) findViewById(R.id.g3);
		g4 = (ImageView) findViewById(R.id.g4);

		try {
			// Assignar a les imatges, els jugadors fets prèviament
			p1.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"Porter"))));

			d1.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"Defensa"))));

			d2.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"Defensa"))));

			d3.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"Defensa"))));

			m1.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"MigCamp"))));

			m2.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"MigCamp"))));

			m3.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"MigCamp"))));

			g1.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"Davanter"))));

			g2.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"Davanter"))));

			g3.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"Davanter"))));

			g4.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"Davanter"))));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.equip, menu);
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
