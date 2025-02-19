package com.bankApp.service;

import java.util.List;

import com.bankApp.entityDto.LoanDto;
import com.bankApp.entityResponse.LoanResponse;

public interface LoanService {

	LoanResponse addLoan(LoanDto dto, Long cid);

	List<LoanResponse> getAllLoan();

	LoanResponse getLoanById(Long id);

	LoanResponse updateLoan(Long id, LoanDto dto, Long cid);

	void deleteLoan(Long id);

	LoanResponse updateLoanPaid(Long id, LoanDto dto, Long cid, Long lpid);

}
