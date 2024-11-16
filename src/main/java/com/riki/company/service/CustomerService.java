package com.riki.company.service;

import java.util.List;

import com.riki.company.form.CustomerForm;
import com.riki.company.model.Customer;

public interface CustomerService {

	List<Customer> findCustomerAll();
	
	public void createNewCustomer(CustomerForm customerForm);
	
	public CustomerForm findCustomer(Long id);

	public void updateCustomer(Long id, CustomerForm customerForm);
	
	public void deleteCustomer(Long id);

}
