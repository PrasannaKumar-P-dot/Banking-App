package com.bankApp.service;

import java.util.List;

import com.bankApp.entityDto.AccountDto;
import com.bankApp.entityResponse.AccountResponse;

import jakarta.mail.MessagingException;

public interface AccountService {

	AccountResponse addAccount(AccountDto employeeDto, Long cid, Long bid) throws MessagingException;

	List<AccountResponse> getAllAccount();

	AccountResponse getAccountById(Long id);

	AccountResponse updateAccount(Long id, AccountDto dto);

	void deleteAccount(Long id);

}
