package com.bankApp.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bankApp.entity.Customer;
import com.bankApp.entity.Employee;
import com.bankApp.repository.CustomerRepository;
import com.bankApp.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
    private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Customer customer = customerRepository.findByCustomerName(username);
		
		if(customer != null) {
			customer.setRoles(Arrays.asList("ROLE_USER"));
			return customer;
		}
		
		Employee employee = employeeRepository.findByEmployeeName(username);
		
		if(employee != null) {
			
			if("Tarak P".equals(username)) {
				employee.setRoles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
			}else {
				employee.setRoles(Arrays.asList("ROLE_EMPLOYEE"));
			}
			return employee;
		}
		
		throw new UsernameNotFoundException("User not found");
	}

}
