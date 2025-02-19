package com.bankApp.entityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {

	private String loanType;
	private Double loanAmount;
	private Integer noMonthsPay;
	
	private Long customerId;

}
