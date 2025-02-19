package com.bankApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankApp.entity.Branch;
import com.bankApp.entityDto.BranchDto;
import com.bankApp.entityResponse.BranchResponse;
import com.bankApp.exception.ResourceNotFoundException;
import com.bankApp.repository.BranchRepository;
import com.bankApp.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService{

	@Autowired
	BranchRepository branchRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public BranchResponse addBranch(BranchDto branchDto) {
		Branch branch = mapper.map(branchDto, Branch.class);
		branchRepository.save(branch);
		BranchResponse branchResponse = mapper.map(branch, BranchResponse.class);
		return branchResponse;
	}

	@Override
	public List<BranchResponse> getAllBranch() {
		List<Branch> branchs = branchRepository.findAll();
		List<BranchResponse> branchResponses = branchs.stream().map(acc -> mapper.map(acc, BranchResponse.class)).collect(Collectors.toList());
		return branchResponses;
	}

	@Override
	public BranchResponse getBranchById(Long id) {
		Branch branch = branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found with Id :"+id));
		BranchResponse branchResponse = mapper.map(branch, BranchResponse.class);
		return branchResponse;
	}

	@Override
	public BranchResponse updateBranch(Long id, BranchDto dto) {
		Branch branch = branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found with Id :"+id));
		mapper.map(dto, branch);
		branchRepository.save(branch);
		BranchResponse branchResponse = mapper.map(branch, BranchResponse.class);		
		return branchResponse;
	}

	@Override
	public void deleteBranch(Long id) {
		branchRepository.deleteById(id);
	}

	@Override
	public List<BranchResponse> getAllBranchBalance() {
		List<Object[]> results = branchRepository.getTotalBalancePerBranch();
		List<BranchResponse> branchResponses = results.stream()
				.map(obj -> new BranchResponse((Long) obj[0], (String) obj[1], (String) obj[2], (Double) obj[3]))
				.collect(Collectors.toList());
		List<BranchResponse> branchResponses1 = branchResponses.stream().map(res -> mapper.map(res, BranchResponse.class)).collect(Collectors.toList());
		return branchResponses1;
	}

}
