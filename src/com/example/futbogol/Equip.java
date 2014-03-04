package com.example.futbogol;

public class Equip{

	private String defensa, davanter, nom;

	public Equip(String defensa, String davanter, String nom) {
		this.defensa = defensa;
		this.davanter = davanter;
		this.nom = nom;
	}

	public String getDefensa() {
		return defensa;
	}

	public void setDefensa(String defensa) {
		this.defensa = defensa;
	}

	public String getDavanter() {
		return davanter;
	}

	public void setDavanter(String davanter) {
		this.davanter = davanter;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Equip " + nom + ". Defensa: " + defensa + ", Davanter: "
				+ davanter;
	}
}
