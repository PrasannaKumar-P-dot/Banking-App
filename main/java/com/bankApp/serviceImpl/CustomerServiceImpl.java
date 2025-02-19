package com.bankApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankApp.entity.Account;
import com.bankApp.entity.Cards;
import com.bankApp.entity.Customer;
import com.bankApp.entityDto.CustomerDto;
import com.bankApp.entityResponse.CustomerResponse;
import com.bankApp.exception.ResourceNotFoundException;
import com.bankApp.repository.CustomerRepository;
import com.bankApp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ModelMapper mapper;

	Account account;
	List<Cards> cards;
	
	@Override
	public CustomerResponse addCustomer(CustomerDto employeeDto) {
		Customer customer = mapper.map(employeeDto, Customer.class);
		customer.setAccount(account);
		customer.setCards(cards);
		customerRepository.save(customer);
		CustomerResponse customerResponse = mapper.map(customer, CustomerResponse.class);
		return customerResponse;
	}

	@Override
	public List<CustomerResponse> getAllCustomer() {
		List<Customer> customers = customerRepository.findAll();
		List<CustomerResponse> customerResponses = customers.stream().map(acc -> mapper.map(acc, CustomerResponse.class)).collect(Collectors.toList());
		return customerResponses;
	}

	@Override
	public CustomerResponse getCustomerById(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with Id :"+id));
		CustomerResponse customerResponse = mapper.map(customer, CustomerResponse.class);
		return customerResponse;
	}

	@Override
	public CustomerResponse updateCustomer(Long id, CustomerDto dto) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with Id :"+id));
		mapper.map(dto, customer);
		customerRepository.save(customer);
		CustomerResponse customerResponse = mapper.map(customer, CustomerResponse.class);		
		return customerResponse;
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

}
