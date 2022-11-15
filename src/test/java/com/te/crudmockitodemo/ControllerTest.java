package com.te.crudmockitodemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.te.crudmockitodemo.controller.EmpController;
import com.te.crudmockitodemo.dto.EmpDto;
import com.te.crudmockitodemo.dto.LoginDto;
import com.te.crudmockitodemo.dto.ResponseDto;
import com.te.crudmockitodemo.service.EmpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
//@ExtendWith(SpringExtension.class)
public class ControllerTest {

	private MockMvc mockMvc;

	// if service is declared as @mockbean empcontroller has to be declare with
	// @autowired

	@Mock
	private EmpService empService;

//		@MockBean
//		private EmpDao dao;

	// plz check ????? was getting error when injectmocks

	@InjectMocks
	private EmpController empController;

	@Autowired
	private LocalValidatorFactoryBean bean;

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(empController).build();
	}

	@Test
	public void positiveTestRegisterController() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		EmpDto dto = new EmpDto("tys1", "12345", "tapas", "tapas@gmail.com");
		when(empService.register(dto)).thenReturn(dto);
//				
		String contentAsString = mockMvc
				.perform(post("/start/register").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//				log.info("contentAsString{}",contentAsString);
		ResponseDto responseDto = mapper.readValue(contentAsString, ResponseDto.class);
//				log.info("contentAsString{}",contentAsString);
		assertEquals("successfully registered", responseDto.getMsg());
	}

//	@Test
//	public void registerNegativeTest() throws Exception {
////		bean.validateProperty(null, null, null)
//		EmpDto dto = new EmpDto("tys1", "12", "tapas", "");
//		ObjectMapper mapper = new ObjectMapper();
//		assertThrows(MethodArgumentNotValidException.class,
//				() -> mockMvc.perform(post("/start/register").accept(MediaType.APPLICATION_JSON)
//						.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto))));
//	}

//	@Test(expect = IllegalArgumentException.class)
//	public void testAllNull() throws Exception {
//		EmpDto dto = new EmpDto("tys1", "12", "tapas", "");
//		bean.validateProperty(dto, dto.getEmpMail());
//	}

//	@Test
//	public void negativeTestLoginController() throws Exception {
//		ObjectMapper mapper=new ObjectMapper();
//		EmpDto dto=new EmpDto("tys1","12","tapas","tapas@gmail.com");
//		when(empService.register(dto)).thenReturn(dto);
//		assertThrows(MethodArgumentNotValidException.class, ()->empService.register(dto));
//	}

	@Test
	public void validatesRegistration() throws Exception {
		EmpDto dto = new EmpDto("tys1", "12345", "tapas", "");
		ObjectMapper mapper = new ObjectMapper();
		String contentAsString = mockMvc
				.perform(post("/start/register").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
//		log.info("contentAsString : {}",contentAsString);
//		assertEquals(contentAsString, is(Empty)));
//		String content = result.getResponse().getContentAsString();
//		assertThat(content, is(notNullValue()));
//		List<Message> messages = JsonUtils.fromJson(content, new TypeReference<List<Message>>() {
//		});
//		assertThat(messages.size(), is(1));
	}

	@Test
	public void testUpdateController() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		EmpDto dto = new EmpDto("tys1", "12345", "tapas", "tapas@gmail.com");
		when(empService.update(dto)).thenReturn(dto);
//			
		String contentAsString = mockMvc
				.perform(put("/start/update").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//			log.info("contentAsString{}",contentAsString);
		ResponseDto responseDto = mapper.readValue(contentAsString, ResponseDto.class);
//			log.info("contentAsString{}",contentAsString);
		assertEquals("successfully updated", responseDto.getMsg());
	}

	@Test
	public void testLoginController() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		EmpDto empDto = new EmpDto("tys1", "12345", "tapas", "tapas@gmail.com");
		LoginDto dto = new LoginDto("tys1", "12345");
		when(empService.login(dto)).thenReturn(empDto);
//			
		String contentAsString = mockMvc
				.perform(post("/start/login").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto responseDto = mapper.readValue(contentAsString, ResponseDto.class);
//			log.info("contentAsString{}",contentAsString);
		assertEquals("successfully login", responseDto.getMsg());
	}

	@Test
	public void testDeleteController() throws UnsupportedEncodingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		String empId = "tys1";
		when(empService.delete(empId)).thenReturn(true);
		String contentAsString = mockMvc
				.perform(delete("/start/delete/{empId}", empId).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		log.info("contentAsString{}",contentAsString);
		ResponseDto responseDto = mapper.readValue(contentAsString, ResponseDto.class);
		assertEquals(true, responseDto.getObject());
	}

}
