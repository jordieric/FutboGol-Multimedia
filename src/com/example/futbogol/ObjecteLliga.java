package com.example.futbogol;

import java.io.Serializable;

public class ObjecteLliga implements Serializable {

	private String nom;
	private int codi;

	public ObjecteLliga() {
	}

	public ObjecteLliga(String nom) {
		this.nom = nom;
	}

	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
