package com.bankApp.service;

import java.util.List;

import com.bankApp.entityDto.TransactionsDto;
import com.bankApp.entityResponse.TransactionsResponse;

import jakarta.mail.MessagingException;

public interface TransactionsService {

	TransactionsResponse addTransactions(TransactionsDto dto, Long fid, Long tif) throws MessagingException;

	List<TransactionsResponse> getAllTransactions();

	TransactionsResponse getTransactionsById(Long id);

	TransactionsResponse updateTransactions(Long id, TransactionsDto dto);

	void deleteTransactions(Long id);

}
