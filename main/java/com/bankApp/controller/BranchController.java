package com.bankApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.entityDto.BranchDto;
import com.bankApp.entityResponse.BranchResponse;
import com.bankApp.service.BranchService;

@RestController
@RequestMapping("/api/branch")
public class BranchController {
	
	@Autowired
	private BranchService service;
	
	public void setService(BranchService service) {
		this.service = service;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
	public BranchResponse addBranch(@RequestBody BranchDto dto) {
		return service.addBranch(dto);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/all")
	public List<BranchResponse> getAllBranch() {
		return service.getAllBranch();
	}
	
//	For get the branch total balance
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/balance")
	public List<BranchResponse> getAllBranchBalance() {
		return service.getAllBranchBalance();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public BranchResponse getBranchById(@RequestParam(value = "id") Long id) {
		return service.getBranchById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}")
	public BranchResponse updateBranch(@PathVariable(value = "id") Long id, @RequestBody BranchDto dto) {
		return service.updateBranch(id,dto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBranch(@PathVariable(value = "id") Long id) {
		service.deleteBranch(id);
		return ResponseEntity.ok("Branch deleted Successfully");
	}
}
