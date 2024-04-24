
public class StatBases2 {

	private double MaxHitPoints;
	private double MarketValue;
	private double Mass;
	private double Flammability;
	private double DeteriorationRate;
	
	public StatBases2() {
		super();
	}

	public StatBases2(double maxHitPoints, double marketValue, double mass, double flammability, double deteriorationRate) {
		super();
		this.MaxHitPoints = maxHitPoints;
		this.MarketValue = marketValue;
		this.Mass = mass;
		this.Flammability = flammability;
		this.DeteriorationRate = deteriorationRate;
	}

	public double getMaxHitPoints() {
		return MaxHitPoints;
	}
	
	public void setMaxHitPoints(double maxHitPoints) {
		this.MaxHitPoints = maxHitPoints;
	}
	
	public double getMarketValue() {
		return MarketValue;
	}
	
	public void setMarketValue(double marketValue) {
		this.MarketValue = marketValue;
	}
	
	public double getMass() {
		return Mass;
	}
	
	public void setMass(double mass) {
		this.Mass = mass;
	}
	
	public double getFlammability() {
		return Flammability;
	}
	
	public void setFlammability(double flammability) {
		this.Flammability = flammability;
	}
	
	public double getDeteriorationRate() {
		return DeteriorationRate;
	}
	
	public void setDeteriorationRate(double deteriorationRate) {
		this.DeteriorationRate = deteriorationRate;
	}

	@Override
	public String toString() {
		return "StatBases [maxHitPoints=" + MaxHitPoints + ", marketValue=" + MarketValue + ", mass=" + Mass
				+ ", flammability=" + Flammability + ", deteriorationRate=" + DeteriorationRate + "]";
	}
}
