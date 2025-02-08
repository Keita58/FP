package Examen.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Personatge")
public class Personatge implements Serializable{

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idPersonatge", updatable = false, insertable = false)
	int idPersonatge;
	
	@Column(name = "Nom", length = 35)
	String nom;
	
	@Column(name = "Huma")
	boolean huma;
	
	@Column(name = "Vida", columnDefinition = "integer default 0")
	int vida;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "PersonatgesQueTenenArma", joinColumns = {@JoinColumn(name = "idPersonatge")}, inverseJoinColumns = {@JoinColumn(name = "idArma")})
    private Set<Armes> armes = new HashSet<>();

	public Personatge() {
		super();
	}

	public Personatge(int idPersonatge, String nom, boolean huma, int vida) {
		super();
		this.idPersonatge = idPersonatge;
		this.nom = nom;
		this.huma = huma;
		this.vida = vida;
	}

	public int getIdPersonatge() {
		return idPersonatge;
	}

	public void setIdPersonatge(int idPersonatge) {
		this.idPersonatge = idPersonatge;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isHuma() {
		return huma;
	}

	public void setHuma(boolean huma) {
		this.huma = huma;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public Set<Armes> getArmes() {
		return armes;
	}

	public void setArmes(Set<Armes> armes) {
		this.armes = armes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPersonatge);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personatge other = (Personatge) obj;
		return idPersonatge == other.idPersonatge;
	}

	@Override
	public String toString() {
		return "Personatge [idPersonatge=" + idPersonatge + ", nom=" + nom + ", huma=" + huma + ", vida=" + vida  + "]";
	}
	
}
