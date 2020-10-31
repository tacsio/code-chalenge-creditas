package io.tacsio.services;

import java.math.BigDecimal;

import io.tacsio.model.LoanModel;

public interface LoanVerifier {

	LoanModel apply(LoanProposal proposal);

	default boolean ageLT30(int age) {
		return age < 30;
	}

	default boolean livesInSP(String location) {
		return location.equalsIgnoreCase("sp");
	}

	default boolean incomeLTE3000(BigDecimal income) {
		return income.compareTo(BigDecimal.valueOf(3000)) <= 0;
	}

	default boolean incomeBTW3000And5000(BigDecimal income) {
		boolean GT3000 = income.compareTo(BigDecimal.valueOf(3000)) > 0;
    boolean LTE5000 = income.compareTo(BigDecimal.valueOf(5000)) < 0;
    
		return GT3000 && LTE5000;
	}

	default boolean incomeGTE5000(BigDecimal income) {
		return income.compareTo(BigDecimal.valueOf(5000)) >= 0;
	}
}
