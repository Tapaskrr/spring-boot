package com.te.crudmockitodemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.crudmockitodemo.controller.EmpController;
import com.te.crudmockitodemo.dto.EmpDto;
import com.te.crudmockitodemo.dto.LoginDto;
import com.te.crudmockitodemo.dto.ResponseDto;
import com.te.crudmockitodemo.service.EmpService;
import com.te.crudmockitodemo.service.EmpServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private EmpService empService;

//		@MockBean
//		private EmpDao dao;

	// plz check ????? was getting error when injectmocks
	
	@Autowired
	private EmpController empController;

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(empController).build();
	}

	@Test
		public void testRegisterController() throws Exception {
			ObjectMapper mapper=new ObjectMapper();
			EmpDto dto=new EmpDto("tys1","12345","tapas","tapas@gmail.com");
			when(empService.register(dto)).thenReturn(dto);
//				
				String contentAsString = mockMvc.perform(post("/start/register").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto))).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//				log.info("contentAsString{}",contentAsString);
				ResponseDto responseDto=mapper.readValue(contentAsString, ResponseDto.class);
//				log.info("contentAsString{}",contentAsString);
				assertEquals("successfully registered", responseDto.getMsg());
		}
	
	@Test
	public void testUpdateController() throws Exception {
		ObjectMapper mapper=new ObjectMapper();
		EmpDto dto=new EmpDto("tys1","12345","tapas","tapas@gmail.com");
		when(empService.update(dto)).thenReturn(dto);
//			
			String contentAsString = mockMvc.perform(put("/start/update").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto))).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			log.info("contentAsString{}",contentAsString);
//			ResponseDto responseDto=mapper.readValue(contentAsString, ResponseDto.class);
			log.info("contentAsString{}",contentAsString);
//			assertEquals("successfully updated", responseDto.getMsg());
	}

	@Test
		public void testLoginController() throws JsonProcessingException, UnsupportedEncodingException, Exception {
			ObjectMapper mapper=new ObjectMapper();
			EmpDto empDto=new EmpDto("tys1","12345","tapas","tapas@gmail.com");
			LoginDto dto=new LoginDto("tys1","12345");
			when(empService.login(dto)).thenReturn(empDto);
//			
			String contentAsString = mockMvc.perform(post("/start/login").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto))).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			ResponseDto responseDto=mapper.readValue(contentAsString, ResponseDto.class);
//			log.info("contentAsString{}",contentAsString);
			assertEquals("successfully login", responseDto.getMsg());
		}
	
	@Test
	public void testDeleteController() throws UnsupportedEncodingException, Exception {
		ObjectMapper mapper=new ObjectMapper();
		String empId="tys1";
		when(empService.delete(empId)).thenReturn(true);
		String contentAsString = mockMvc.perform(delete("/start/delete/{empId}",empId).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		log.info("contentAsString{}",contentAsString);
		ResponseDto responseDto=mapper.readValue(contentAsString, ResponseDto.class);
		assertEquals(true, responseDto.getObject());
	}
	


}
