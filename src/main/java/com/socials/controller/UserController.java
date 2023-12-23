package com.socials.controller;

import java.util.List;

import com.socials.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socials.models.User;
import com.socials.repository.UserRepository;
import com.socials.service.Userservice;

@RestController
public class UserController {
	
	@Autowired
	private Userservice userService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/api/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	@GetMapping("/api/users/{id}")
	public User getUserById(@PathVariable("id") Integer id) throws Exception {
		
		return userService.findUserById(id);
		
	}
	
	
	@GetMapping("/api/users/emil{email}")
	public User getUserByEmail(@PathVariable String email) {
		
		return userService.findUserByEmail(email);
		
	}
	
	
	
	//don't send the id from the front end via path variable rather use jwt token to ensure security
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization")String jwt, @RequestBody User user) throws Exception {
		
		//only logged in user allowed to update not other user
		User reqUser = userService.findUserByJwt(jwt);
		
		
		User updatedUser = userService.updateUser(reqUser.getId(), user);
		
		return updatedUser;
		
	}
	
	
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHnadler(@RequestHeader("Authorization")String jwt, @PathVariable Integer userId2) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		User user = userService.followUser(reqUser.getId(), userId2);
		return user;
	}
	
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users = userService.searchUser(query);
		
		return users;
		
	}
	
	@DeleteMapping("/api/users/{id}")
	public String deleteUser(@PathVariable("id") Integer id) {

		return "user deleted successfully";
		
	}
	
	
	@GetMapping("api/user/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) throws UserException {
		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}

}
