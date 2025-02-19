package com.bankApp.service;

import java.util.List;

import com.bankApp.entityDto.LoanPaymentDto;
import com.bankApp.entityResponse.LoanPaymentResponse;

public interface LoanPaymentService {

	LoanPaymentResponse addLoanPayment(LoanPaymentDto dto, Long lid);

	List<LoanPaymentResponse> getAllLoanPayment();

	LoanPaymentResponse getLoanPaymentById(Long id);

	LoanPaymentResponse updateLoanPayment(Long id, LoanPaymentDto dto, Long lid);

	void deleteLoanPayment(Long id);

}
