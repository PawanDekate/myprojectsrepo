package com.Tera.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;


import com.Tera.Entity.JwtUser;
import com.Tera.Entity.Jwtauthdata;


@Mapper
public interface JwtMapper {

	@Select("Select * from jwtauthdata")
	 List<Jwtauthdata> findAll();
	
	@Insert("INSERT INTO jwtuser(username,password)" + " VALUES (#{username},#{password})")
	 public int saveUser(JwtUser jwtUser);
	


}
