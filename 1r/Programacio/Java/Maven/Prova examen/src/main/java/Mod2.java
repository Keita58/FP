import java.util.ArrayList;

public class Mod2 {
	
	private String ModName;
	private ArrayList<ThingDef2> Defs = new ArrayList<>();
	
	public Mod2() {
		super();
	}
	
	public Mod2(String ModName, ArrayList<ThingDef2> def) {
		super();
		this.ModName = ModName;
		this.Defs = def;
	}
	
	public String getModName() {
		return ModName;
	}

	public void setModName(String ModName) {
		this.ModName = ModName;
	}
	
	public ArrayList<ThingDef2> getDef() {
		return Defs;
	}

	public void setDef(ArrayList<ThingDef2> def) {
		this.Defs = def;
	}

	@Override
	public String toString() {
		return "Mod [modName=" + ModName + ", def=" + Defs + "]";
	}
}
