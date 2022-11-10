package com.te.crudmockitodemo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.crudmockitodemo.dao.EmpDao;
import com.te.crudmockitodemo.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private EmpDao empDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loading user in service");
		Employee findByEmpId = empDao.findByEmpId(username);
		if(findByEmpId!=null) {
			return new User(findByEmpId.getEmpId(), findByEmpId.getEmpPwd(), new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("user not found");
		}
		
	}

}
