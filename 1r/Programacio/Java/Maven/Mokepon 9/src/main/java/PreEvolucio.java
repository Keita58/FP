import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pre-evolucio")
public class PreEvolucio extends Evolucio{

	String nom;
	
	public PreEvolucio() {
		super();
	}
	
	public PreEvolucio(String nom) {
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
		return "PreEvolucio [nom=" + nom + "]";
	}
	
}
