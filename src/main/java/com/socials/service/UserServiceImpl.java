package com.socials.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socials.config.JwtProvider;
import com.socials.models.User;
import com.socials.repository.UserRepository;

@Service
public class UserServiceImpl implements Userservice{
	
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setGender(user.getGender());
		newUser.setPassword(user.getPassword());
		User savedUser = userRepository.save(newUser);
		
		return savedUser;
		
	}

	
	@Override
	public User findUserById(Integer userId) throws Exception {
		

		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent())
			return user.get();
		
		throw new Exception("User Not Found with " + userId);
		
	}

	
	@Override
	public User findUserByEmail(String email) {

		return userRepository.findByEmail(email);
		
	}

	
	
	
	/*	  
	  reqUser wants to follow user2
	  So, rewuser must be logged in user	  
	  */
	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws Exception {
		
		User reqUser = findUserById(reqUserId);
		
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(reqUserId);
		
		reqUser.getFollowings().add(userId2);
		
		userRepository.save(reqUser);
		userRepository.save(user2);
		
		return reqUser;
	}

	
	@Override
	public User updateUser(Integer id, User user) throws Exception {
		Optional<User> user1 = userRepository.findById(id);
		if(user1.isEmpty()) {
			throw new Exception("User does not exist with " + id);
		}
		
		User oldUser = user1.get();
		
		
		if(user.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
	      }
	    if(user.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		  }
	    if(user.getEmail()!=null) {
	    	oldUser.setEmail(user.getEmail());
	    }
	    if(user.getGender()!=null) {
	    	oldUser.setGender(user.getGender());
	    }

		
		return userRepository.save(oldUser);
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
		
	}


	@Override
	public User findUserByJwt(String jwt) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		User user = findUserByEmail(email);
		return user;
	}

}
