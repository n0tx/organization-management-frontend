package com.riki.company.model;

import java.util.List;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Customer {

	private Long id;
	
	private String customerName;
	
	private List<CustomerAddress> addresses;

}
