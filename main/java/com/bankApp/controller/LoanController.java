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
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.entityDto.LoanDto;
import com.bankApp.entityResponse.LoanResponse;
import com.bankApp.service.LoanService;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
	
	@Autowired
	private LoanService service;
	
	public void setService(LoanService service) {
		this.service = service;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add/{cid}")
	public LoanResponse addLoan(@RequestBody LoanDto dto, @PathVariable(value = "cid") Long cid) {
		return service.addLoan(dto, cid);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/all")
	public List<LoanResponse> getAllLoan() {
		return service.getAllLoan();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public LoanResponse getLoanById(@PathVariable(value = "id") Long id) {
		return service.getLoanById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}/{cid}")
	public LoanResponse updateLoan(@PathVariable(value = "id") Long id, @RequestBody LoanDto dto, @PathVariable(value = "cid") Long cid) {
		return service.updateLoan(id,dto,cid);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/paid/{id}/{cid}/{lpid}")
	public LoanResponse updateLoanPaid(@PathVariable(value = "id") Long id, @RequestBody LoanDto dto, @PathVariable(value = "cid") Long cid, @PathVariable(value = "lpid") Long lpid) {
		return service.updateLoanPaid(id,dto,cid,lpid);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteLoan(@PathVariable(value = "id") Long id) {
		service.deleteLoan(id);
		return ResponseEntity.ok("Loan deleted Successfully");
	}
}
