package com.riki.company.service.impl;

import com.riki.company.form.CustomerForm;
import com.riki.company.form.LoginForm;
import com.riki.company.model.Customer;
import com.riki.company.model.CustomerAddress;
import com.riki.company.model.User;
import com.riki.company.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {

	@Value("${api.url}")
	private String apiUrl;
	
	@Autowired
	private RestTemplate restTemplate;

	/*
	@Override
	public List<Customer> findCustomerAll() {
		return Arrays.stream(restTemplate.getForObject(apiUrl + "/customer/list", Customer[].class)).collect(Collectors.toList());
	}
	 */

	@Override
	public void login(LoginForm loginForm) {
		User user = new User();
		user.setEmail(loginForm.getEmail());
		user.setPassword(loginForm.getPassword());
		restTemplate.postForObject(apiUrl + "/user/login/local", user, User.class);
	}

	@Override
	public String loginGoogle() {
		// http://localhost:8080/login/oauth2/code/google
		 // return  apiUrl + "/login/oauth2/code/google";
		 return  apiUrl + "/user/login/google";
//		return "Redirecting to Google OAuth2 login...";
	}

	@Override
	public void logout() {
		// Panggil API logout backend
		String logoutUrl = apiUrl + "/user/logout";
		restTemplate.postForObject(logoutUrl, null, Void.class);
	}


	/*
	@GetMapping("/login/local")
	public String loginLocal(Model model) {
		List<CustomerAddress> custAddresses = custAddressService.findCustAddressAll();
		model.addAttribute("custAddresses", custAddresses);
		return "customer_address/custaddress-list";
	}
	 */

	/*
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
	 */
	
}
