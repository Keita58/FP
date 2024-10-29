package Pokemon.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Pokemon")
public class Pokemon implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idPokemon", updatable=false)
	private int idPokemon;
	
	@Column(name="Nom", length=50, nullable=false)
	private String nom;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Tipus")
	private Tipus tipus;
	
	@Column(name="Descripcio", length=50, nullable=false)
	private String descripcio;
		
	@Column(name="Nivell", columnDefinition="integer DEFAULT 0")
	private int nivell;
	
	@Column(name="Capturat")
	private boolean capturat;
	
	@Column(name="PoderActual", columnDefinition="DECIMAL(12, 2) DEFAULT 0.0")
	private double poderActual;
	
	@Column(name="DataCaptura", columnDefinition="DATETIME DEFAULT CURRENT_TIMESTAMP") //Current_Timestamp ens inicialitza la columna amb la data actual
	private LocalDateTime dataCaptura;

	public Pokemon() {
		super();
	}

	public Pokemon(String nom, String descripcio, boolean capturat) {
		super();
		this.nom = nom;
		this.descripcio = descripcio;
		this.capturat = capturat;
	}

	public Pokemon(int idPokemon, String nom, Tipus tipus, String descripcio, int nivell, boolean capturat,
			double poderActual, LocalDateTime dataCaptura) {
		super();
		this.idPokemon = idPokemon;
		this.nom = nom;
		this.tipus = tipus;
		this.descripcio = descripcio;
		this.nivell = nivell;
		this.capturat = capturat;
		this.poderActual = poderActual;
		this.dataCaptura = dataCaptura;
	}

	public int getIdPokemon() {
		return idPokemon;
	}

	public void setIdPokemon(int idPokemon) {
		this.idPokemon = idPokemon;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Tipus getTipus() {
		return tipus;
	}

	public void setTipus(Tipus tipus) {
		this.tipus = tipus;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public int getNivell() {
		return nivell;
	}

	public void setNivell(int nivell) {
		this.nivell = nivell;
	}

	public boolean isCapturat() {
		return capturat;
	}

	public void setCapturat(boolean capturat) {
		this.capturat = capturat;
	}

	public double getPoderActual() {
		return poderActual;
	}

	public void setPoderActual(double poderActual) {
		this.poderActual = poderActual;
	}

	public LocalDateTime getDataCaptura() {
		return dataCaptura;
	}

	public void setDataCaptura(LocalDateTime dataCaptura) {
		this.dataCaptura = dataCaptura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPokemon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return idPokemon == other.idPokemon;
	}

	@Override
	public String toString() {
		return "Pokemon [idPokemon=" + idPokemon + ", nom=" + nom + ", tipus=" + tipus + ", descripcio=" + descripcio
				+ ", nivell=" + nivell + ", capturat=" + capturat + ", poderActual=" + poderActual + ", dataCaptura="
				+ dataCaptura + "]";
	}
}
