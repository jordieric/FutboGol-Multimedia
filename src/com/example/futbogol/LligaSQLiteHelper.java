package com.example.futbogol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe que estén les funcionalitats de SQLiteOpenHelper
 * 
 * @author Marc Nicolau (marc.nicolau@gmail.com)
 * 
 */
public class LligaSQLiteHelper extends SQLiteOpenHelper {
	// Sentència SQL per crear la taula de Titulars
	private final String SQL_CREATE_LLIGA = "CREATE TABLE Lliga(codi INTEGER PRIMARY KEY, " + " nom TEXT)";

	/**
	 * Constructor amb paràmetres
	 * 
	 * @param context
	 *            el context de l'aplicació
	 * @param nom
	 *            el nom de la base de dades a crear
	 * @param factory
	 *            s'utilitza per crear objectes cursor, o null per defecte
	 * @param versio
	 *            número de versió de la BD. Si és més gran que la versió
	 *            actual, es farà un Upgrade; si és menor es farà un Downgrade
	 */
	public LligaSQLiteHelper(Context context, String nom,
			CursorFactory factory, int versio) {
		super(context, nom, factory, versio);
	}

	/**
	 * Event que es produeix quan s'ha de crear la BD
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// S'executen les sentències SQL de creació de la BD
		db.execSQL(SQL_CREATE_LLIGA);
	}

	/**
	 * Event que es produeix quan s'ha d'actualitzar la BD a una versió superior
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int versioAnterior, int versioNova) {
		// NOTA: Per simplificar l'exemple, aquí s'utilitza directament
		// l'opció d'eliminar la taula anterior i tornar-la a crear buida
		// amb la nova estructura.
		// No obstant, el més habitual és migrar les dades de la taula antiga
		// a la nova, per la qual cosa aquest mètode hauria de fer més coses.

		// S'elimina la versió anterior de la taula
		db.execSQL("DROP TABLE IF EXISTS Lliga");
		// Es crea la nova versió de la taula
		db.execSQL(SQL_CREATE_LLIGA);
	}
}