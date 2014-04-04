package com.example.futbogol;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CrearEquipFotos extends Activity {

	private File tempImageFilePorter, tempImageFileDefensa,
			tempImageFileMigCamp, tempImageFileDavanter;
	private Button generarEquip;
	private Boolean porter = false, defensa = false, migcamp = false,
			davanter = false;

	// un codi per l'aplicació a obrir
	static final int CAMERA_APP_CODE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_equip_fotos);

		generarEquip = (Button) findViewById(R.id.generarequip);

	}

	public void porter(View view) throws IOException {
		if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
			// intenció de fer una foto
			Intent takePictureIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			// crear la ruta del fitxer on desar la foto
			tempImageFilePorter = crearFitxer("Porter");
			// li passem paràmetres a l'Inent per indicar que es vol guarda
			// la captura en un fitxer
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(tempImageFilePorter));
			// inciar l'intent
			startActivityForResult(takePictureIntent, CAMERA_APP_CODE);

			// Indiquem que s'ha fet la foto
			porter = true;
		} else {
			Toast.makeText(this, "No hi ha cap aplicació per capturar fotos",
					Toast.LENGTH_LONG).show();
		}
	}

	public void defensa(View view) throws IOException {
		if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
			// intenció de fer una foto
			Intent takePictureIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			// crear la ruta del fitxer on desar la foto
			tempImageFileDefensa = crearFitxer("Defensa");
			// li passem paràmetres a l'Inent per indicar que es vol guarda
			// la captura en un fitxer
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(tempImageFileDefensa));
			// inciar l'intent
			startActivityForResult(takePictureIntent, CAMERA_APP_CODE);

			// Indiquem que s'ha fet la foto
			defensa = true;
		} else {
			Toast.makeText(this, "No hi ha cap aplicació per capturar fotos",
					Toast.LENGTH_LONG).show();
		}
	}

	public void migcamp(View view) throws IOException {
		if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
			// intenció de fer una foto
			Intent takePictureIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			// crear la ruta del fitxer on desar la foto
			tempImageFileMigCamp = crearFitxer("Migcamp");
			// li passem paràmetres a l'Inent per indicar que es vol guarda
			// la captura en un fitxer
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(tempImageFileMigCamp));
			// inciar l'intent
			startActivityForResult(takePictureIntent, CAMERA_APP_CODE);

			// Indiquem que s'ha fet la foto
			migcamp = true;
		} else {
			Toast.makeText(this, "No hi ha cap aplicació per capturar fotos",
					Toast.LENGTH_LONG).show();
		}
	}

	public void davanter(View view) throws IOException {
		if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
			// intenció de fer una foto
			Intent takePictureIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			// crear la ruta del fitxer on desar la foto
			tempImageFileDavanter = crearFitxer("Davanter");
			// li passem paràmetres a l'Inent per indicar que es vol guarda
			// la captura en un fitxer
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(tempImageFileDavanter));
			// inciar l'intent
			startActivityForResult(takePictureIntent, CAMERA_APP_CODE);

			// Indiquem que s'ha fet la foto
			davanter = true;
		} else {
			Toast.makeText(this, "No hi ha cap aplicació per capturar fotos",
					Toast.LENGTH_LONG).show();
		}
	}

	public void generarequip(View view) {

		if (porter || defensa || migcamp || davanter) {

			if (reduirQualitat(tempImageFilePorter)) {
				if (reduirQualitat(tempImageFileDefensa)) {
					if (reduirQualitat(tempImageFileMigCamp)) {
						if (reduirQualitat(tempImageFileDavanter)) {
							Intent equipazo = new Intent(CrearEquipFotos.this,
									Equip.class);

							Bundle b = new Bundle();
							b.putSerializable("Porter", tempImageFilePorter);
							b.putSerializable("Defensa", tempImageFileDefensa);
							b.putSerializable("MigCamp", tempImageFileMigCamp);
							b.putSerializable("Davanter", tempImageFileDavanter);
							equipazo.putExtras(b);
							startActivity(equipazo);
						} else {
							Toast.makeText(
									this,
									"Has de fer la foto del DAVANTER en VERTICAL!",
									Toast.LENGTH_LONG).show();
						}
					} else {
						Toast.makeText(
								this,
								"Has de fer la foto del MIGCAMPISTA en VERTICAL!",
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(this,
							"Has de fer la foto de la DEFENSA en VERTICAL!",
							Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(this,
						"Has de fer la foto del PORTER en VERTICAL!",
						Toast.LENGTH_LONG).show();
			}
		} else {

			Toast.makeText(this, "Has de fer totes les fotos!",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Mètode que comprova si hi ha una aplicició per a captura de fotos
	 * 
	 * @param context
	 * @param action
	 * @return true si existeix, false en cas contrari
	 */
	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	/**
	 * Crea la ruta absoluta per a un nou fitxer temporal
	 * 
	 * @return L'objecte File que representa el fitxer
	 */
	private File crearFitxer(String nom) {
		// Create an image file name
		String imageFileName = "foto" + nom + ".jpg";
		File path = new File(Environment.getExternalStorageDirectory(),
				this.getPackageName());
		if (!path.exists())
			path.mkdirs();

		return new File(path, imageFileName);
	}

	public boolean reduirQualitat(File tempImageFile) {

		int MAX_IMAGE_SIZE = 200 * 1024;
		Bitmap bmpPic = BitmapFactory.decodeFile(tempImageFile.getPath());

		if (bmpPic.getWidth() > bmpPic.getHeight()) {
			return false;
		} else {

			if ((bmpPic.getWidth() >= 1024) && (bmpPic.getHeight() >= 1024)) {
				BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
				bmpOptions.inSampleSize = 1;
				while ((bmpPic.getWidth() >= 1024)
						&& (bmpPic.getHeight() >= 1024)) {
					bmpOptions.inSampleSize++;
					bmpPic = BitmapFactory.decodeFile(tempImageFile.getPath(),
							bmpOptions);
				}
			}
			int compressQuality = 104;
			int streamLength = MAX_IMAGE_SIZE;
			while (streamLength >= MAX_IMAGE_SIZE) {
				ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
				compressQuality -= 5;
				bmpPic.compress(Bitmap.CompressFormat.JPEG, compressQuality,
						bmpStream);
				byte[] bmpPicByteArray = bmpStream.toByteArray();
				streamLength = bmpPicByteArray.length;
			}
			try {
				FileOutputStream bmpFile = new FileOutputStream(tempImageFile);
				bmpPic.compress(Bitmap.CompressFormat.JPEG, compressQuality,
						bmpFile);
				bmpFile.flush();
				bmpFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crear_equip_fotos, menu);
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
