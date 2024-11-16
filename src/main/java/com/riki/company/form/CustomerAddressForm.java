package com.riki.company.form;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerAddressForm {
	
	List<String> provinces;
	
	private Long id;
	
	@NotBlank(message = "address must not blank")
	private String address;
	
	@NotBlank(message = "province must not blank")
	private String province;
	
	@NotNull(message = "customer must not blank")
	private Long customerId;
	
	private String customerName;

}
