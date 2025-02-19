package com.bankApp.entityResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse {

	private Long loanId;
	private String loanType;
	private Double loanAmount;
	private Double interestRate;
	private Double remainingAmountToPay;
	private Double monthlyPay;
	private Double totalAmountToPay;

}
