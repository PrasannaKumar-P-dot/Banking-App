package com.bankApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bankApp.entity.Account;
import com.bankApp.entity.Branch;
import com.bankApp.entity.Customer;
import com.bankApp.entityDto.AccountDto;
import com.bankApp.entityResponse.AccountResponse;
import com.bankApp.exception.ResourceNotFoundException;
import com.bankApp.repository.AccountRepository;
import com.bankApp.repository.BranchRepository;
import com.bankApp.repository.CustomerRepository;
import com.bankApp.service.AccountService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{
	
//	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	BranchRepository branchRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	private JavaMailSender sender;
	
	
	@Override
	public AccountResponse addAccount(AccountDto dto, Long cid, Long bid) throws MessagingException {
		
		log.info("Adding account for customer ID: {} at Branch ID: {}",cid,bid);
		
		Customer customer = customerRepository.findById(cid).orElseThrow(() -> {
            log.error("Customer not found with ID: {}", cid);
            return new ResourceNotFoundException("Customer not found with id" + cid);
        });
		
		Branch branch =  branchRepository.findById(bid).orElseThrow(() ->{
			log.error("Branch not found with ID: {}",bid);
			return new ResourceNotFoundException("Branch not found with id:"+bid);
		});
		
		Account account = new Account();
		account.setBalance(dto.getBalance());
		account.setAccountType(dto.getAccountType());
		account.setCustomer(customer);
		account.setBranch(branch);
		accountRepository.save(account);
		
		log.info("Account created successfully with account number: {}",account.getAccountNumber());
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(customer.getEmail());
		helper.setSubject("Congratulations Your Account is Created - Demo");
		helper.setText("Dear Mr."+customer.getCustomerName()+
				"\n \nYour account has been created and your account number is: "+ account.getAccountNumber()+
				"\nYour account type is: "+account.getAccountType()+
				"\nYour account Branch will be: "+branch.getBranchName()+
				"\nYour account balance is: "+account.getBalance());
		sender.send(message);
		
		AccountResponse accountResponse = mapper.map(account, AccountResponse.class);
		return accountResponse;
	}

	@Override
	public List<AccountResponse> getAllAccount() {
		log.info("Fetching all accounts");
		List<Account> accounts = accountRepository.findAll();
		List<AccountResponse> accountResponses = accounts.stream().map(acc -> mapper.map(acc, AccountResponse.class)).collect(Collectors.toList());
		return accountResponses;
	}

	@Override
	public AccountResponse getAccountById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(() -> {
			log.error("Account not found with ID: {}",id);
		return new RuntimeException("Account not found with Id :"+id);});
		AccountResponse accountResponse = mapper.map(account, AccountResponse.class);
		return accountResponse;
	}

	@Override
	public AccountResponse updateAccount(Long id, AccountDto dto) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found with Id :"+id));
		mapper.map(dto, account);
		accountRepository.save(account);
		log.info("Account updated succussfully with ID: {}", id);
		AccountResponse accountResponse = mapper.map(account, AccountResponse.class);		
		return accountResponse;
	}

	@Override
	public void deleteAccount(Long id) {
//		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found with Id :"+id));
		log.info("Account deleted successfully with ID: {}",id);
		accountRepository.deleteById(id);
	}

}
