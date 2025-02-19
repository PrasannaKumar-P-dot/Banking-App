package com.bankApp.entityDto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	
	private String customerName;
	private Date dob;
	private String email;
	private Long phone;
	private String address;

}
