package com.example.futbosoci;

import java.io.Serializable;

public class Soci implements Serializable {

	private String nom, cognom, correu;

	public Soci() {

	}

	public Soci(String nom, String cognom, String correu) {
		this.nom = nom;
		this.cognom = cognom;
		this.correu = correu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}

	public String getCorreu() {
		return correu;
	}

	public void setCorreu(String correu) {
		this.correu = correu;
	}

}
