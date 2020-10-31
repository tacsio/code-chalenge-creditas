package io.tacsio.services;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import io.tacsio.model.LoanModel;

@ApplicationScoped
public class CreditAnalysisService {

	@Inject
	Instance<LoanVerifier> loanOptions;

	public Set<LoanModel> analyzeCredit(LoanProposal proposal) {

	return loanOptions.stream()
		.map(verifier -> verifier.apply(proposal))
		.filter(Objects::nonNull)
		.collect(Collectors.toSet());		
	}
}
