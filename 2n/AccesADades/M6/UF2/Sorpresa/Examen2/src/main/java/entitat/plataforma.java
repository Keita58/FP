package entitat;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Plataforma")
public class plataforma implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPlataforma", nullable = false, updatable = false)
	private int idPlataforma;
	
	@Column(name = "NomPlataforma", length = 25, nullable = false)
	private String nomPlataforma;
	
	@Column(name = "Pagat")
	private boolean pagat;
	
	@Column(name = "Puntuacio", columnDefinition = "INT DEFAULT 0")
	private int puntuacio;

	public plataforma() {
		super();
	}

	public plataforma(String nomPlataforma, boolean pagat) {
		super();
		this.nomPlataforma = nomPlataforma;
		this.pagat = pagat;
	}

	public plataforma(String nomPlataforma, boolean pagat, int puntuacio) {
		super();
		this.nomPlataforma = nomPlataforma;
		this.pagat = pagat;
		this.puntuacio = puntuacio;
	}

	public int getIdPlataforma() {
		return idPlataforma;
	}

	public void setIdPlataforma(int idPlataforma) {
		this.idPlataforma = idPlataforma;
	}

	public String getNomPlataforma() {
		return nomPlataforma;
	}

	public void setNomPlataforma(String nomPlataforma) {
		this.nomPlataforma = nomPlataforma;
	}

	public boolean isPagat() {
		return pagat;
	}

	public void setPagat(boolean pagat) {
		this.pagat = pagat;
	}

	public int getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(int puntuacio) {
		this.puntuacio = puntuacio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPlataforma);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		plataforma other = (plataforma) obj;
		return idPlataforma == other.idPlataforma;
	}

	@Override
	public String toString() {
		return "plataforma [idPlataforma=" + idPlataforma + ", nomPlataforma=" + nomPlataforma + ", pagat=" + pagat
				+ ", puntuacio=" + puntuacio + "]";
	}
	
}
