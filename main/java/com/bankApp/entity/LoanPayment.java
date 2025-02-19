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
public class LoanPayment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;
	
	private Date paymentDate;
	private Double amountPaid;
	private Double remainingAmountToPay;
	
	public Double setRemainingAmount(Double loanAmount, Double amountPaid) {
		return loanAmount - amountPaid;
	}
	
	@PrePersist
	private void setDate() {
		this.paymentDate = new Date();
	}
	
	@ManyToOne
	@JoinColumn(name = "loan_id", referencedColumnName = "loanId")
	private Loan loan;

//	@OneToMany(mappedBy = "loanPayment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Transactions transactions;
}
