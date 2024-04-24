import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="post-evolucio")
public class PostEvolucio extends Evolucio{

	String nom;
	
	public PostEvolucio() {
		super();
	}
	
	public PostEvolucio(String nom) {
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
		return "PostEvolucio [nom=" + nom + "]";
	}
	
}
