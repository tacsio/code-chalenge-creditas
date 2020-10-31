package io.tacsio;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.tacsio.api.dto.CreditAnalysisRequest;
import io.tacsio.api.dto.CustomerRequest;

@QuarkusTest
public class LoanTest {

  @Test
  public void onlyPersonalLoan() {
    String customer = "User";

    List<CreditAnalysisRequest> requests = Arrays.asList(
      createRequest(customer, 29, "BH", 3000.0),
      createRequest(customer, 50, "BH", 4000.0)
    );

    requests.stream().forEach(request -> {
      given().contentType(ContentType.JSON).when()
        .body(request)
        .post("/loan")
      .then()
        .statusCode(200)
        .body(
          "customer", is(customer), 
          "loans.type", Matchers.hasSize(1), 
          "loans.type", Matchers.hasItems("personal")
        );
    });
  }

  @Test
  public void onlyPersonalAndWarrantyLoan() {
    String customer = "User";

    List<CreditAnalysisRequest> requests = Arrays.asList(
      createRequest(customer, 29, "SP", 2000.0),
      createRequest(customer, 50, "SP", 3000.1)
    );

    requests.stream().forEach(request -> {
      given().contentType(ContentType.JSON).when()
        .body(request)
        .post("/loan")
      .then()
        .statusCode(200)
        .body(
          "customer", is(customer), 
          "loans.type", Matchers.hasSize(2), 
          "loans.type", Matchers.hasItems("personal"),
          "loans.type", Matchers.hasItems("with_warranty")
        );
    });
  }

  @Test
  public void onlyPersonalAndConsigned() {
    String customer = "User";

    List<CreditAnalysisRequest> requests = Arrays.asList(
      createRequest(customer, 31, "SP", 5000.0),
      createRequest(customer, 50, "PE", 5000.0)
    );

    requests.stream().forEach(request -> {
      given().contentType(ContentType.JSON).when()
        .body(request)
        .post("/loan")
      .then()
        .statusCode(200)
        .body(
          "customer", is(customer), 
          "loans.type", Matchers.hasSize(2), 
          "loans.type", Matchers.hasItems("personal"),
          "loans.type", Matchers.hasItems("consigned")
        );
    });
  }

  @Test
  public void allLoanTypes() {
    String customer = "User";

    List<CreditAnalysisRequest> requests = Arrays.asList(
      createRequest(customer, 29, "SP", 5000.0),
      createRequest(customer, 29, "PE", 5000.0)
    );

    requests.stream().forEach(request -> {
      given().contentType(ContentType.JSON).when()
        .body(request)
        .post("/loan")
      .then()
        .statusCode(200)
        .body(
          "customer", is(customer), 
          "loans.type", Matchers.hasSize(3), 
          "loans.type", Matchers.hasItems("personal"),
          "loans.type", Matchers.hasItems("with_warranty"),
          "loans.type", Matchers.hasItems("consigned")
        );
    });
  }

  public CreditAnalysisRequest createRequest(String customer, int age, String location, double income) {
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.cpf = "12345678909";
    customerRequest.name = customer;
    customerRequest.age = age;
    customerRequest.income = BigDecimal.valueOf(income);
    customerRequest.location = location;

    CreditAnalysisRequest request = new CreditAnalysisRequest();
    request.customer = customerRequest;

    return request;
  }

}