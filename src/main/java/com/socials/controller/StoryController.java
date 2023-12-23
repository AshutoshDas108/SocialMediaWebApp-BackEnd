package com.socials.controller;

import java.util.List;

import com.socials.exceptions.StoryException;
import com.socials.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socials.models.Story;
import com.socials.models.User;
import com.socials.service.StoryService;
import com.socials.service.Userservice;

@RestController
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private Userservice userservice;
	
	
	@PostMapping("api/story")
	public Story createStory(@RequestBody Story story, 
			@RequestHeader("Authorization") String jwt) throws UserException {
		
		User reqUser = userservice.findUserByJwt(jwt);
		
		Story createdStory = storyService.createStory(story, reqUser);
		
		return createdStory;
	}

	
	@GetMapping("api/story/user/{userId}")
	public List<Story> findUserStory(@PathVariable Integer userId, 
			@RequestHeader("Authorization") String jwt) throws StoryException, UserException {
		
		User reqUser = userservice.findUserByJwt(jwt);
		
		List<Story> stories = storyService.findStoryByUserId(userId);
		
		return stories;
	}
}
