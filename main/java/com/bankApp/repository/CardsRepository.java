package com.bankApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankApp.entity.Cards;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long>{

}
