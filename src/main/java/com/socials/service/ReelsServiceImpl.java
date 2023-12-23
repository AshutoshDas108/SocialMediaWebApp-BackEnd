package com.socials.service;

import java.util.List;

import com.socials.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socials.models.Reels;
import com.socials.models.User;
import com.socials.repository.ReelsRepository;

@Service
public class ReelsServiceImpl implements ReelsService {
	
	
	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private Userservice userservice;

	@Override
	public Reels createReels(Reels reels, User user) {
		
		Reels createReel =new Reels();	
		
		createReel.setTitle(reels.getTitle());
		createReel.setVideo(reels.getVideo());
		createReel.setUser(user);
		
		Reels savedReel = reelsRepository.save(createReel);
		
		return savedReel;
	}

	@Override
	public List<Reels> findAllReels() {
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUsersReel(Integer userId) throws UserException {
		//automatically throws exception if there is no user
		userservice.findUserById(userId);
		return reelsRepository.findByUserId(userId);
	}

}
