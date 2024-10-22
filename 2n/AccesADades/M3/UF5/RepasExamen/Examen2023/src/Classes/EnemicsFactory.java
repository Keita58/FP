package Classes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.zip.ZipEntry;

public class EnemicsFactory {
	
	private PropertyChangeSupport support;
	
	public EnemicsFactory() {
		support = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

	public Enemics crearEnemic(TipusEnemics tipus, String nom, PowerRanger nemesis) {
        if (tipus.equals(TipusEnemics.RitaRepulsa)) {
            RitaRepulsa ritarepulsa = new RitaRepulsa(nom, nemesis);
            System.out.println(ritarepulsa);
            support.addPropertyChangeListener(nemesis);
            support.firePropertyChange("vigilo", ritarepulsa, nemesis);
            support.removePropertyChangeListener(nemesis);
            return ritarepulsa;
        } 
        else if (tipus.equals(TipusEnemics.LordZedd)) {
            LordZedd lordzedd = new LordZedd(nom, nemesis);
            System.out.println(lordzedd);
            support.addPropertyChangeListener(nemesis);
            support.firePropertyChange("vigilo", lordzedd, nemesis);
            support.removePropertyChangeListener(nemesis);
            return lordzedd;
        } 
        return null;
    }
}
