package com.bankApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankApp.entity.Branch;
import com.bankApp.entity.Employee;
import com.bankApp.entityDto.EmployeeDto;
import com.bankApp.entityResponse.EmployeeResponse;
import com.bankApp.exception.ResourceNotFoundException;
import com.bankApp.repository.BranchRepository;
import com.bankApp.repository.EmployeeRepository;
import com.bankApp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	BranchRepository branchRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public EmployeeResponse addEmployee(EmployeeDto employeeDto, Long bid) {
		Branch branch = branchRepository.findById(bid).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id:"+bid));
		
		Employee employee = new Employee();
		employee.setEmployeeName(employeeDto.getName());
		employee.setPosition(employeeDto.getPosition());
		employee.setEmail(employeeDto.getEmail());
		employee.setSalary(employeeDto.getSalary());
		employee.setBranch(branch);
		employeeRepository.save(employee);
		EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
		return employeeResponse;
	}

	@Override
	public List<EmployeeResponse> getAllEmployee() {
		List<Employee> employee = employeeRepository.findAll();
		List<EmployeeResponse> employeeResponse = employee.stream().map(emp -> mapper.map(emp, EmployeeResponse.class)).collect(Collectors.toList());
		return employeeResponse;
	}

	@Override
	public EmployeeResponse getEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id: "+ id));
		EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
		return employeeResponse;
	}

	@Override
	public EmployeeResponse updateEmployee(Long id, EmployeeDto dto, Long bid) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id: "+ id));
		Branch branch = branchRepository.findById(bid).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id:"+bid));
		employee.setEmployeeName(dto.getName());
		employee.setPosition(dto.getPosition());
		employee.setEmail(dto.getEmail());
		employee.setSalary(dto.getSalary());
		employee.setBranch(branch);
		employeeRepository.save(employee);
		EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
		return employeeResponse;
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

}
