package com.bankApp.entityResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
	
	private Long employeeId;
	
	private String name;
	private String email;
	private String position;
	private String salary;
	private String branchId;


}
