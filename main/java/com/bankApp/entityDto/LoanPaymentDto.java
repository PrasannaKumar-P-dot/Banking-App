package com.bankApp.entityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanPaymentDto {
	
	private Double amountPaid;

	private Long loanId;

}
