package com.te.crudmockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.te.crudmockitodemo.dao.EmpDao;
import com.te.crudmockitodemo.dto.EmpDto;
import com.te.crudmockitodemo.dto.LoginDto;
import com.te.crudmockitodemo.entity.Employee;
import com.te.crudmockitodemo.service.EmpServiceImpl;

 
@SpringBootTest
class CrudmockitodemoApplicationTests {

	
	@Autowired
	private EmpServiceImpl empServiceImpl;
	
	@MockBean
	private EmpDao dao;
	
	@Test
	public void registerTest() {
		EmpDto dto=new EmpDto("tys1","12345","tapas1","tapas@gmail.com");
		Employee emp=new Employee("tys1","12345","tapas","tapas@gmail.com");
		when(dao.save(emp)).thenReturn(emp);
		assertEquals(dto, empServiceImpl.register(dto));
	}
	
	@Test
	public void loginTest() {
		LoginDto dto=new LoginDto("tys1","12345");
		EmpDto empDto=new EmpDto("tys1","12345","tapas","tapas@gmail.com");
		Employee emp=new Employee("tys1","12345","tapas","tapas@gmail.com");
		when(dao.findByEmpId(dto.getEmpId())).thenReturn(emp);
		assertEquals(empDto, empServiceImpl.login(dto));
	}

	@Test
	public void updateTest() {
		EmpDto dto=new EmpDto("tys1","12345","tapas","tapas@gmail.com");
		Employee emp=new Employee("tys1","12345","tapas","tapas@gmail.com");
		when(dao.save(emp)).thenReturn(emp);
		assertEquals(dto, empServiceImpl.register(dto));
	}
	
	@Test
	public void deleteTest() {
		String empId="tys1";
		Employee emp=new Employee("tys1","12345","tapas","tapas@gmail.com");
		empServiceImpl.delete(empId);
		verify(dao, times(1)).deleteById(empId);
	}
}
