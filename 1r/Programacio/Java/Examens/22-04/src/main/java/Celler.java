import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="celler")
@XmlType(propOrder = {"nom","gins"})
public class Celler {

	private String nom;
	private ArrayList<Gin> gins = new ArrayList<>();
	
	public Celler() {
		super();
	}

	public Celler(String nom, ArrayList<Gin> gins) {
		super();
		this.nom = nom;
		this.gins = gins;
	}
	
	@XmlElement
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@XmlElementWrapper(name="gins")
	@XmlElement(name="gin")
	public ArrayList<Gin> getGins() {
		return gins;
	}
	
	public void setGins(ArrayList<Gin> gins) {
		this.gins = gins;
	}

	@Override
	public String toString() {
		return "Celler [nom=" + nom + ", gins=" + gins + "]";
	}
	
}
