package com.riki.company.controller;

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

import com.riki.company.form.CustomerAddressForm;
import com.riki.company.model.CustomerAddress;
import com.riki.company.service.CustomerAddressService;
import com.riki.company.service.CustomerService;

@Controller
public class CustomerAddressController {
	
	@Autowired
	private CustomerAddressService custAddressService;
	
	@Autowired
	private CustomerService customerService;

	/*
	@GetMapping("/login/local")
	public String loginLocal(Model model) {
		List<CustomerAddress> custAddresses = custAddressService.findCustAddressAll();
		model.addAttribute("custAddresses", custAddresses);
		return "customer_address/custaddress-list";
	}
	 */

	@GetMapping("/custaddress/list")
	public String findCustAddressList(Model model) {
		List<CustomerAddress> custAddresses = custAddressService.findCustAddressAll();
		model.addAttribute("custAddresses", custAddresses); 
		return "customer_address/custaddress-list";
	}
	
	@GetMapping("/custaddress/new")
	public String loadCustAddressForm(Model model) {
		CustomerAddressForm custAddressForm = new CustomerAddressForm();
		model.addAttribute("custAddressForm", custAddressForm);
		model.addAttribute("provinces", custAddressService.loadCustAddressFormParam());
		model.addAttribute("customers", customerService.findCustomerAll());
		return "customer_address/custaddress-create";
	}

	@PostMapping("/custaddress/new")
	public String addCustomerAddress(@ModelAttribute("custAddressForm") @Valid CustomerAddressForm custAddressForm,
			BindingResult bindingResult,
			Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("custAddressForm", custAddressForm);
			model.addAttribute("provinces", custAddressService.loadCustAddressFormParam());
			model.addAttribute("customers", customerService.findCustomerAll());
			return "customer_address/custaddress-create";
		}
		custAddressService.createNewCustAddress(custAddressForm);
		return "redirect:/custaddress/list";
	}

	@GetMapping("/custaddress/update/{id}")
	public String loadUpdateCustAddressForm(@PathVariable("id") Long id, Model model) {
		CustomerAddressForm custAddressForm = custAddressService.findCustAddress(id);
		model.addAttribute("custAddressForm", custAddressForm);
		model.addAttribute("provinces", custAddressService.loadCustAddressFormParam());
		model.addAttribute("customers", customerService.findCustomerAll());
		return "customer_address/custaddress-update";
	}
	
	@PostMapping("/custaddress/update/{id}")
	public String updateCustAddress(@PathVariable("id") Long id, 
			@ModelAttribute("custAddressForm") @Valid CustomerAddressForm custAddressForm, 
			BindingResult bindingResult,
			Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("custAddressForm", custAddressForm);
			model.addAttribute("provinces", custAddressService.loadCustAddressFormParam());
			model.addAttribute("customers", customerService.findCustomerAll());
			return "customer_address/custaddress-update";
		}
		custAddressService.updateCustAddress(id, custAddressForm);
		return "redirect:/custaddress/list";
	}

	@GetMapping("/custaddress/delete/{id}")
	public String deleteCustAddress(@PathVariable("id") Long id, Model model) {
		custAddressService.deleteCustAddress(id);
		return "redirect:/custaddress/list";
	}
	
}
