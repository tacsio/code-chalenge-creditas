package io.tacsio.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CPF;

public class CustomerRequest {
	@NotBlank
	public String name;
	@CPF
	public String cpf;
	@Positive
	public Integer age;
	@NotBlank
	public String location;
	@PositiveOrZero
	public BigDecimal income;

	@Override
	public String toString() {
		return "CustomerRequest [age=" + age + ", cpf=" + cpf + ", income=" + income + ", location=" + location + ", name="
				+ name + "]";
	}
}
