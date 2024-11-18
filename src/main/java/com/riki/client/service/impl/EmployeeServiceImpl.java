package com.riki.client.service.impl;

import com.riki.client.form.EmployeeForm;
import com.riki.client.model.Employee;
import com.riki.client.model.pagination.Paging;
import com.riki.client.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Value("${api.url}")
	private String apiUrl;

	@Autowired
	private RestTemplate restTemplate;


	@Override
	public Paging<Employee> findEmployeeAll(Integer page, Integer limit, String sortBy, String direction, String search) {
		String url = String.format(
				apiUrl + "/employee/list?page=%d&limit=%d&sortBy=%s&direction=%s&search=%s",
				page, limit, sortBy, direction, search == null ? "" : search
		);

		return restTemplate.exchange(
				url,
				HttpMethod.GET,
				null, // No additional headers or body
				new ParameterizedTypeReference<Paging<Employee>>() {}
		).getBody();
	}

	@Override
	public void createNewEmployee(EmployeeForm employeeForm) {
		Employee employee = new Employee();
		employee.setFullname(employeeForm.getFullname());
		employee.setEmail(employeeForm.getEmail());
		employee.setBirthDate(employeeForm.getBirthDate());
		employee.setPhoneNumber(employeeForm.getPhoneNumber());
		employee.setUrlPicture(employeeForm.getUrlPicture());
		restTemplate.postForObject(apiUrl + "/employee/new", employee, Employee.class);
	}

	@Override
	public EmployeeForm findEmployee(Long id) {
		EmployeeForm employeeForm = restTemplate.getForObject(apiUrl + "/employee/{id}", EmployeeForm.class, id);
		return employeeForm;
	}

	@Override
	public void updateEmployee(Long id, EmployeeForm employeeForm) {
		restTemplate.exchange(apiUrl + "/employee/update/{id}", HttpMethod.PUT, new HttpEntity<>(employeeForm), EmployeeForm.class, id);
	}

	@Override
	public void deleteEmployee(Long id) {
		restTemplate.delete(apiUrl + "/employee/delete/{id}", id);
	}

//	@Override
//	public String savePhoto(MultipartFile file) {
//
//		// 1. Header
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//		// 2. Body multipart/form-data
//		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//		body.add("file", convertFileToResource(file));
//		// body.add("employeeForm", employeeForm);
//
//		// 3. HttpEntity
//		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
//
//		// 4. Backend
//		ResponseEntity<EmployeeForm> response = restTemplate.exchange(
//				apiUrl + "/employee/upload",
//				HttpMethod.POST,
//				requestEntity,
//				EmployeeForm.class
//		);
//
//		return "";
//	}

//	public String savePhoto(MultipartFile file) {
//		try {
//
//			// Headers untuk multipart request
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//			// File sebagai bagian dari request
//			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//			body.add("file", new MultipartInputStreamFileResource (file.getInputStream(), file.getOriginalFilename()));
//
//			// HttpEntity untuk request
//			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
//
//			// Menggunakan RestTemplate untuk mengirim POST request
//			String response = restTemplate.postForObject(apiUrl + "/employee/upload", requestEntity, String.class);
//
//			return response; // Mengembalikan URL gambar yang dihasilkan backend
//		} catch (IOException e) {
//			throw new RuntimeException("Failed to process file: " + e.getMessage(), e);
//		}
//	}

	private Resource convertFileToResource(MultipartFile file) {
		try {
			return new ByteArrayResource(file.getBytes()) {
				@Override
				public String getFilename() {
					return file.getOriginalFilename();
				}
			};
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert file to resource", e);
		}
	}


	@Override
	public Resource load(String filename) {

		ResponseEntity<Resource> response = restTemplate.exchange(
				apiUrl + "/uploads/{filename:.+}",
				HttpMethod.GET,
				HttpEntity.EMPTY,
				Resource.class
		);

		return response.getBody();
	}

	public class MultipartInputStreamFileResource extends InputStreamResource {

		private final String filename;

		public MultipartInputStreamFileResource(InputStream inputStream, String filename) {
			super(inputStream);
			this.filename = filename;
		}

		@Override
		public String getFilename() {
			return this.filename;
		}

		@Override
		public long contentLength() {
			try {
				return getInputStream().available();
			} catch (IOException e) {
				return -1;
			}
		}
	}

	public String savePhoto(MultipartFile file) {
		try {
			// Headers untuk multipart request
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			// Konversi file ke ByteArrayResource
			ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
				@Override
				public String getFilename() {
					return file.getOriginalFilename(); // Pastikan nama file tetap tersedia
				}
			};

			// Multipart body
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("file", fileAsResource);

			// HttpEntity untuk request
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

			// Menggunakan RestTemplate untuk mengirim POST request
			String response = restTemplate.postForObject(apiUrl + "/employee/upload", requestEntity, String.class);

			return response; // Mengembalikan URL gambar yang dihasilkan backend
		} catch (IOException e) {
			throw new RuntimeException("Failed to process file: " + e.getMessage(), e);
		}
	}
}
