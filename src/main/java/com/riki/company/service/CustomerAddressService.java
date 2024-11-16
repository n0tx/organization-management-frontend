package com.riki.company.service;

import java.util.List;

import com.riki.company.form.CustomerAddressForm;
import com.riki.company.model.Customer;
import com.riki.company.model.CustomerAddress;

public interface CustomerAddressService {

	List<CustomerAddress> findCustAddressAll();
	
	List<String> loadCustAddressFormParam();
	
	public void createNewCustAddress(CustomerAddressForm custAddressForm);
	
	public CustomerAddressForm findCustAddress(Long id);
	
	public Customer findCustomer(Long id);

	public void updateCustAddress(Long id, CustomerAddressForm custAddressForm);
	
	public void deleteCustAddress(Long id);

}
