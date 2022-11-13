package model.entities;

public class Natural extends Payer {
	private Double healthExpenditures;
	
	public Natural() { super(); }
	
	public Natural(String name, Double anualIncome, Double healthExpenditures) {
		super(name, anualIncome);
		this.healthExpenditures = healthExpenditures;
	}

	public Double getHealthExpenditures() {
		return healthExpenditures;
	}

	public void setHealthExpenditures(Double healthExpenditures) {
		this.healthExpenditures = healthExpenditures;
	}
	
	// ************************************ other methods ************************************	
	
	@Override
	public Double tax() {
		return this.getAnualIncome() < 20000.00 ? 
			healthExpenditures > 0 ? ((this.getAnualIncome() * 0.15) - (healthExpenditures * 0.50)) : (this.getAnualIncome() * 0.15)
			: 
			healthExpenditures > 0 ? ((this.getAnualIncome() * 0.25) - (healthExpenditures * 0.50)) : (this.getAnualIncome() * 0.25);
	}
}
