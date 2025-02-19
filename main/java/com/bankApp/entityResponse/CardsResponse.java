package com.bankApp.entityResponse;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsResponse {
	
	private Long cardId;
	private String cardNumber;
	private String cardType;
	private Date expiryDate;
	private Long customerId;
	

}
