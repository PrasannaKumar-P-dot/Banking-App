package com.bankApp.entityDto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsDto {

	private String cardType;
	private Date expiryDate;
	
	private Long customerId;

}
