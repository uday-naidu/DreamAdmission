package com.dreamadmission;

import java.util.ArrayList;

public class Groupe {
	private String nom;
	private ArrayList<Objet> objets;

	public Groupe(String nom) {
		super();
		this.nom = nom;
		this.objets = new ArrayList<Objet>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Objet> getObjets() {
		return objets;
	}

	public void setObjets(ArrayList<Objet> objets) {
		this.objets = objets;
	}

}
