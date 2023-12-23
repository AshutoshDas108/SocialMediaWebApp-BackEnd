package com.socials.service;

import java.util.List;

import com.socials.exceptions.StoryException;
import com.socials.exceptions.UserException;
import com.socials.models.Story;
import com.socials.models.User;

public interface StoryService {
	
	public Story createStory(Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws StoryException, UserException;

}
