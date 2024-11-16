package com.riki.client.service.impl;

import com.riki.client.form.LoginForm;
import com.riki.client.model.User;
import com.riki.client.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
