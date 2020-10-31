package io.tacsio.services.verifier;

import javax.enterprise.context.ApplicationScoped;

import io.tacsio.model.LoanModel;
import io.tacsio.services.LoanProposal;
import io.tacsio.services.LoanVerifier;

@ApplicationScoped
public class ConsigedAnalysis implements LoanVerifier {

	@Override
	public LoanModel apply(LoanProposal proposal) {
		if (this.incomeGTE5000(proposal.income)) {
			return LoanModel.CONSIGNED;
		}
		return null;
	}

}
