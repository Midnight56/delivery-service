package org.noname.labo.fly.dao;

import org.noname.labo.fly.domain.UserOptions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserOptionsDao extends CrudRepository<UserOptions, Integer>{

	@Query("SELECT u.id FROM UserOptions u WHERE LOWER(u.rating) = LOWER(:rating)")
	Integer findUserByRating(Integer rating);
	
	@Query("SELECT u.id FROM UserOptions u WHERE LOWER(u.block) = LOWER(:block)")
	Integer findBlockedUser(Boolean block);
}
