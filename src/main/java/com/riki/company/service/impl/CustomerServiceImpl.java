package com.riki.company.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.riki.company.form.CustomerForm;
import com.riki.company.model.Customer;
import com.riki.company.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Value("${api.url}")
	private String apiUrl;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Customer> findCustomerAll() {
		return Arrays.stream(restTemplate.getForObject(apiUrl + "/customer/list", Customer[].class)).collect(Collectors.toList());
	}
	
	@Override
	public void createNewCustomer(CustomerForm customerForm) {
		Customer customer = new Customer();
		customer.setCustomerName(customerForm.getCustomerName());
		restTemplate.postForObject(apiUrl + "/customer/new", customer, Customer.class);
	}

	@Override
	public CustomerForm findCustomer(Long id) {
		CustomerForm customerForm = restTemplate.getForObject(apiUrl + "/customer/{id}", CustomerForm.class, id);
		customerForm.setId(id);
		return customerForm;
	}

	@Override
	public void updateCustomer(Long id, CustomerForm customerForm) {
		restTemplate.exchange(apiUrl + "/customer/update/{id}", HttpMethod.PUT, new HttpEntity<>(customerForm), CustomerForm.class, id);
	}

	@Override
	public void deleteCustomer(Long id) {
		restTemplate.delete(apiUrl + "/customer/delete/{id}", id);
	}
	
}
