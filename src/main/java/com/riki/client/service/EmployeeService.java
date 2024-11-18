package com.riki.client.service;

import com.riki.client.form.EmployeeForm;
import com.riki.client.model.Employee;
import com.riki.client.model.pagination.Paging;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {

	public Paging<Employee> findEmployeeAll(Integer page, Integer limit, String sortBy, String direction, String search);
	
	public void createNewEmployee(EmployeeForm employeeForm);
	
	public EmployeeForm findEmployee(Long id);

	public void updateEmployee(Long id, EmployeeForm employeeForm);

	public void deleteEmployee(Long id);
	
	public String savePhoto(MultipartFile file);

	public Resource load(String filename);

}
