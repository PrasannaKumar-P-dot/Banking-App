package com.bankApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankApp.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{

}
