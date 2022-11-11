package com.te.crudmockitodemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Authority {
	@Id
	private String name;
	private String pwd;
	private String role;

}
