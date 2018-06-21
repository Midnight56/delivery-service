package org.noname.labo.fly.service;

public interface UserOptionsService {
	
	Integer findUserByRating(Integer rating);
	
	Integer findBlockedUser(Boolean block);

}
