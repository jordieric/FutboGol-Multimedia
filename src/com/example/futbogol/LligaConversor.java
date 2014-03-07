package com.example.futbogol;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Classe conversora d'objectes Titular a BD
 * 
 * @author Marc Nicolau Reixach
 * 
 */
public class LligaConversor {

	private static LligaSQLiteHelper helper;

	/**
	 * Consructor per defecte
	 */
	public LligaConversor() {

	}

	/**
	 * Constructor amb paràmetres
	 * 
	 * @param helper
	 *            l'ajudant de la BD de Titulars
	 */
	public LligaConversor(LligaSQLiteHelper helper) {
		this.helper = helper;
	}

	/**
	 * Desa un nou titular a la taula
	 * 
	 * @param titular
	 *            l'objecte a desar
	 * @return l'id del nou titular desat
	 */
	public long save(ObjecteLliga lliga) {
		long index = -1;
		// s'agafa l'objecte base de dades en mode escriptura
		SQLiteDatabase db = helper.getWritableDatabase();
		// es crea un objecte de diccionari (clau,valor) per indicar els valors
		// a afegir
		ContentValues dades = new ContentValues();

		dades.put("nom", lliga.getNom());
		try {
			// volem veure en el log el que passa
			index = db.insertOrThrow("Lliga", null, dades);
			Log.i("Lliga",
					dades.toString() + " afegida amb el nom " + lliga.getNom());
		} catch (Exception e) {
			// volem reflectir en el log que hi ha hagut un error
			Log.e("Lliga", e.getMessage());
		}
		db.close();
		return index;
	}

	/**
	 * Retorna un cursor amb totes les dades de la taula
	 * 
	 * @return
	 */
	public Cursor getAll() {
		SQLiteDatabase db = helper.getReadableDatabase();

		return db.query(true, "Lliga", new String[] { "nom" }, null, null,
				null, null, null, null);
	}
	
	/**
	 * Esborra el titular passat per paràmetre
	 * 
	 * @param t
	 *            el titular a esborrar
	 * @return la quantitat de titulars eliminats
	 */
	public boolean remove(String lliga) {
		// obtenir l'objecte BD en mode esriptura
		SQLiteDatabase db = helper.getWritableDatabase();

		return db.delete("Lliga", "nom='" + lliga + "'", null) > 0;
	}

	/**
	 * Esborra tots els titulars de la taula
	 * 
	 * @return
	 */
	public boolean removeAll() {
		// obtenir l'objecte BD en mode escriptura
		SQLiteDatabase db = helper.getWritableDatabase();

		return db.delete("Lliga", null, null) > 0;
	}
}