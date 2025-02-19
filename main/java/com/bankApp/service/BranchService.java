package com.bankApp.service;

import java.util.List;

import com.bankApp.entityDto.BranchDto;
import com.bankApp.entityResponse.BranchResponse;

public interface BranchService {

	BranchResponse addBranch(BranchDto branchDto);

	List<BranchResponse> getAllBranch();

	BranchResponse getBranchById(Long id);

	BranchResponse updateBranch(Long id, BranchDto dto);

	void deleteBranch(Long id);

	List<BranchResponse> getAllBranchBalance();

}
