package com.socials.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socials.models.Story;
import com.socials.models.User;
import com.socials.repository.StoryRepository;


@Service
public class StoryServiceImpl implements StoryService {
	
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private Userservice userservice;

	@Override
	public Story createStory(Story story, User user) {
		Story createdStory =new Story();
		createdStory.setCaption(story.getCaption());
		createdStory.setUser(user);
		createdStory.setImage(story.getImage());
		createdStory.setTimeStamp(LocalDateTime.now());
		
		Story savedStory = storyRepository.save(createdStory);
		return savedStory;
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		userservice.findUserById(userId);
		List<Story> stories = storyRepository.findByUserId(userId);
		return stories;
	}

}
