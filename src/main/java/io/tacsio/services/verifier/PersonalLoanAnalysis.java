package io.tacsio.services.verifier;

import javax.enterprise.context.ApplicationScoped;

import io.tacsio.model.LoanModel;
import io.tacsio.services.LoanProposal;
import io.tacsio.services.LoanVerifier;

@ApplicationScoped
public class PersonalLoanAnalysis implements LoanVerifier {

	@Override
	public LoanModel apply(LoanProposal proposal) {
		return LoanModel.PERSONAL;
	}

}
