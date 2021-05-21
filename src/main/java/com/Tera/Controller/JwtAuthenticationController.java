package com.Tera.Controller;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Tera.Dto.JwtUserDto;
import com.Tera.Entity.JwtRequest;
import com.Tera.Entity.JwtResponse;
import com.Tera.Entity.JwtUser;
import com.Tera.Mapper.JwtMapper;
import com.Tera.Service.JwtUserDetailsService;
import com.Tera.config.JwtTokenUtil;

import ch.qos.logback.core.joran.util.beans.BeanUtil;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private JwtMapper jwtMapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public int saveUser(@RequestBody JwtUserDto user) throws Exception {
		JwtUser jwtUser = new JwtUser();
		BeanUtils.copyProperties(user,jwtUser);
		jwtUser.setPassword(bcryptEncoder.encode(jwtUser.getPassword()));
		return jwtMapper.saveUser(jwtUser);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}