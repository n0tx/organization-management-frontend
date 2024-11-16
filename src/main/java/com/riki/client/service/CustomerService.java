package com.riki.client.service;

import java.util.List;

import com.riki.client.form.CustomerForm;
import com.riki.client.model.Customer;

public interface CustomerService {

	List<Customer> findCustomerAll();
	
	public void createNewCustomer(CustomerForm customerForm);
	
	public CustomerForm findCustomer(Long id);

	public void updateCustomer(Long id, CustomerForm customerForm);
	
	public void deleteCustomer(Long id);

}
