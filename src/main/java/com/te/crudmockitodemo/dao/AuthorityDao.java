package com.te.crudmockitodemo.dao;

import org.springframework.data.repository.CrudRepository;

import com.te.crudmockitodemo.entity.Authority;

public interface AuthorityDao extends CrudRepository<Authority, String> {
	public Authority findByName(String name);
}
