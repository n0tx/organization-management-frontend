package com.riki.client.controller;

import com.riki.client.form.EmployeeForm;
import com.riki.client.form.annotation.OnCreate;
import com.riki.client.form.annotation.OnUpdate;
import com.riki.client.model.Employee;
import com.riki.client.model.pagination.Paging;
import com.riki.client.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@Validated
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee/list")
	public String findEmployeeList(
			@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
			@RequestParam(value = "limit", required = true, defaultValue = "2") Integer limit,
			@RequestParam(value = "sortBy", required = true, defaultValue = "id") String sortBy,
			@RequestParam(value = "direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(value = "search", required = false) String search,
			Model model) {
		Paging<Employee> employees = employeeService.findEmployeeAll(page, limit, sortBy, direction, search);
		model.addAttribute("employees", employees);
		return "employee/employee-list";
	}
	
	@GetMapping("/employee/new")
	public String loadEmployeeForm(Model model) {
		model.addAttribute("employeeForm", new EmployeeForm());
		return "employee/employee-create";
	}
	
	@PostMapping("/employee/new")
	public String addNewEmployee(@ModelAttribute("employeeForm") @Validated(OnCreate.class) EmployeeForm employeeForm,
			BindingResult bindingResult,
			Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("employeeForm", employeeForm);
			return "employee/employee-create";
		}
		employeeService.createNewEmployee(employeeForm);
		return "redirect:/employee/list";
	}
	
	@GetMapping("/employee/update/{id}")
	public String loadUpdateEmployeeForm(@PathVariable("id") Long id, Model model) {
		EmployeeForm employeeForm = employeeService.findEmployee(id);
		model.addAttribute("employeeForm", employeeForm);
		return "employee/employee-update";
	}
	
	@PostMapping("/employee/update/{id}")
	public String updateEmployee(@PathVariable("id") Long id,
			@ModelAttribute("employeeForm") @Validated(OnUpdate.class) EmployeeForm employeeForm,
			BindingResult bindingResult,
			Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("employeeForm", employeeForm);
			return "employee/employee-update";
		}
		employeeService.updateEmployee(id, employeeForm);
		return "redirect:/employee/list";
	}
	
	@GetMapping("/employee/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Long id, Model model) {
		employeeService.deleteEmployee(id);
		return "redirect:/employee/list";
	}

	@PostMapping("/upload")
	public String uploadPhoto(@RequestParam("file") MultipartFile file, Model model, @ModelAttribute("employeeForm") EmployeeForm employeeForm) {

		String urlPicture = employeeService.savePhoto(file);

		employeeForm.setUrlPicture(urlPicture);

		model.addAttribute("employeeForm", employeeForm);


		model.addAttribute("message", "File uploaded successfully!");

		if (employeeForm.getId() != null) {
			return "employee/employee-update";
		}

		return "employee/employee-create";

	}

	@GetMapping("/uploads/{filename:.+}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename) {
		Resource file = employeeService.load(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

}

