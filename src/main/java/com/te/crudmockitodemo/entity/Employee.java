package com.te.crudmockitodemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Employee  {
	@Id
	private String empId;
	private String empPwd;
	private String empName;
	private String empMail;
//	private String roll;
}
