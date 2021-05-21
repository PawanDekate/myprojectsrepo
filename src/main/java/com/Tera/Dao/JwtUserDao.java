package com.Tera.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Tera.Entity.JwtUser;

@Repository
public interface JwtUserDao extends CrudRepository<JwtUser, Integer>{

	JwtUser findByUsername(String username);
}
