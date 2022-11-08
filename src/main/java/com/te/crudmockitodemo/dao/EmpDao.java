package com.te.crudmockitodemo.dao;

import org.springframework.data.repository.CrudRepository;

import com.te.crudmockitodemo.entity.Employee;

public interface EmpDao extends CrudRepository<Employee, String> {
	public Employee findByEmpId(String empId);

}
