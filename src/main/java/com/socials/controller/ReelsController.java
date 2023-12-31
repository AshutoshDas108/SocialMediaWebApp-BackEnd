package com.socials.controller;

import java.util.List;

import com.socials.exceptions.ReelsException;
import com.socials.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socials.models.Reels;
import com.socials.models.User;
import com.socials.service.ReelsService;
import com.socials.service.Userservice;

@RestController
public class ReelsController {
	
	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	private Userservice userservice;
	
	
	@PostMapping("api/reels")
	public Reels createReels(@RequestBody Reels reel, 
			@RequestHeader("Authorization")String jwt) throws UserException {
		
		
		User reqUser = userservice.findUserByJwt(jwt);
		
		Reels createdReels = reelsService.createReels(reel, reqUser);
		
		return createdReels;
	}
	
	
	
	@GetMapping("api/reels")
	public List<Reels> findAllReels() {
		
		List<Reels> reels = reelsService.findAllReels();
		
		return reels;
		
	}
	
	
	
	
	@GetMapping("api/reels/user/{userId}")
	public List<Reels> findUserReels(@PathVariable Integer userId) throws ReelsException, UserException {
		
		List<Reels> reels = reelsService.findUsersReel(userId);
		
		return reels;
		
	}

}
