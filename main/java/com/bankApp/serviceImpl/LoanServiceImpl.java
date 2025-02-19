package com.bankApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankApp.entity.Customer;
import com.bankApp.entity.Loan;
import com.bankApp.entity.LoanPayment;
import com.bankApp.entityDto.LoanDto;
import com.bankApp.entityResponse.LoanResponse;
import com.bankApp.exception.ResourceNotFoundException;
import com.bankApp.repository.CustomerRepository;
import com.bankApp.repository.LoanPaymentRepository;
import com.bankApp.repository.LoanRepository;
import com.bankApp.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	LoanPaymentRepository loanPaymentRepository;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public LoanResponse addLoan(LoanDto dto, Long cid) {
		Customer customer = customerRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id:"+cid));
		
		Loan loan = new Loan();
		loan.setCustomer(customer);
		loan.setLoanAmount(dto.getLoanAmount());
		loan.setLoanType(dto.getLoanType());
		loan.setNoMonthsPay(dto.getNoMonthsPay());
		
		Double interestRate = loan.setInterestRate();
		loan.setInterestRate(interestRate);
		
		Double monthlyPay = loan.setMonthlyPay(dto.getLoanAmount(), interestRate, dto.getNoMonthsPay());
		loan.setMonthlyPay(monthlyPay);
		
		Double totalAmountToPay = loan.setTotalAmountToPay1(monthlyPay, dto.getLoanAmount());
		loan.setTotalAmountToPay(totalAmountToPay);
		
		loanRepository.save(loan);
		
		LoanResponse loanResponse = mapper.map(loan, LoanResponse.class);
		return loanResponse;
	}

	@Override
	public List<LoanResponse> getAllLoan() {
		List<Loan> loans = loanRepository.findAll();
		List<LoanResponse> loanResponses = loans.stream().map(acc -> mapper.map(acc, LoanResponse.class)).collect(Collectors.toList());
		return loanResponses;
	}

	@Override
	public LoanResponse getLoanById(Long id) {
		Loan loan = loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan not found with Id :"+id));
		LoanResponse loanResponse = mapper.map(loan, LoanResponse.class);
		return loanResponse;
	}

	@Override
	public LoanResponse updateLoan(Long id, LoanDto dto, Long cid) {
		Loan loan = loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan not found with Id :"+id));
		Customer customer = customerRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id:"+cid));
		
		loan.setCustomer(customer);
		loan.setLoanAmount(dto.getLoanAmount());
		loan.setLoanType(dto.getLoanType());
		loan.setNoMonthsPay(dto.getNoMonthsPay());
		loanRepository.save(loan);
		LoanResponse loanResponse = mapper.map(loan, LoanResponse.class);		
		return loanResponse;
	}

	@Override
	public void deleteLoan(Long id) {
		loanRepository.deleteById(id);
	}

	@Override
	public LoanResponse updateLoanPaid(Long id, LoanDto dto, Long cid, Long lpid) {
		Loan loan = loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan not found with Id :"+id));
		Customer customer = customerRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id:"+cid));
		LoanPayment loanPayment = loanPaymentRepository.findById(lpid).orElseThrow(() -> new ResourceNotFoundException("LoanPayment not found with id:"+lpid));
		
		loan.setCustomer(customer);
		loan.setLoanAmount(dto.getLoanAmount());
		loan.setLoanType(dto.getLoanType());
		loan.setNoMonthsPay(dto.getNoMonthsPay());

		loan.setRemainingAmountToPay(loanPayment.getAmountPaid() - dto.getLoanAmount());
		loanRepository.save(loan);
		LoanResponse loanResponse = mapper.map(loan, LoanResponse.class);		
		return loanResponse;
	}

}
