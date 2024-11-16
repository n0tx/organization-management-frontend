package com.riki.company.form;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginForm {
	
	private Long id;

	@NotBlank(message = "email must not blank")
	@Email(message = "email must be a valid email address")
	private String email;

	@NotBlank(message = "password must not blank")
	private String password;

}
