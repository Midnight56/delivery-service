package org.noname.labo.fly.dao;

import org.noname.labo.fly.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Integer>{
	
	Role findByName(String name);
}
