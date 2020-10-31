package io.tacsio.api.dto;

import java.util.Set;
import java.util.stream.Collectors;

import io.tacsio.model.LoanModel;

public class LoanProposalResponse {
	public final String customer;
	public final Set<LoanResponse> loans;

	public LoanProposalResponse(String customer, Set<LoanModel> models) {
		this.customer = customer;

		this.loans = models.stream()
      .map(LoanResponse::new)
      .collect(Collectors.toSet());
  }
}

class LoanResponse {
  public final String type;
  public final double taxes;

  public LoanResponse(LoanModel loanModel) {
    this.type = loanModel.name().toLowerCase();
    this.taxes = loanModel.getTax();
  }
}
