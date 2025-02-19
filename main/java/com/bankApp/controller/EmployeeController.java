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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.entityDto.EmployeeDto;
import com.bankApp.entityResponse.EmployeeResponse;
import com.bankApp.service.EmployeeService;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	public void setService(EmployeeService service) {
		this.service = service;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add/{bid}")
	public EmployeeResponse addEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable(value = "bid") Long bid) {
		return service.addEmployee(employeeDto, bid);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping
	public List<EmployeeResponse> getAllEmployee() {
		return service.getAllEmployee();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public EmployeeResponse getEmployeeById(@RequestParam("id") Long id) {
		return service.getEmployeeById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}/{bid}")
	public EmployeeResponse updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto dto, @PathVariable(value = "bid") Long bid) {
		return service.updateEmployee(id,dto,bid);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
		service.deleteEmployee(id);
		return ResponseEntity.ok("Employee deleted Successfully");
	}
}
