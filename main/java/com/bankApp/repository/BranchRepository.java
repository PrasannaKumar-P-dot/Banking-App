package com.bankApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankApp.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{
	
	@Query(value = "SELECT b.branch_id, b.branch_name, b.branch_address, SUM(a.balance) AS total_balance " +
            "FROM branch b " +
            "JOIN account a ON b.branch_id = a.branch_id " +
            "GROUP BY b.branch_id, b.branch_name, b.branch_address", nativeQuery = true)
	List<Object[]> getTotalBalancePerBranch(); 

}
