package com.te.crudmockitodemo.exception;

public class EmployeeException extends RuntimeException {
	private String msg;

	public EmployeeException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return this.msg;
	}

}
