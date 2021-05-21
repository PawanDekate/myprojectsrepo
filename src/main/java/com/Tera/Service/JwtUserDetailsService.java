package com.Tera.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Tera.Dao.JwtUserDao;
import com.Tera.Dto.JwtUserDto;
import com.Tera.Entity.JwtUser;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private JwtUserDao jwtUserDao;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JwtUser jwtUser = jwtUserDao.findByUsername(username);
	    if(jwtUser == null) {
	    	throw new UsernameNotFoundException(" User not Found with username" +username);
		} 
	    return new org.springframework.security.core.userdetails.User(jwtUser.getUsername(),jwtUser.getPassword(), new ArrayList<>());
	}
	 
	
//	 public JwtUser save(JwtUserDto jwtUserDto) { JwtUser jwtUser = new JwtUser();
//	 jwtUser.setUsername(jwtUserDto.getUsername());
//	  jwtUser.setPassword(bcryptEncoder.encode(jwtUserDto.getPassword())); 
//	  return jwtUserDao.save(jwtUser); }
//	 
	
	
	
}
