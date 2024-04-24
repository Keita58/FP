import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="ingredient")
@XmlType(propOrder = {"nom"})
public class Ingredient {
	
	private String nom;
	private double vol;
	
	public Ingredient() {
		super();
	}

	public Ingredient(String nom, double vol) {
		super();
		this.nom = nom;
		this.vol = vol;
	}

	@XmlAttribute
	public double getVol() {
		return vol;
	}

	public void setVol(double vol) {
		this.vol = vol;
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
		return "Ingredient [nom=" + nom + ", vol=" + vol + "]";
	}
}
