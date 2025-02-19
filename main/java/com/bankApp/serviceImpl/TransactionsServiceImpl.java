package com.bankApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bankApp.entity.Account;
import com.bankApp.entity.Transactions;
import com.bankApp.entityDto.TransactionsDto;
import com.bankApp.entityResponse.TransactionsResponse;
import com.bankApp.exception.ResourceNotFoundException;
import com.bankApp.repository.AccountRepository;
import com.bankApp.repository.CustomerRepository;
import com.bankApp.repository.TransactionsRepository;
import com.bankApp.service.TransactionsService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class TransactionsServiceImpl implements TransactionsService{
	
	@Autowired
	TransactionsRepository transactionsRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public TransactionsResponse addTransactions(TransactionsDto dto, Long fid, Long tid) throws MessagingException {
		Account account = accountRepository.findById(fid).orElseThrow(() -> new ResourceNotFoundException("Account not found with id:"+fid));
		Account account2 = accountRepository.findById(tid).orElseThrow(() -> new ResourceNotFoundException("Account not found with id:"+tid));
		
		Transactions transactions = new Transactions();
		transactions.setAmount(dto.getAmount());
		transactions.setTransactionType(dto.getTransactionType());
		transactions.setTransactionId(transactions.getTransactionId());
		transactions.setTransactionDate(transactions.getTransactionDate());
		transactions.setFromAccount(account);
		transactions.setToAccount(account2);
		
		transactionsRepository.save(transactions);
		
		account.setBalance(account.getBalance()-transactions.getAmount());
		account2.setBalance(account2.getBalance()+transactions.getAmount());
		
		accountRepository.save(account);
		accountRepository.save(account2);
				
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(customerRepository.getEmailByAccountId(fid));
		helper.setSubject("Transaction Alert - Id:"+transactions.getTransactionId());
		helper.setText("Dear Mr."+customerRepository.getNameByAccountId(fid)+","+
		"\n\nThis is transation alert mail \n Account no:"+account.getAccountNumber()+
		"debited by "+transactions.getAmount()+" on "+transactions.getTransactionDate()+" transfer to "+customerRepository.getNameByAccountId(tid));
		sender.send(message);
		
		MimeMessageHelper helper1 = new MimeMessageHelper(message, true);
		helper1.setTo(customerRepository.getEmailByAccountId(tid));
		helper1.setSubject("Transaction Alert - Id:"+transactions.getTransactionId());
		helper1.setText("Dear Mr."+customerRepository.getNameByAccountId(tid)+","+
		"\n\nThis is transation alert mail \n Account no:"+account2.getAccountNumber()+
		"credited by "+transactions.getAmount()+" on "+transactions.getTransactionDate()+" transfer from "+customerRepository.getNameByAccountId(fid));
		sender.send(message);
		
		TransactionsResponse transactionsResponse = mapper.map(transactions, TransactionsResponse.class);
		return transactionsResponse;
	}

	@Override
	public List<TransactionsResponse> getAllTransactions() {
		List<Transactions> transactionss = transactionsRepository.findAll();
		List<TransactionsResponse> transactionsResponses = transactionss.stream().map(acc -> mapper.map(acc, TransactionsResponse.class)).collect(Collectors.toList());
		return transactionsResponses;
	}

	@Override
	public TransactionsResponse getTransactionsById(Long id) {
		Transactions transactions = transactionsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transactions not found with Id :"+id));
		TransactionsResponse transactionsResponse = mapper.map(transactions, TransactionsResponse.class);
		return transactionsResponse;
	}

	@Override
	public TransactionsResponse updateTransactions(Long id, TransactionsDto dto) {
		Transactions transactions = transactionsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transactions not found with Id :"+id));
		mapper.map(dto, transactions);
		transactionsRepository.save(transactions);
		TransactionsResponse transactionsResponse = mapper.map(transactions, TransactionsResponse.class);		
		return transactionsResponse;
	}

	@Override
	public void deleteTransactions(Long id) {
		transactionsRepository.deleteById(id);
	}

}
