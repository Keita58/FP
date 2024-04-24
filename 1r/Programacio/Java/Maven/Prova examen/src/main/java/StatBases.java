import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="statBases")
@XmlType(propOrder = {"maxHitPoints", "marketValue", "mass", "flammability", "deteriorationRate"})
public class StatBases {

	private int maxHitPoints;
	private double marketValue;
	private double mass;
	private double flammability;
	private double deteriorationRate;
	
	public StatBases() {
		super();
	}

	public StatBases(int maxHitPoints, double marketValue, double mass, double flammability, double deteriorationRate) {
		super();
		this.maxHitPoints = maxHitPoints;
		this.marketValue = marketValue;
		this.mass = mass;
		this.flammability = flammability;
		this.deteriorationRate = deteriorationRate;
	}

	@XmlElement(name="MaxHitPoints")
	public int getMaxHitPoints() {
		return maxHitPoints;
	}
	
	public void setMaxHitPoints(int maxHitPoints) {
		this.maxHitPoints = maxHitPoints;
	}
	
	@XmlElement(name="MarketValue")
	public double getMarketValue() {
		return marketValue;
	}
	
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	
	@XmlElement(name="Mass")
	public double getMass() {
		return mass;
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	@XmlElement(name="Flammability")
	public double getFlammability() {
		return flammability;
	}
	
	public void setFlammability(double flammability) {
		this.flammability = flammability;
	}
	
	@XmlElement(name="DeteriorationRate")
	public double getDeteriorationRate() {
		return deteriorationRate;
	}
	
	public void setDeteriorationRate(double deteriorationRate) {
		this.deteriorationRate = deteriorationRate;
	}

	@Override
	public String toString() {
		return "StatBases [maxHitPoints=" + maxHitPoints + ", marketValue=" + marketValue + ", mass=" + mass
				+ ", flammability=" + flammability + ", deteriorationRate=" + deteriorationRate + "]";
	}
}
