package com.socials.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socials.models.Chat;
import com.socials.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
	
	public List<Chat> findByUsersId(Integer userId);
	
	
	//Chat-->same as className
	@Query("select c from Chat c Where :user Member of c.users And :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
	

}
