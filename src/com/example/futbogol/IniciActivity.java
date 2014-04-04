package com.example.futbogol;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futbosoci.Soci;

public class IniciActivity extends Activity {

	// un codi per l'aplicació a obrir
	static final int CAMERA_APP_CODE = 100;

	// el fitxer on es guardarà la imatge
	private File tempImageFile;

	private String nom, cognom, correu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inici);
	}

	public void btnFotoClick(View v) {

		nom = ((EditText) findViewById(R.id.iniciNom)).getText().toString();
		cognom = ((EditText) findViewById(R.id.iniciCognom)).getText()
				.toString();
		correu = ((EditText) findViewById(R.id.iniciCorreu)).getText()
				.toString();

		if (nom.equals("") || cognom.equals("") || correu.equals("")) {
			Toast.makeText(this, "Omple els camps!", Toast.LENGTH_SHORT).show();
		} else {
			if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
				// intenció de fer una foto

				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				// crear la ruta del fitxer on desar la foto
				tempImageFile = crearFitxer();
				// li passem paràmetres a l'Inent per indicar que es vol guarda
				// la captura en un fitxer
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(tempImageFile));
				// inciar l'intent
				startActivityForResult(takePictureIntent, CAMERA_APP_CODE);

			} else {
				Toast.makeText(this,
						"No hi ha cap aplicació per capturar fotos",
						Toast.LENGTH_LONG).show();
			}
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
	 * Mètode que respon a l'event clic del botó
	 * 
	 * @param view
	 * @throws IOException
	 */

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA_APP_CODE) {
			if (resultCode == RESULT_OK) {
				if (reduirQualitat()) {
					Intent i = new Intent(IniciActivity.this,
							CarnetSociActivity.class);

					Soci soci = new Soci(nom, cognom, correu);
					Bundle b = new Bundle();
					b.putSerializable("Soci", soci);
					b.putSerializable("Imatge", tempImageFile);
					i.putExtras(b);

					startActivity(i);
				} else {
					Toast.makeText(this,
							"Has de fer la foto en VERTICAL!",
							Toast.LENGTH_LONG).show();
				}

			}
		}
	}

	/**
	 * Crea la ruta absoluta per a un nou fitxer temporal
	 * 
	 * @return L'objecte File que representa el fitxer
	 */
	private File crearFitxer() {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		String imageFileName = "foto" + timeStamp + ".jpg";
		File path = new File(Environment.getExternalStorageDirectory(),
				this.getPackageName());
		if (!path.exists())
			path.mkdirs();

		return new File(path, imageFileName);
	}

	public boolean reduirQualitat() {

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
			}
			return true;
		}

	}

}
