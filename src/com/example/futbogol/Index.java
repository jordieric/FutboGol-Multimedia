package com.example.futbogol;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.Jugades.Jugades;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class Index extends Activity {

	private ImageView imatge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		imatge = (ImageView) findViewById(R.id.futblin);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index, menu);
		
		return true;
	}
	
	public void jugadesclic(View view){
		Intent jugades = new Intent(Index.this, Jugades.class);
		startActivity(jugades);
	}
	
	public void lligaclic(View view){
		Intent lliga = new Intent(Index.this, Lliga.class);
		startActivity(lliga);
	}
	
	public void pulsar(View view) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				final Bitmap bmp = loadImageFromNetwork("http://www.juegosalairelibre.com/images/futbolin-barcelona-monedero-profesional-modelo-catalan_l.jpg");

				imatge.post(new Runnable() {

					@Override
					public void run() {
						imatge.setImageBitmap(bmp);
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
			imatge.setImageBitmap(result);
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
