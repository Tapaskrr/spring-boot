package com.te.crudmockitodemo.controller;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.te.crudmockitodemo.dto.ResponseDto;
import com.te.crudmockitodemo.exception.EmployeeException;

@RestControllerAdvice
//extends ResponseEntityExceptionHandler
public class ExceptionController  {
	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<?> exceptionHandler(EmployeeException employeeException) {
		return new ResponseEntity<ResponseDto>(new ResponseDto(false, employeeException.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgsNotValidException(MethodArgumentNotValidException e){
		Map<String, String> resp=new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error)->{
			String field = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			resp.put(field, defaultMessage);
		});
		return new ResponseEntity<ResponseDto>(new ResponseDto(false, "failed", "hello world"),HttpStatus.BAD_REQUEST);
	}
}
