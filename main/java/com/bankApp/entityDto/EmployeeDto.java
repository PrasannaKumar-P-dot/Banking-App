package com.bankApp.entityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	
	private String name;
	private String email;
	private String position;
	private Double salary;

	private Long branchId;
}
