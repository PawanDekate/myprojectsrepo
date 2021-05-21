package com.Tera;


import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Tera.Entity.JwtUser;
import com.Tera.Entity.Jwtauthdata;


@MappedTypes(JwtUser.class)
@MapperScan("com.Tera.Mapper")
@SpringBootApplication
public class MyBetisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBetisApplication.class, args);
	}

}
