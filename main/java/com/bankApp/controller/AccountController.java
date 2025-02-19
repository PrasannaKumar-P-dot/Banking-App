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

import com.bankApp.entityDto.AccountDto;
import com.bankApp.entityResponse.AccountResponse;
import com.bankApp.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/account")
@Tag(name = "Account Management", description = "API for account management")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	public void setService(AccountService service) {
		this.service = service;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add/{cid}/{bid}")
	@Operation(summary = "Post account", description = "Add the account")
	public AccountResponse addAccount(@RequestBody AccountDto accountDto, @PathVariable(value = "cid") Long cid, @PathVariable(value = "bid") Long bid) throws MessagingException {
		return service.addAccount(accountDto, cid, bid);
	}
	
	@GetMapping
	@Operation(summary = "Get All Accounts", description = "Fetch all accounts")
	public List<AccountResponse> getAllAccount() {
		return service.getAllAccount();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	@Operation(summary = "Get Account By Id", description = "Fetch the account details by Id")
	public AccountResponse getAccountById(@RequestParam("id") Long id) {
		return service.getAccountById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}")
	@Operation(summary = "Update account by Id", description = "Fetch the account by Id and Update")
	public AccountResponse updateAccount(@PathVariable("id") Long id, @RequestBody AccountDto dto) {
		return service.updateAccount(id,dto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete account by Id", description = "Fetch the account by Id and delete")
	public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id) {
		service.deleteAccount(id);
		return ResponseEntity.ok("Employee deleted Successfully");
	}
}
