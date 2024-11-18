package com.riki.client.form;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.riki.client.form.annotation.OnCreate;
import com.riki.client.form.annotation.OnUpdate;
import com.riki.client.form.annotation.PatternPhoneNumber;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmployeeForm {
	
	private Long id;
	
	@NotBlank(message = "name must not blank", groups = {OnCreate.class, OnUpdate.class})
	@Size(max = 31, message = "name size too long, max 30 letters", groups = {OnCreate.class, OnUpdate.class})
	private String fullname;
	
	@NotNull(message = "birth date must not blank", groups = {OnCreate.class, OnUpdate.class})
	@PastOrPresent(message = "birth date must not exceed current date", groups = {OnCreate.class, OnUpdate.class})
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthDate;
	
	@NotBlank(message = "email must not blank", groups = {OnCreate.class, OnUpdate.class})
	@Email(message = "email must be a valid email address", groups = {OnCreate.class, OnUpdate.class})
	private String email;
	
	@PatternPhoneNumber(groups = {OnCreate.class, OnUpdate.class})
	private String phoneNumber;

	private String urlPicture;

}
