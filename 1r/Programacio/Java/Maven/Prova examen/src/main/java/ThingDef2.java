import java.util.ArrayList;

public class ThingDef2 {
	
	private String ParentName;
	private String defName;
	private String description;
	private int stackLimit;
	private boolean healthAffectsPrice;
	private boolean intricate;
	private StatBases2 statBases;
	private ArrayList<String> thingCategories = new ArrayList<>();
	
	public ThingDef2() {
		super();
	}
	
	public ThingDef2(String defName, String description, int stackLimit, StatBases2 statBases,
			ArrayList<String> thingCategories, boolean intricate, boolean healthAffectsPrice, String parentName) {
		super();
		this.defName = defName;
		this.description = description;
		this.stackLimit = stackLimit;
		this.statBases = statBases;
		this.thingCategories = thingCategories;
		this.intricate = intricate;
		this.healthAffectsPrice = healthAffectsPrice;
		this.ParentName = parentName;
	}

	public String getParentName() {
		return ParentName;
	}

	public void setParentName(String parentName) {
		this.ParentName = parentName;
	}

	public String getDefName() {
		return defName;
	}
	
	public void setDefName(String defName) {
		this.defName = defName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getStackLimit() {
		return stackLimit;
	}
	
	public void setStackLimit(int stackLimit) {
		this.stackLimit = stackLimit;
	}
	
	public StatBases2 getStatBases() {
		return statBases;
	}
	
	public void setStatBases(StatBases2 statBases) {
		this.statBases = statBases;
	}
	
	public ArrayList<String> getThingCategories() {
		return thingCategories;
	}
	
	public void setThingCategories(ArrayList<String> thingCategories) {
		this.thingCategories = thingCategories;
	}
	
	public boolean isIntricate() {
		return intricate;
	}

	public void setIntricate(boolean intricate) {
		this.intricate = intricate;
	}
	
	public boolean isHealthAffectsPrice() {
		return healthAffectsPrice;
	}

	public void setHealthAffectsPrice(boolean healthAffectsPrice) {
		this.healthAffectsPrice = healthAffectsPrice;
	}

	@Override
	public String toString() {
		return "ThingDef [defName=" + defName + ", description=" + description + ", stackLimit=" + stackLimit
				+ ", statBases=" + statBases + ", thingCategories=" + thingCategories + ", intricate=" + intricate
				+ ", healthAffectsPrice=" + healthAffectsPrice + ", parentName=" + ParentName + "]";
	}
}
