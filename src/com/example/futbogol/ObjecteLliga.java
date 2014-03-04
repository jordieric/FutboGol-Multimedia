package com.example.futbogol;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ObjecteLliga implements Serializable{

	private String nom;
	private int codi = 0;
	private int comptador;
	
	public ObjecteLliga(){
	}

	public ObjecteLliga(String nom) {
		comptador = codi;
		this.nom = nom;
		this.codi = comptador;
		this.codi++;
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
