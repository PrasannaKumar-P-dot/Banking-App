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

import com.bankApp.entityDto.TransactionsDto;
import com.bankApp.entityResponse.TransactionsResponse;
import com.bankApp.service.TransactionsService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {
	
	@Autowired
	private TransactionsService service;
	
	public void setService(TransactionsService service) {
		this.service = service;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/add/{fid}/{tid}")
	public TransactionsResponse addTransactions(@RequestBody TransactionsDto dto, @PathVariable(value = "fid") Long fid, @PathVariable(value = "tid") Long tid) throws MessagingException {
		return service.addTransactions(dto, fid, tid);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping
	public List<TransactionsResponse> getAllTransactions() {
		return service.getAllTransactions();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public TransactionsResponse getTransactionsById(@PathVariable(value = "id") Long id) {
		return service.getTransactionsById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}")
	public TransactionsResponse updateTransactions(@PathVariable(value = "id") Long id, @RequestBody TransactionsDto dto) {
		return service.updateTransactions(id,dto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTransactions(@PathVariable(value = "id") Long id) {
		service.deleteTransactions(id);
		return ResponseEntity.ok("Transaction deleted Successfully");
	}
}
