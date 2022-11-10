package com.te.crudmockitodemo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpDto {
	
	private String empId;
	private String empPwd;
	private String empName;
//	@Email
//	@NotNull
	private String empMail;
//	private String roll;
}
