package com.riki.client.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Employee implements Serializable {

	private static final long serialVersionUID = -2717624279840430130L;
	
	private Long id;
	private String fullname;
	private String email;
	private LocalDate birthDate;
	private String phoneNumber;
	private Boolean active;
	private String urlPicture;
}
