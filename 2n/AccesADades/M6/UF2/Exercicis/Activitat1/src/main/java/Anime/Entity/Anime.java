package Anime.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name="Anime")
public class Anime  implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id", updatable=false)
	private int Id;
	
	@Column(name="Titol", length=50, nullable=false)
	private String titol;
	
	@Column(name="Sinopsis", length=50, nullable=false)
	private String sinopsis;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Genere", columnDefinition="VARCHAR(30)")
	private Genere genere;
	
	@Column(name="Episodis", columnDefinition="integer DEFAULT 0")
	private int episodis;
	
	@Column(name="DisponibleStreaming")
	private boolean disponibleStreaming;
	
	@Column(name="Puntuacio", columnDefinition="DECIMAL(12, 2) DEFAULT 0.0")
	private double puntuacio;
	
	@Column(name="DataCreacio", columnDefinition="DATETIME DEFAULT CURRENT_TIMESTAMP") //Current_Timestamp ens inicialitza la columna amb la data actual
	private LocalDateTime dataCreacio;

	public Anime() {
		super();
	}

	public Anime(String titol, String sinopsis, boolean disponibleStreaming) {
		super();
		this.titol = titol;
		this.sinopsis = sinopsis;
		this.disponibleStreaming = disponibleStreaming;
	}

	public Anime(int id, String titol, String sinopsis, Genere genere, int episodis, boolean disponibleStreaming, double puntuacio, LocalDateTime dataCreacio) {
		super();
		Id = id;
		this.titol = titol;
		this.sinopsis = sinopsis;
		this.genere = genere;
		this.episodis = episodis;
		this.disponibleStreaming = disponibleStreaming;
		this.puntuacio = puntuacio;
		this.dataCreacio = dataCreacio;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public Genere getGenere() {
		return genere;
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}

	public int getEpisodis() {
		return episodis;
	}

	public void setEpisodis(int episodis) {
		this.episodis = episodis;
	}

	public boolean isDisponibleStreaming() {
		return disponibleStreaming;
	}

	public void setDisponibleStreaming(boolean disponibleStreaming) {
		this.disponibleStreaming = disponibleStreaming;
	}

	public double getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(double puntuacio) {
		this.puntuacio = puntuacio;
	}

	public LocalDateTime getDataCreacio() {
		return dataCreacio;
	}

	public void setDataCreacio(LocalDateTime dataCreacio) {
		this.dataCreacio = dataCreacio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anime other = (Anime) obj;
		return Id == other.Id;
	}

	@Override
	public String toString() {
		return "Anime [Id=" + Id + ", titol=" + titol + ", sinopsis=" + sinopsis + ", genere=" + genere + ", episodis="
				+ episodis + ", disponibleStreaming=" + disponibleStreaming + ", puntuacio=" + puntuacio
				+ ", dataCreacio=" + dataCreacio + "]";
	}
}
