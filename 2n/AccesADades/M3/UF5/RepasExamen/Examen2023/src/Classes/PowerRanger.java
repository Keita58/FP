package Classes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PowerRanger implements PropertyChangeListener, HeroiEquipat {

	String nom;
	String nomMecha;
	Color color;
	double poder;
	
	public PowerRanger(String nom, String nomMecha, Color color, double poder) {
		super();
		this.nom = nom;
		this.nomMecha = nomMecha;
		this.color = color;
		this.poder = poder;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomMecha() {
		return nomMecha;
	}

	public void setNomMecha(String nomMecha) {
		this.nomMecha = nomMecha;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getPoder() {
		return poder;
	}

	public void setPoder(double poder) {
		this.poder = poder;
	}

	@Override
	public String toString() {
		return "PowerRanger [nom=" + nom + ", nomMecha=" + nomMecha + ", color=" + color + ", poder=" + poder + "]";
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String nomEsdeveniment = evt.getPropertyName();
		if(nomEsdeveniment.equals("vigilo")) {
			Enemics enemic = (Enemics) evt.getOldValue();
			PowerRanger self = (PowerRanger) evt.getNewValue();
			if(self.getNom().equals(this.nom)) {
				if(enemic instanceof RitaRepulsa)
					((RitaRepulsa) enemic).addPropertyChangeListener(this);
				else if(enemic instanceof LordZedd)
					((LordZedd) enemic).addPropertyChangeListener(this);
				System.out.println("Soc en " + this.nom + " i vigilo a " + enemic.getNom());
			}
		}
		else if(nomEsdeveniment.equals("ataco")) {
			Enemics enemicAtaca = (Enemics) evt.getNewValue();
			System.out.println("Soc " + enemicAtaca.getNom() + " i ataco!!!");
			System.out.println(this.nom + ": Oh no!! Ens ataquen");
		}
	}

	@Override
	public String equipar() {
		return this.getNom();
	}
}
