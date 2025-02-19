package com.bankApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankApp.entity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long>{

}
