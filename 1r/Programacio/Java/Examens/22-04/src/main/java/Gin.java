import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="gin")
@XmlType(propOrder = {"nom","graduacio", "sabor", "ingredients"})
public class Gin {

	private String nom;
	private int graduacio;
	private String sabor;
	private ArrayList<Ingredient> ingredients = new ArrayList<>();
	
	public Gin() {
		super();
	}

	public Gin(String nom, int graduacio, String sabor, ArrayList<Ingredient> ingredients) {
		super();
		this.nom = nom;
		this.graduacio = graduacio;
		this.sabor = sabor;
		this.ingredients = ingredients;
	}

	@XmlElement
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@XmlElement
	public int getGraduacio() {
		return graduacio;
	}
	
	public void setGraduacio(int graduacio) {
		this.graduacio = graduacio;
	}
	
	@XmlElement
	public String getSabor() {
		return sabor;
	}
	
	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	
	@XmlElementWrapper(name="ingredients")
	@XmlElement(name="ingredient")
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Gin [nom=" + nom + ", graduacio=" + graduacio + ", sabor=" + sabor + ", ingredients=" + ingredients
				+ "]";
	}
}
