package io.tacsio.api;

import java.util.Set;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.tacsio.api.dto.CreditAnalysisRequest;
import io.tacsio.api.dto.LoanProposalResponse;
import io.tacsio.model.LoanModel;
import io.tacsio.services.CreditAnalysisService;

@Path("/loan")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanResource {

  CreditAnalysisService analysisService;

  public LoanResource(CreditAnalysisService analysisService) {
    this.analysisService = analysisService;
  }

  @POST
  public Response analizerDefault(@Valid CreditAnalysisRequest request) {
    Set<LoanModel> models = analysisService.analyzeCredit(request.proposal());

    String customer = request.customer.name;
    return Response.ok(new LoanProposalResponse(customer, models)).build();
  }

}
