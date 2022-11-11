package com.te.crudmockitodemo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.te.crudmockitodemo.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService detailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("inside request filter method");
		String header = request.getHeader("Authorization");
		String username = null;
		String token = null;
		log.info("inside request filter method");
		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
			username = jwtUtil.extractUsername(token);
			log.info("username generated in filter");
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = detailsService.loadUserByUsername(username);
			log.info("user details generated in filter");

			if (jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken jwt = new UsernamePasswordAuthenticationToken(
						userDetails.getPassword(), userDetails.getUsername(), userDetails.getAuthorities());
				log.info("jwt generated in filter");
				log.info("{}", userDetails.getUsername());
				log.info("{}", userDetails.getPassword());
				log.info("{}",userDetails.getAuthorities().toString());
				jwt.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(jwt);
			}
		}
		filterChain.doFilter(request, response);
	}
}
