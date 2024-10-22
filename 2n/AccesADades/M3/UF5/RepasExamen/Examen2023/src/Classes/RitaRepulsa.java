package Classes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RitaRepulsa implements Enemics {
	
	String nom;
	PowerRanger nemesis;
	private PropertyChangeSupport support;

	public RitaRepulsa(String nom, PowerRanger nemesis) {
		super();
		this.nom = nom;
		this.nemesis = nemesis;
		support = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public PowerRanger getNemesis() {
		return this.nemesis;
	}

	@Override
	public void atacar() {
		support.firePropertyChange("ataco", null, this);
	}

	@Override
	public String toString() {
		return "RitaRepulsa [nom=" + nom + ", nemesis=" + nemesis + "]";
	}	
}
