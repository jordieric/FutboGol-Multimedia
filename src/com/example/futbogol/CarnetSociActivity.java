package com.example.futbogol;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futbosoci.Soci;

public class CarnetSociActivity extends Activity {

	private String correu;
	private File f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carnet_soci);

		ImageView imatge = (ImageView) findViewById(R.id.imgView);
		TextView nom = (TextView) findViewById(R.id.csNom);
		TextView cognom = (TextView) findViewById(R.id.csCognom);
		TextView num = (TextView) findViewById(R.id.csNumSoci);
		try {
			imatge.setImageBitmap(Media.getBitmap(
					getContentResolver(),
					Uri.fromFile((File) getIntent().getSerializableExtra(
							"Imatge"))));

			Soci soci = (Soci) getIntent().getSerializableExtra("Soci");
			nom.setText("Nom: " + soci.getNom());
			cognom.setText("Cognom: " + soci.getCognom());
			num.setText("Soci n�: " + 00000001);
			correu = soci.getCorreu();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Capturar el layout del carnet i converti-lo a jpg per guardar-lo a la
		// sd.
		View memecontentView = findViewById(R.id.layoutCarnet);
		View v1 = memecontentView;
		v1.setDrawingCacheEnabled(true);

		v1.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

		v1.layout(0, 0, v1.getMeasuredWidth(), v1.getMeasuredHeight());
		v1.buildDrawingCache(true);

		Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();

		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		f = new File(Environment.getExternalStorageDirectory() + File.separator
				+ "carnet.jpg");
		try {
			f.createNewFile();
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(bytes.toByteArray());
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Toast.makeText(getApplicationContext(),
				"Tens el carnet a " + f.getPath(), Toast.LENGTH_SHORT).show();
		v1.destroyDrawingCache();

		// Enviar correu
		enviarCorreu();

		// Splash
		new Handler().postDelayed(new Runnable() {
			public void run() {
				// Quan pasen 10 segons mostra la activitat Index.
				Intent intent = new Intent(CarnetSociActivity.this,
						MenuInicialMusical.class);
				startActivity(intent);
				finish();
			};
		}, 10000);
	}

	private void enviarCorreu() {

		Intent intentEnviar = new Intent(android.content.Intent.ACTION_SEND);
		intentEnviar.putExtra(android.content.Intent.EXTRA_SUBJECT,
				"Carnet FUTBOSOCI");
		intentEnviar.putExtra(android.content.Intent.EXTRA_EMAIL,
				new String[] { correu });

		intentEnviar.setType("text/plain");
		intentEnviar.putExtra(android.content.Intent.EXTRA_TEXT,
				"Aqui tens el carnet de soci de FUTBOGOL");
		intentEnviar.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

		intentEnviar.setType("image/jpeg");
		intentEnviar.putExtra(Intent.EXTRA_STREAM,
				Uri.parse(f.getAbsolutePath()));
		startActivity(Intent.createChooser(intentEnviar, "FutboSoci"));

		// Properties props = new Properties();
		// props.put("mail.smtp.host", "smtp.gmail.com");
		// props.put("mail.smtp.socketFactory.port", "465");
		// props.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.port", "465");
		//
		// Session session = Session.getDefaultInstance(props,
		// new javax.mail.Authenticator() {
		// protected PasswordAuthentication getPasswordAuthentication() {
		// return new PasswordAuthentication(
		// "jcoll@infobosccoma.net", "XXXXXX");
		// }
		// });
		//
		// try {
		//
		// // Per enviar el correu
		// MimeMessage m = new MimeMessage(session);
		//
		// // Qui envia el correu.
		// m.setFrom(new InternetAddress("jcoll@infobosccoma.net"));
		//
		// // Assumpte
		// m.setSubject("Carnet FUTBOSOCI");
		//
		// // Missatge
		// BodyPart text = new MimeBodyPart();
		// text.setText("Aqui tens el carnet de soci de FUTBOGOL");
		//
		// // Imatge
		// BodyPart adjunt = new MimeBodyPart();
		// adjunt.setDataHandler(new DataHandler(new FileDataSource(f
		// .getAbsolutePath())));
		// adjunt.setFileName("FutboSoci.jpg");
		//
		// // Crea el correu
		// MimeMultipart multiParte = new MimeMultipart();
		// multiParte.addBodyPart(text);
		// multiParte.addBodyPart(adjunt);
		//
		// m.setContent(multiParte);
		// m.setRecipients(MimeMessage.RecipientType.TO,
		// InternetAddress.parse(correu));
		//
		// // Envia el correu
		// Transport t = session.getTransport("smtp");
		// t.connect("jcoll@infobosccoma.net", "Bxorogi7");
		// t.sendMessage(m, m.getAllRecipients());
		//
		// Toast.makeText(getApplicationContext(),
		// "Tens el carnet al correu company del FUTBOLIN!",
		// Toast.LENGTH_SHORT).show();
		//
		// t.close();
		//
		// } catch (MessagingException e) {
		// e.printStackTrace();
		// }
	}
}
