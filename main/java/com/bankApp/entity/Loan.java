package com.bankApp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;
	
	private String loanType;
	private Double loanAmount;
	private Integer noMonthsPay;
	private Double remainingAmountToPay;
	
	private Double interestRate;
	private Double monthlyPay;
	private Double totalAmountToPay;
	
	public double setInterestRate() {
		return 5.5;
	}
	
	public double setMonthlyPay(Double loanAmount, Double interestRate, Integer noMonthsPay) {
		Double monthlyrate = interestRate/(noMonthsPay*100);
		Double monthlyPay = (loanAmount * monthlyrate * Math.pow(1 + monthlyrate, noMonthsPay)) /
				(Math.pow(1 + monthlyrate, noMonthsPay) - 1);
		return monthlyPay;
	}
	
	public Double setTotalAmountToPay1(Double monthlyPay, Double loanAmount) {
		return (monthlyPay * 12) + loanAmount;
	}
	
	public Double remainingAmount(Double loanAmount, Double amountPaid) {
		return loanAmount - amountPaid;
	}
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<LoanPayment> loanPayments;

	public void setRemainingAmountToPay(double remainingAmountToPay2) {
		// TODO Auto-generated method stub
		
	}
}
