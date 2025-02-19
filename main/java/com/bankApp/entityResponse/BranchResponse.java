package com.bankApp.entityResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponse {

	private Long branchId;
	private String branchName;
	private String branchAddress;
//	private String ifscCode;
	private Double branchBalance;
	
	public BranchResponse(Long branchId, String branchName, Double branchBalance, String address) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchBalance = branchBalance;
		this.branchAddress = address;
	}

}
