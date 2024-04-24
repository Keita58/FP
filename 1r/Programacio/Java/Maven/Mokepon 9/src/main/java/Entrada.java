import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//l'element es diu persona en el XML
@XmlRootElement (name = "entrada")
//l'ordre en que apareixeran els seus elements interns en el XML
@XmlType(propOrder = {"nom","tipus","pes","evolucions","localitzacio"})
public class Entrada {
    
    private int id;
    private String nom, tipus;
    private double pes;
    private Evolucio evolucions;
    private ArrayList<String> localitzacions = new ArrayList<>();

    public Entrada() {
		super();
	}
    
	public Entrada(int id, String nom, String tipus, double pes, Evolucio evolucions,
			ArrayList<String> localitzacio) {
		super();
		this.id = id;
		this.nom = nom;
		this.tipus = tipus;
		this.pes = pes;
		this.evolucions = evolucions;
		this.localitzacions = localitzacio;
	}

	//es un atribut. els atributs son els que es posen a la propia etiqueta arrel
    @XmlAttribute
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    //es un element. Com que no especifiquem name, s'assumeix que es diu nom
    @XmlElement
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    //aquests tags es posen sempre abans del getter
    @XmlElement
    public String getTipus() {
        return tipus;
    }
    
    public void setTipus(String t) {
        this.tipus = t;
    }
    
    @XmlElement
	public double getPes() {
		return pes;
	}

	public void setPes(double pes) {
		this.pes = pes;
	}
	
	@XmlElement(name="evolucions")
	public Evolucio getEvolucions() {
		return evolucions;
	}

	public void setEvolucions(Evolucio evolucions) {
		this.evolucions = evolucions;
	}
	
	@XmlElementWrapper(name="localitzacions")
	@XmlElement(name="localitzacio")
	public ArrayList<String> getLocalitzacio() {
		return localitzacions;
	}

	public void setLocalitzacio(ArrayList<String> localitzacio) {
		this.localitzacions = localitzacio;
	}

	@Override
	public String toString() {
		return "Entrada [id=" + id + ", nom=" + nom + ", tipus=" + tipus + ", pes=" + pes + ", evolucions=" + evolucions
				+ ", localitzacions=" + localitzacions + "]";
	}
}
