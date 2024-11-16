package com.riki.company.service;

import com.riki.company.form.CustomerForm;
import com.riki.company.form.LoginForm;
import com.riki.company.model.Customer;
import com.riki.company.model.User;

import java.util.List;

public interface HomeService {

	public void login(LoginForm loginForm);

	public String loginGoogle();

	public void logout();

	/*
	List<Customer> findCustomerAll();
	
	public CustomerForm findCustomer(Long id);

	public void updateCustomer(Long id, CustomerForm customerForm);
	
	public void deleteCustomer(Long id);
	 */

}
