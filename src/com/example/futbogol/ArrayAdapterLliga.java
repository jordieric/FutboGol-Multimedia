package com.example.futbogol;

import android.app.Activity;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ArrayAdapterLliga extends BaseAdapter {

	private Activity context;
	private Cursor llistaLligues;

	public ArrayAdapterLliga(Activity context,
			Cursor llistaLligues) {
		super();
		this.context = context;
		this.llistaLligues = llistaLligues;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {

		// Rescatem cada item del listview i el definim en el nostre layout
		View item = convertView;
		
		ObjecteLliga c = (ObjecteLliga) getItem(position);
		
//		item = context.getLayoutInflater().inflate(
//				R.layout.activity_array_adapter_lliga, null);

		if (item == null){
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.activity_array_adapter_lliga, null);
		}

		// Definim els elements que  el nostre layout
		TextView nomlliga = (TextView) item.findViewById(R.id.lligaLlista);
		nomlliga.setText(c.getNom());

		return item;
	}

	@Override
	public int getCount() {
		return llistaLligues.getCount();
	}

	@Override
	public ObjecteLliga getItem(int pos) {
		ObjecteLliga olliga = new ObjecteLliga();
		if(llistaLligues.moveToPosition(pos)) {
			olliga.setCodi(llistaLligues.getInt(0));
			olliga.setNom(llistaLligues.getString(1));
		}	
		return olliga;
	}

	@Override
	public long getItemId(int position) {
		return getItemId(position);
	}
}