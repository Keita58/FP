package com.example.listview_niko;

import java.util.Date;

public class Titular {
	private String nom;
	private String cognom;
	private String telefon;
	private String adreca;
	private String mail;
	private String dataNaixement;

	public Titular(String nom, String cognom, String telefon, String adreca, String mail, String dataNaixement) {
		this.nom = nom;
		this.cognom = cognom;
		this.telefon = telefon;
		this.adreca = adreca;
		this.mail = mail;
		this.dataNaixement = dataNaixement;
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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdreca() {
		return adreca;
	}

	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDataNaixement() {
		return dataNaixement;
	}

	public void setDataNaixement(String dataNaixement) {
		this.dataNaixement = dataNaixement;
	}
}