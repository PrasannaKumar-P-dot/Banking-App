package com.bankApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bankApp.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(value = "SELECT c.email from customer c join account a on c.customer_id = a.customer_id where a.account_id=?1", nativeQuery = true)
	public String getEmailByAccountId(Long accountId);
	
	@Query(value = "SELECT c.customer_name from customer c join account a on c.customer_id = a.customer_id where a.account_id=?1", nativeQuery = true)
	public String getNameByAccountId(Long accountId);
	
    Customer findByCustomerName(String customerName);
}
