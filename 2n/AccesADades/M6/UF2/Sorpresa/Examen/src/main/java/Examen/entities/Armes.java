package Examen.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Armes")
public class Armes implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idArma", updatable = false, insertable = false)
	int idArma;
	
	@Column(name = "Arma", length = 75)
	String arma;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Mortalitat")
	private Mortalitat mortalitat;
	
	@Column(name = "PuntsRestarVida", columnDefinition = "integer default 0")
	int puntsRestarVida;
	
	@Column(name = "Combat")
	boolean combat;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "armes", fetch = FetchType.EAGER)
    private Set<Personatge> personatges = new HashSet<>();

	public Armes() {
		super();
	}

	public Armes(int idArma, String arma, Mortalitat mortalitat, int puntsRestarVida, boolean combat) {
		super();
		this.idArma = idArma;
		this.arma = arma;
		this.mortalitat = mortalitat;
		this.puntsRestarVida = puntsRestarVida;
		this.combat = combat;
	}

	public int getIdArma() {
		return idArma;
	}

	public void setIdArma(int idArma) {
		this.idArma = idArma;
	}

	public String getArma() {
		return arma;
	}

	public void setArma(String arma) {
		this.arma = arma;
	}

	public Mortalitat getMortalitat() {
		return mortalitat;
	}

	public void setMortalitat(Mortalitat mortalitat) {
		this.mortalitat = mortalitat;
	}

	public int getPuntsRestarVida() {
		return puntsRestarVida;
	}

	public void setPuntsRestarVida(int puntsRestarVida) {
		this.puntsRestarVida = puntsRestarVida;
	}

	public boolean isCombat() {
		return combat;
	}

	public void setCombat(boolean combat) {
		this.combat = combat;
	}

	public Set<Personatge> getPersonatges() {
		return personatges;
	}

	public void setPersonatges(Set<Personatge> personatges) {
		this.personatges = personatges;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idArma);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Armes other = (Armes) obj;
		return idArma == other.idArma;
	}

	@Override
	public String toString() {
		return "Armes [idArma=" + idArma + ", arma=" + arma + ", mortalitat=" + mortalitat + ", puntsRestarVida="
				+ puntsRestarVida + ", combat=" + combat + "]";
	}
	
}
