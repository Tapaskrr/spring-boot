package com.te.crudmockitodemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.crudmockitodemo.dao.AuthorityDao;
import com.te.crudmockitodemo.dao.EmpDao;
import com.te.crudmockitodemo.entity.Authority;
import com.te.crudmockitodemo.entity.Employee;
import com.te.crudmockitodemo.entity.MyUserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private AuthorityDao empDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loading user in service");
		Authority user = empDao.findByName(username);
		if(user!=null) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//			authorities.add(employee.getRoles());
			authorities.add(authority);
//			return authorities;
//			return new user(user.getName(),user.getPwd(),new ArrayList<>());
			return new MyUserDetails(user);
		}else {
			throw new UsernameNotFoundException("user not found");
		}
		
	}

}
