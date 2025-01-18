package Projecte;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Observer que utilitsem a les naus per poder sumar els objectes.
 */
public class RecolectorPunts {

	private ObjecteCapturat objC;
	private PropertyChangeSupport support;
	
	public RecolectorPunts() {
		support = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {support.addPropertyChangeListener(pcl);}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

	/**
	 * Crida a la funció subscrita que realitzarà el càlcul de punts que sumarem a la nau
	 * @param obj ObjecteCapturat on hi tenim la nau que ha capturat l'objecte i l'objecte mateix
	 */
	public void setPunts(ObjecteCapturat obj) {
		System.out.println("Objecte que arriba a l'observer: " + obj.getObjecte());
		support.firePropertyChange("punts", obj, this.objC);
		this.objC = obj;
	}
}
