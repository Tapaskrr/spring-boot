package com.te.crudmockitodemo.service;

import com.te.crudmockitodemo.dto.EmpDto;
import com.te.crudmockitodemo.dto.LoginDto;

public interface EmpService {

	public EmpDto register(EmpDto empDto);

	public EmpDto login(LoginDto loginDto);

	public EmpDto update(EmpDto empDto);

	public Boolean delete(String empId);
}
