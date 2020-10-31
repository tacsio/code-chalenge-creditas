package io.tacsio.services;

import java.math.BigDecimal;

public class LoanProposal {

	public final String customer;
	public final BigDecimal income;
	public final int age;
	public final String location;

	public LoanProposal(String customer, BigDecimal income, int age, String location) {
		this.customer = customer;
		this.income = income;
		this.age = age;
		this.location = location;
	}
}
