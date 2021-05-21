package com.Tera.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tera.Entity.Jwtauthdata;
import com.Tera.Mapper.JwtMapper;

@RestController
public class EmployeeController {
	
	@Autowired
	private JwtMapper JwtMapper;
	
	public EmployeeController(JwtMapper jwtMapper) {
		this.JwtMapper = jwtMapper;
	}
	@RequestMapping("/hello" )
	public List<Jwtauthdata> getAll() {
		return JwtMapper.findAll();
	}
	
}