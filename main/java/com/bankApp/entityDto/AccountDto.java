package com.bankApp.entityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

	private Double balance;
	private String accountType;
	
	private Long customerId;
	private Long branchId;
	
}
