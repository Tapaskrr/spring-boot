package com.te.crudmockitodemo.dto;

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
	private String empMail;
}
