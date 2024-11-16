package com.riki.client.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.riki.client.form.CustomerAddressForm;
import com.riki.client.model.Customer;
import com.riki.client.model.CustomerAddress;
import com.riki.client.service.CustomerAddressService;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

	@Value("${api.url}")
	private String apiUrl;
	
	@Autowired
	private RestTemplate restTemplate;
		
	@Override
	public List<CustomerAddress> findCustAddressAll() {
		return Arrays.stream(restTemplate.getForObject(apiUrl + "/custaddress/list", CustomerAddress[].class)).collect(Collectors.toList());
	}
	
	@Override
	public void createNewCustAddress(CustomerAddressForm custAddressForm) {
		CustomerAddress custAddress = new CustomerAddress();
		custAddress.setAddress(custAddressForm.getAddress());
		custAddress.setProvince(custAddressForm.getProvince());
		custAddress.setCustomerId(custAddressForm.getCustomerId());
		restTemplate.postForObject(apiUrl + "/custaddress/new", custAddress, CustomerAddress.class);
	}

	@Override
	public CustomerAddressForm findCustAddress(Long id) {
		CustomerAddressForm custAddressForm = restTemplate.getForObject(apiUrl + "/custaddress/{id}", CustomerAddressForm.class, id);
		custAddressForm.setId(id);
		return custAddressForm;
	}

	@Override
	public void updateCustAddress(Long id, CustomerAddressForm custAddressForm) {
		restTemplate.exchange(apiUrl + "/custaddress/update/{id}", HttpMethod.PUT, new HttpEntity<>(custAddressForm), CustomerAddressForm.class, id);
	}

	@Override
	public void deleteCustAddress(Long id) {
		restTemplate.delete(apiUrl + "/custaddress/delete/{id}", id);
	}

	@Override
	public List<String> loadCustAddressFormParam() {
		return restTemplate.getForObject(apiUrl + "/custaddress/new", CustomerAddressForm.class).getProvinces(); 
	}

	@Override
	public Customer findCustomer(Long id) {
		Customer customer = restTemplate.getForObject(apiUrl + "/custaddress/{id}", Customer.class, id);
		customer.setId(id);
		return customer;
	}

}
