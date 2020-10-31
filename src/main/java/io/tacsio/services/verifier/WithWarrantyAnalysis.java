package io.tacsio.services.verifier;

import javax.enterprise.context.ApplicationScoped;

import io.tacsio.model.LoanModel;
import io.tacsio.services.LoanProposal;
import io.tacsio.services.LoanVerifier;

@ApplicationScoped
public class WithWarrantyAnalysis implements LoanVerifier {

  @Override
  public LoanModel apply(LoanProposal proposal) {
    // Salario <= 3000 + Clientes com menos de 30 anos que residem em SP
    // Salario > 3000 e < 5000 + Clientes que residem em SP
    // Salário >= 5000
    if ((this.incomeLTE3000(proposal.income) && ageLT30(proposal.age) && livesInSP(proposal.location))
        || (this.incomeBTW3000And5000(proposal.income) && livesInSP(proposal.location))
        || (this.incomeGTE5000(proposal.income) && ageLT30(proposal.age))) {
      return LoanModel.WITH_WARRANTY;
    }
    return null;
  }
}
