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

import com.bankApp.entityDto.LoanPaymentDto;
import com.bankApp.entityResponse.LoanPaymentResponse;
import com.bankApp.service.LoanPaymentService;

@RestController
@RequestMapping("/api/payment")
public class LoanPaymentController {
	
	@Autowired
	private LoanPaymentService service;
	
	public void setService(LoanPaymentService service) {
		this.service = service;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/add/{lid}")
	public LoanPaymentResponse addLoanPayment(@RequestBody LoanPaymentDto dto, @PathVariable(value = "lid") Long lid) {
		return service.addLoanPayment(dto, lid);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/all")
	public List<LoanPaymentResponse> getAllLoanPayment() {
		return service.getAllLoanPayment();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public LoanPaymentResponse getLoanPaymentById(@PathVariable(value = "id") Long id) {
		return service.getLoanPaymentById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}/{lid}")
	public LoanPaymentResponse updateLoanPayment(@PathVariable(value = "id") Long id, @RequestBody LoanPaymentDto dto, @PathVariable("lid") Long lid) {
		return service.updateLoanPayment(id,dto, lid);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteLoanPayment(@PathVariable(value = "id") Long id) {
		service.deleteLoanPayment(id);
		return ResponseEntity.ok("LoanPayment deleted Successfully");
	}
}
