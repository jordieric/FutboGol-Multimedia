package com.example.Jugades;

import com.example.futbogol.MenuInicialMusical;
import com.example.futbogol.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class JugadesVideos extends Activity {
	private String jugada;
	private Button titolJugada;
	private VideoView videoView;
	private MediaController mediaController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jugades_videos);

		jugada = getIntent().getStringExtra("Jugada");

		titolJugada = (Button) findViewById(R.id.titoljugades);
		titolJugada.setText(jugada);

		videoView = (VideoView) findViewById(R.id.videojugada);

		if (jugada.equals("Martell")) {

			Uri path = Uri.parse("android.resource://" + getPackageName() + "/"
					+ R.raw.cmartell);

			videoView.setVideoURI(path);

			mediaController = new MediaController(this);
			videoView.setMediaController(mediaController);

			videoView.start();
			mediaController.show();
			videoView.requestFocus();

		} else if (jugada.equals("Globus")) {

			Uri path = Uri.parse("android.resource://" + getPackageName() + "/"
					+ R.raw.cglobus);

			videoView.setVideoURI(path);

			mediaController = new MediaController(this);
			videoView.setMediaController(mediaController);

			videoView.start();
			mediaController.show();
			videoView.requestFocus();
		} else if (jugada.equals("Canvi 1 - 3")) {
			Uri path = Uri.parse("android.resource://" + getPackageName() + "/"
					+ R.raw.canvi13canvi);

			videoView.setVideoURI(path);

			mediaController = new MediaController(this);
			videoView.setMediaController(mediaController);

			videoView.start();
			mediaController.show();
			videoView.requestFocus();
		} else if (jugada.equals("Canvi 1 - 2 - 1")) {
			Uri path = Uri.parse("android.resource://" + getPackageName() + "/"
					+ R.raw.canvi121canvi);

			videoView.setVideoURI(path);

			mediaController = new MediaController(this);
			videoView.setMediaController(mediaController);

			videoView.start();
			mediaController.show();
			videoView.requestFocus();
		} else if (jugada.equals("Canvi 2 - 3 - 1")) {
			Uri path = Uri.parse("android.resource://" + getPackageName() + "/"
					+ R.raw.canvi231saludu);

			videoView.setVideoURI(path);

			mediaController = new MediaController(this);
			videoView.setMediaController(mediaController);

			videoView.start();
			mediaController.show();
			videoView.requestFocus();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jugades_videos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

}
