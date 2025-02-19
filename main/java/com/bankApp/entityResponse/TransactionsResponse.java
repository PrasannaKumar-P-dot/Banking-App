package com.bankApp.entityResponse;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsResponse {

	private Long transactionId;
	private Date transactionDate;
	private Double amount;
	private String transactionType;
	private Long fromAccountId;
	private Long toAccountId;

}
