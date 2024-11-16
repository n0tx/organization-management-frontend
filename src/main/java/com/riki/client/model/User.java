package com.riki.client.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {

	private static final long serialVersionUID = 892057876184682310L;

	private Long id;
	
	private String name;
	private String email;
	private String password;
	private String position;
	private String urlPicture;
	private String role;

}
