package com.bankApp.service;

import java.util.List;

import com.bankApp.entityDto.EmployeeDto;
import com.bankApp.entityResponse.EmployeeResponse;

public interface EmployeeService {

	EmployeeResponse addEmployee(EmployeeDto employeeDto, Long bid);

	List<EmployeeResponse> getAllEmployee();

	EmployeeResponse getEmployeeById(Long id);

	EmployeeResponse updateEmployee(Long id, EmployeeDto dto, Long bid);

	void deleteEmployee(Long id);

	

}
