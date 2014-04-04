package com.example.futbogol;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;

public class GravacioGol extends Activity {

	private static final String LOG_TAG = "GravacioAudio";
	private static String mFileName = null;

	private MediaRecorder mRecorder = null;
	private MediaPlayer mPlayer = null;

	private boolean mStartRecording = true;
	private boolean mStartPlaying = true;

	private ImageButton rec;
	private ImageButton play;

	private Chronometer crono;

	/**
	 * Constructor
	 */
	public GravacioGol() {
		mFileName = "gravacioGol.3gp";
		File path = new File(Environment.getExternalStorageDirectory(), LOG_TAG);
		if (!path.exists())
			path.mkdirs();

		mFileName = new File(path, mFileName).getAbsolutePath();
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_gravacio_gol);
		rec = (ImageButton) findViewById(R.id.btnRec);
		play = (ImageButton) findViewById(R.id.btnPlay);
		crono = (Chronometer) findViewById(R.id.cronometre);
		rec.setBackgroundResource(R.drawable.football);
		//play.setBackgroundResource(R.drawable.footballplay);
	}

	public void iniciarGravacio(View v) {
		onRecord(mStartRecording);
		if (mStartRecording) {
			rec.setBackgroundResource(R.drawable.footballstop);
			play.setBackgroundResource(0);
		} else {
			rec.setBackgroundResource(R.drawable.football);
			play.setBackgroundResource(R.drawable.footballplay);
		}
		mStartRecording = !mStartRecording;
	}

	public void reproduirGravacio(View v) {
		onPlay(mStartPlaying);
		if (mStartPlaying) {
			play.setBackgroundResource(R.drawable.footballstop);
		} else {
			play.setBackgroundResource(R.drawable.footballplay);
		}
		mStartPlaying = !mStartPlaying;
	}

	private void onRecord(boolean start) {
		if (start) {
			startRecording();
		} else {
			stopRecording();
		}
	}

	private void onPlay(boolean start) {
		if (start) {
			startPlaying();
		} else {
			stopPlaying();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (mRecorder != null) {
			mRecorder.release();
			mRecorder = null;
		}

		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}

	/**
	 * Iniciar la reproducció
	 */
	private void startPlaying() {
		mPlayer = new MediaPlayer();
		try {
			mPlayer.setDataSource(mFileName);
			mPlayer.prepare();
			mPlayer.start();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}
	}

	/**
	 * Aturar la reproducció
	 */
	private void stopPlaying() {
		mPlayer.release();
		mPlayer = null;
	}

	/**
	 * Iniciar la gravació
	 */
	private void startRecording() {
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}

		mRecorder.start();
		crono.setBase(SystemClock.elapsedRealtime());
		crono.start();
	}

	/**
	 * Aturar la gravació
	 */
	private void stopRecording() {
		mRecorder.stop();
		crono.stop();
		mRecorder.release();
		mRecorder = null;
	}

}