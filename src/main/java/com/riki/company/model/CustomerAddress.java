package com.riki.company.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerAddress implements Serializable {

	private static final long serialVersionUID = 892057876184682310L;

	private Long id;
	
	private String address;
	
	private String province;
	
	private Long customerId;
	
	private String customerName;

}
