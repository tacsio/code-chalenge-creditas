package io.tacsio.model;

public enum LoanModel {
	PERSONAL(4), WITH_WARRANTY(3), CONSIGNED(2);

	private final double tax;

	private LoanModel(double tax) {
		this.tax = tax;
	}

	public double getTax() {
		return tax;
	}
}
