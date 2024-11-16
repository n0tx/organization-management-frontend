package com.riki.client.controller;

import java.util.List;

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

import com.riki.client.form.CustomerForm;
import com.riki.client.model.Customer;
import com.riki.client.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

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
	
	@PostMapping("/customer/new")
	public String addCustomer(@ModelAttribute("customerForm") @Valid CustomerForm customerForm,
			BindingResult bindingResult,
			Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("customerForm", customerForm);
			return "customer/customer-create";
		}
		customerService.createNewCustomer(customerForm);
		return "redirect:/customer/list";
	}

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
		return "redirect:/customer/list";
	}

	@GetMapping("/customer/delete/{id}")
	public String deleteCustomer(@PathVariable("id") Long id, Model model) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
	
}
