package org.noname.labo.fly.dao;

import org.noname.labo.fly.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends CrudRepository<User, Integer>{

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name)")
	User findByName(@Param("name") String name);
	
	@Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
	User findByEmail(@Param("email")String email);
}
