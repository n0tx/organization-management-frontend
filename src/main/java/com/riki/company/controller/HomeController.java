package com.riki.company.controller;

import com.riki.company.form.CustomerForm;
import com.riki.company.form.LoginForm;
import com.riki.company.model.Customer;
import com.riki.company.service.CustomerService;
import com.riki.company.service.HomeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private HomeService homeService;

	@GetMapping("/hello")
	public String home(Model model) {
		model.addAttribute("home_message", "Hello World from SpringMVC + Thymleaf");
		return "home";
	}

	@GetMapping("/")
	public String login(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		return "authentication/login";
	}

	@PostMapping("/")
	public String login(@ModelAttribute("loginForm") @Valid LoginForm loginForm,
						BindingResult bindingResult,
						Errors errors,
						Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("loginForm", loginForm);
			return "authentication/login";
		}
		homeService.login(loginForm);
		return "redirect:/customer/list";
	}

	@GetMapping("/login/google")
	public String loginGoogleAuth() {
		String backendService = homeService.loginGoogle();
		return "redirect:" + backendService;
	}


	/*
	@GetMapping("/customer/list") // ini dari "/" ke "/customer/list"
	public String findCustomerList(Model model) {
		List<Customer> customers = customerService.findCustomerAll();
		model.addAttribute("customers", customers);
		return "customer/customer-list";
	}

	@GetMapping("/customer/new")
	public String loadCustomerForm(Model model) {
		CustomerForm customerForm = new CustomerForm();
		model.addAttribute("customerForm", customerForm);
		return "customer/customer-create";
	}
	 */

	/*
	@GetMapping("/customer/update/{id}")
	public String loadUpdateCustomerForm(@PathVariable("id") Long id, Model model) {
		CustomerForm customerForm = customerService.findCustomer(id);
		model.addAttribute("customerForm", customerForm);
		return "customer/customer-update";
	}

	@PostMapping("/customer/update/{id}")
	public String updateCustomer(@PathVariable("id") Long id,
								 @ModelAttribute("customerForm") @Valid CustomerForm customerForm,
								 BindingResult bindingResult,
								 Errors errors,
								 Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("customerForm", customerForm);
			return "customer/customer-update";
		}
		customerService.updateCustomer(id, customerForm);
		return "redirect:/";
	}

	@GetMapping("/customer/delete/{id}")
	public String deleteCustomer(@PathVariable("id") Long id, Model model) {
		customerService.deleteCustomer(id);
		return "redirect:/";
	}
	 */

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		homeService.logout();
		// Invalidate session di client
		request.getSession().invalidate();

		// Redirect ke halaman login setelah logout
		return "redirect:/?logout";
	}
}
