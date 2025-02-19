package com.bankApp.entityResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

	private Long accountId;
	private String accountNumber;
	private Double balance;
	private String accountType;
	private Long customerId;
	private Long branchId;
	
}
