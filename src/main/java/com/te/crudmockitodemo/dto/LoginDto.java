package com.te.crudmockitodemo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {
	@NotEmpty
	private String empId;
	@NotEmpty
	@Size(min = 3,max = 10,message = "password must be minimum of 3 and maximum of 10")
	private String empPwd;
}
