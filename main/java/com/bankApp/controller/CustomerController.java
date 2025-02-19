package com.bankApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.entityDto.CustomerDto;
import com.bankApp.entityResponse.CustomerResponse;
import com.bankApp.service.CustomerService;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	public void setService(CustomerService service) {
		this.service = service;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
	public CustomerResponse addCustomer(@RequestBody CustomerDto dto) {
		return service.addCustomer(dto);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping
	public List<CustomerResponse> getAllCustomer() {
		return service.getAllCustomer();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public CustomerResponse getCustomerById(@PathVariable("id") Long id) {
		return service.getCustomerById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}")
	public CustomerResponse updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto dto) {
		return service.updateCustomer(id,dto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		service.deleteCustomer(id);
		return ResponseEntity.ok("Customer deleted Successfully");
	}
}
