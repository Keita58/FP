package entitat;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Jocs")
public class Jocs implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idJoc")
	private int idJoc;
	
	@Column(name = "Nom", length = 125)
	private String nom;
	
	@Column(name = "Nivell")
	private int nivell;
	
	@Column(name = "Vendes", columnDefinition = "INT DEFAULT 0")
	private int vendes;
	
	@Column(name = "Comprar")
	private boolean comprar;

	@ManyToOne
	@JoinColumn(name = "idPlataforma")
	private plataforma plataforma;
	
	public Jocs() {
		super();
	}

	public Jocs(String nom, int vendes) {
		super();
		this.nom = nom;
		this.vendes = vendes;
	}

	public Jocs(String nom, int nivell, int vendes, boolean comprar) {
		super();
		this.nom = nom;
		this.nivell = nivell;
		this.vendes = vendes;
		this.comprar = comprar;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNivell() {
		return nivell;
	}

	public void setNivell(int nivell) {
		this.nivell = nivell;
	}

	public int getVendes() {
		return vendes;
	}

	public void setVendes(int vendes) {
		this.vendes = vendes;
	}

	public boolean isComprar() {
		return comprar;
	}

	public void setComprar(boolean comprar) {
		this.comprar = comprar;
	}

	public plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(plataforma plataforma) {
		this.plataforma = plataforma;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idJoc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jocs other = (Jocs) obj;
		return idJoc == other.idJoc;
	}

	@Override
	public String toString() {
		return "Jocs [idJoc=" + idJoc + ", nom=" + nom + ", nivell=" + nivell + ", vendes=" + vendes + ", comprar="
				+ comprar + "]";
	}
	
}
