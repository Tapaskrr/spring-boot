package com.te.crudmockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.crudmockitodemo.dao.EmpDao;
import com.te.crudmockitodemo.dto.EmpDto;
import com.te.crudmockitodemo.dto.LoginDto;
import com.te.crudmockitodemo.dto.ResponseDto;
import com.te.crudmockitodemo.entity.Employee;
import com.te.crudmockitodemo.service.EmpService;
import com.te.crudmockitodemo.service.EmpServiceImpl;

 
@SpringBootTest
class CrudmockitodemoApplicationTests {

	private MockMvc mockMvc;
	
	@Autowired
	private EmpService empService;
	
	@MockBean
	private EmpDao dao;
	
	@Test
	public void registerPositiveTest() {
		EmpDto dto=new EmpDto("tys1","12345","tapas","tapas@gmail.com");
		Employee emp=new Employee("tys1","12345","tapas","tapas@gmail.com");
		when(dao.save(emp)).thenReturn(emp);
		assertEquals(dto, empService.register(dto));
	}
	
//	@Test
//	public void registerNegativeTest() {
//		EmpDto dto=new EmpDto("tys1","12345","tapas","tapas@gmail.com");
//		Employee emp=new Employee("tys1","12345","tapas","tapas@gmail.com");
//		when(dao.save(emp)).thenReturn(null);
//		assertEquals(dto, empService.register(dto));
//	}
	
	@Test
	public void loginTest() {
		LoginDto dto=new LoginDto("tys1","12345");
		EmpDto empDto=new EmpDto("tys1","12345","tapas","tapas@gmail.com");
		Employee emp=new Employee("tys1","12345","tapas","tapas@gmail.com");
		when(dao.findByEmpId(dto.getEmpId())).thenReturn(emp);
		assertEquals(empDto, empService.login(dto));
	}

	@Test
	public void updateTest() {
		EmpDto dto=new EmpDto("tys1","12345","tapas","tapas@gmail.com");
		Employee emp=new Employee("tys1","12345","tapas","tapas@gmail.com");
		when(dao.save(emp)).thenReturn(emp);
		assertEquals(dto, empService.register(dto));
	}
	
	@Test
	public void deleteTest() {
		String empId="tys1";
//		String empId="tys1";
		Employee emp=new Employee("tys1","12345","tapas","tapas@gmail.com");
		when(dao.findByEmpId(empId)).thenReturn(emp);
		empService.delete(empId);
		verify(dao, times(1)).deleteById(empId);
//		assertEquals(true, empService.delete(empId));
	}
	
	@Test
	public void serializeTestForEmployee() throws JsonMappingException, JsonProcessingException {
		String json= "{\"empId\":\"tys1\",\"empPwd\":\"12345\",\"empName\":\"tapas\",\"empMail\":\"tapas@gmail.com\"}";
		ObjectMapper objectMapper=new ObjectMapper();
		Employee employee=objectMapper.readValue(json, Employee.class);
		String writeValueAsString = objectMapper.writeValueAsString(employee);
		assertEquals(writeValueAsString, json);
	}
	
	@Test
	public void deserializeTestForEmployee() throws JsonMappingException, JsonProcessingException {
		String json= "{\"empId\":\"tys1\",\"empPwd\":\"12345\",\"empName\":\"tapas\",\"empMail\":\"tapas@gmail.com\"}";
		ObjectMapper objectMapper=new ObjectMapper();
		Employee employee=objectMapper.readValue(json, Employee.class);
		assertEquals("tys1", employee.getEmpId());
	}
	
	@Test
	public void serializeTestForEmpDto() throws JsonMappingException, JsonProcessingException {
		String json= "{\"empId\":\"tys1\",\"empPwd\":\"12345\",\"empName\":\"tapas\",\"empMail\":\"tapas@gmail.com\"}";
		ObjectMapper objectMapper=new ObjectMapper();
		EmpDto emp=objectMapper.readValue(json, EmpDto.class);
		String writeValueAsString = objectMapper.writeValueAsString(emp);
		assertEquals(writeValueAsString, json);
	}
	
	@Test
	public void deserializeTestForEmpDto() throws JsonMappingException, JsonProcessingException {
		String json= "{\"empId\":\"tys1\",\"empPwd\":\"12345\",\"empName\":\"tapas\",\"empMail\":\"tapas@gmail.com\"}";
		ObjectMapper objectMapper=new ObjectMapper();
		EmpDto emp=objectMapper.readValue(json, EmpDto.class);
		assertEquals("tys1", emp.getEmpId());
	}
	
	@Test
	public void serializeTestForLoginDto() throws JsonMappingException, JsonProcessingException {
		String json= "{\"empId\":\"tys1\",\"empPwd\":\"12345\"}";
		ObjectMapper objectMapper=new ObjectMapper();
		LoginDto employee=objectMapper.readValue(json, LoginDto.class);
		String writeValueAsString = objectMapper.writeValueAsString(employee);
		assertEquals(writeValueAsString, json);
	}
	
	@Test
	public void deserializeTestForLoginDto() throws JsonMappingException, JsonProcessingException {
		String json= "{\"empId\":\"tys1\",\"empPwd\":\"12345\"}";
		ObjectMapper objectMapper=new ObjectMapper();
		LoginDto employee=objectMapper.readValue(json, LoginDto.class);
		assertEquals("tys1", employee.getEmpId());
	}
	
	@Test
	public void serializeTestForResponseDto() throws JsonMappingException, JsonProcessingException {
		String json= "{\"status\":true,\"msg\":\"success\",\"object\":null}";
		ObjectMapper objectMapper=new ObjectMapper();
		ResponseDto employee=objectMapper.readValue(json, ResponseDto.class);
		String writeValueAsString = objectMapper.writeValueAsString(employee);
		assertEquals(writeValueAsString, json);
	}
	
	@Test
	public void deserializeTestForResponseDto() throws JsonMappingException, JsonProcessingException {
		String json= "{\"status\":true,\"msg\":\"success\",\"object\":null}";
		ObjectMapper objectMapper=new ObjectMapper();
		ResponseDto employee=objectMapper.readValue(json, ResponseDto.class);
		assertEquals(true, employee.getStatus());
	}
	
//	@Test
//	public void testRegisterController() throws Exception {
//		EmpDto dto=new EmpDto("tys1","12345","tapas","tapas@gmail.com");
//		when(empService.register(dto)).thenReturn(dto);
//		mockMvc.perform(post("/start/register").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.msg", Matchers.is("successfully registered")));
//	}
	
	
	
}
