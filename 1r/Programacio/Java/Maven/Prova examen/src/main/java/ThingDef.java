import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="ThingDef")
@XmlType(propOrder = {"defName", "description", "stackLimit", "healthAffectsPrice", "statBases", "intricate", "thingCategories"})
public class ThingDef {

	private String defName;
	private String description;
	private int stackLimit;
	private StatBases statBases;
	private ArrayList<String> thingCategories = new ArrayList<>();
	private Boolean intricate;
	private Boolean healthAffectsPrice;
	private String parentName;
	
	public ThingDef() {
		super();
	}
	
	public ThingDef(String defName, String description, int stackLimit, StatBases statBases,
			ArrayList<String> thingCategories, boolean intricate, boolean healthAffectsPrice, String parentName) {
		super();
		this.defName = defName;
		this.description = description;
		this.stackLimit = stackLimit;
		this.statBases = statBases;
		this.thingCategories = thingCategories;
		this.intricate = intricate;
		this.healthAffectsPrice = healthAffectsPrice;
		this.parentName = parentName;
	}

	@XmlAttribute(name="ParentName")
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@XmlElement
	public String getDefName() {
		return defName;
	}
	
	public void setDefName(String defName) {
		this.defName = defName;
	}
	
	@XmlElement
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement
	public int getStackLimit() {
		return stackLimit;
	}
	
	public void setStackLimit(int stackLimit) {
		this.stackLimit = stackLimit;
	}
	
	@XmlElement
	public StatBases getStatBases() {
		return statBases;
	}
	
	public void setStatBases(StatBases statBases) {
		this.statBases = statBases;
	}
	
	@XmlElementWrapper(name="thingCategories")
	@XmlElement(name="li")
	public ArrayList<String> getThingCategories() {
		return thingCategories;
	}
	
	public void setThingCategories(ArrayList<String> thingCategories) {
		this.thingCategories = thingCategories;
	}
	
	@XmlElement
	public Boolean isIntricate() {
		return intricate;
	}

	public void setIntricate(Boolean intricate) {
		this.intricate = intricate;
	}
	
	@XmlElement
	public Boolean isHealthAffectsPrice() {
		return healthAffectsPrice;
	}

	public void setHealthAffectsPrice(Boolean healthAffectsPrice) {
		this.healthAffectsPrice = healthAffectsPrice;
	}

	@Override
	public String toString() {
		return "ThingDef [defName=" + defName + ", description=" + description + ", stackLimit=" + stackLimit
				+ ", statBases=" + statBases + ", thingCategories=" + thingCategories + ", intricate=" + intricate
				+ ", healthAffectsPrice=" + healthAffectsPrice + ", parentName=" + parentName + "]";
	}
}
