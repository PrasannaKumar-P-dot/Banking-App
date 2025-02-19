package com.bankApp.service;

import java.util.List;

import com.bankApp.entityDto.CustomerDto;
import com.bankApp.entityResponse.CustomerResponse;

public interface CustomerService {

	CustomerResponse addCustomer(CustomerDto dto);

	List<CustomerResponse> getAllCustomer();

	CustomerResponse getCustomerById(Long id);

	CustomerResponse updateCustomer(Long id, CustomerDto dto);

	void deleteCustomer(Long id);

}
