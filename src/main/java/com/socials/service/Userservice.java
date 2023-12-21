package com.socials.service;

import java.util.List;

import com.socials.models.User;

public interface Userservice {
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws Exception;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer userId1, Integer userId2) throws Exception;
	
	public User updateUser(Integer id, User user) throws Exception;
	
	//search by any parameter
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);

}
