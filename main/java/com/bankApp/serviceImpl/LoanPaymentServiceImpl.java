package com.bankApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankApp.entity.Loan;
import com.bankApp.entity.LoanPayment;
import com.bankApp.entityDto.LoanPaymentDto;
import com.bankApp.entityResponse.LoanPaymentResponse;
import com.bankApp.exception.ResourceNotFoundException;
import com.bankApp.repository.LoanPaymentRepository;
import com.bankApp.repository.LoanRepository;
import com.bankApp.service.LoanPaymentService;

@Service
public class LoanPaymentServiceImpl implements LoanPaymentService{
	
	@Autowired
	LoanPaymentRepository loanPaymentRepository;
	
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public LoanPaymentResponse addLoanPayment(LoanPaymentDto dto, Long lid) {
		Loan loan = loanRepository.findById(lid).orElseThrow(() -> new ResourceNotFoundException("Loan not found with id:"+lid));
		
		LoanPayment loanPayment = new LoanPayment();
		loanPayment.setAmountPaid(dto.getAmountPaid());
		loanPayment.setLoan(loan);
		
		Double remainingAmount = loanPayment.setRemainingAmount(loan.getLoanAmount(), dto.getAmountPaid());
		loanPayment.setRemainingAmountToPay(remainingAmount);
		
		loanPaymentRepository.save(loanPayment);
		
		LoanPaymentResponse loanPaymentResponse = mapper.map(loanPayment, LoanPaymentResponse.class);
		return loanPaymentResponse;
	}

	@Override
	public List<LoanPaymentResponse> getAllLoanPayment() {
		List<LoanPayment> loanPayments = loanPaymentRepository.findAll();
		List<LoanPaymentResponse> loanPaymentResponses = loanPayments.stream().map(acc -> mapper.map(acc, LoanPaymentResponse.class)).collect(Collectors.toList());
		return loanPaymentResponses;
	}

	@Override
	public LoanPaymentResponse getLoanPaymentById(Long id) {
		LoanPayment loanPayment = loanPaymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LoanPayment not found with Id :"+id));
		LoanPaymentResponse loanPaymentResponse = mapper.map(loanPayment, LoanPaymentResponse.class);
		return loanPaymentResponse;
	}

	@Override
	public LoanPaymentResponse updateLoanPayment(Long id, LoanPaymentDto dto, Long lid) {
		LoanPayment loanPayment = loanPaymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LoanPayment not found with Id :"+id));

		Loan loan = loanRepository.findById(lid).orElseThrow(() -> new ResourceNotFoundException("Loan not found with id:"+lid));

		loanPayment.setAmountPaid(dto.getAmountPaid());
		loanPayment.setLoan(loan);
		
		Double remainingAmount = loanPayment.setRemainingAmount(loan.getLoanAmount(), dto.getAmountPaid());
		loanPayment.setRemainingAmountToPay(remainingAmount);
		
		loanPaymentRepository.save(loanPayment);
		LoanPaymentResponse loanPaymentResponse = mapper.map(loanPayment, LoanPaymentResponse.class);		
		return loanPaymentResponse;
	}

	@Override
	public void deleteLoanPayment(Long id) {
		loanPaymentRepository.deleteById(id);
	}

}
