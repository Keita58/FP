import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Mod")
@XmlType(propOrder = {"modName","def"})
public class Mod {
	
	private String modName;
	private ArrayList<ThingDef> def = new ArrayList<>();
	
	public Mod() {
		super();
	}
	
	public Mod(String modName, ArrayList<ThingDef> def) {
		super();
		this.modName = modName;
		this.def = def;
	}
	
	@XmlElement(name = "ModName")
	public String getModName() {
		return modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}
	
	@XmlElementWrapper(name = "Defs")
	@XmlElement(name="ThingDef")
	public ArrayList<ThingDef> getDef() {
		return def;
	}

	public void setDef(ArrayList<ThingDef> def) {
		this.def = def;
	}

	@Override
	public String toString() {
		return "Mod [modName=" + modName + ", def=" + def + "]";
	}
}
