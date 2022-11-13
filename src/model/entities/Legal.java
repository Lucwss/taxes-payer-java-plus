package model.entities;

public class Legal extends Payer {
	private Integer numberOfEmployees;
	
	public Legal() { super(); }
	
	public Legal(String name, Double anualIncome, Integer numberOfEmployees) {
		super(name, anualIncome);
		this.numberOfEmployees = numberOfEmployees;
	}

	public Integer getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(Integer numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}
	
	// ************************************ other methods ************************************
	
	@Override
	public Double tax() {
		return numberOfEmployees > 10 ?
			this.getAnualIncome() * 0.14
			:
			this.getAnualIncome() * 0.16;
	}
	
}
