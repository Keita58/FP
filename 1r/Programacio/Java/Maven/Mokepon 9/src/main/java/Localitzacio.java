import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = "nom")
public class Localitzacio {

	String nom;

	public Localitzacio() {
		super();
	}
	
	public Localitzacio(String nom) {
		super();
		this.nom = nom;
	}

	@XmlElement
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Localitzacio [nom=" + nom + "]";
	}
}
