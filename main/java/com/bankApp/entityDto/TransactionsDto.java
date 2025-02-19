package com.bankApp.entityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsDto {
	
	private Double amount;
	private String transactionType;
	
	private Long fromAccountId;
	private Long toAccountId;

}
