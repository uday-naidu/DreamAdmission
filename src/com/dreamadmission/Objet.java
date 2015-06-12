package com.dreamadmission;

public class Objet {

	private Groupe groupe;
	private String nom;

	public Objet(Groupe groupe, String nom) {
		super();
		this.groupe = groupe;
		this.nom = nom;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
