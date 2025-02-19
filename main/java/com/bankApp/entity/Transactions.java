package com.bankApp.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	private Double amount;
	private String transactionType;
	
	private Date transactionDate;
	
	@PrePersist
	public void setDate() {
		this.transactionDate = new Date();
	}

	@ManyToOne
	@JoinColumn(name = "from_account_id", referencedColumnName = "accountId")
	private Account fromAccount;
	
	@ManyToOne
	@JoinColumn(name = "to_account_id", referencedColumnName = "accountId")
	private Account toAccount;

}
