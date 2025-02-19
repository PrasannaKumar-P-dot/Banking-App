package com.bankApp.entityResponse;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanPaymentResponse {
	
	private Long paymentId;
	private Date paymentDate;
	private Double amountPaid;
	private Long loanId;
	private Double remainingAmountToPay;

}
