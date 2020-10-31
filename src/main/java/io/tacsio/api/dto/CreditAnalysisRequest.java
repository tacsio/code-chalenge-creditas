package io.tacsio.api.dto;

import javax.validation.Valid;

import io.tacsio.services.LoanProposal;

public class CreditAnalysisRequest {
	@Valid
	public CustomerRequest customer;

	@Override
	public String toString() {
		return "CreditAnalysisRequest [customer=" + customer + "]";
	}

	public LoanProposal proposal() {
		return new LoanProposal(customer.name, customer.income, customer.age, customer.location);
	}

}
