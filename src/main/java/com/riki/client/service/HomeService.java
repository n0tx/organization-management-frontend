package com.riki.client.service;

import com.riki.client.form.LoginForm;

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
